package com.yagmur.service;

import com.yagmur.dto.request.*;
import com.yagmur.dto.response.GetCommentResponseDto;
import com.yagmur.entity.*;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.mapper.AddressMaper;
import com.yagmur.mapper.HotelMapper;
import com.yagmur.repository.*;
import com.yagmur.utility.JwtTokenManager;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HotelService {

    private final HotelRepository hotelRepository;
    private final AddressRepository addressRepository;
    private final ImagesRepository imagesRepository;
    private final RoomRepository roomRepository;
    private final FacilityFeaturesRepository facilityFeaturesRepository;
    private final HotelMapper hotelMapper;
    private final AddressMaper addressMapper;
    private final CategoryRepository categoryRepository;
    private final HotelCategoryRepository hotelCategoryRepository;
    private final CommentsRepository commentsRepository;
    private final JwtTokenManager jwtTokenManager;
    private final ReservationRepository reservationRepository;
    private final PaymentRepository paymentRepository;
    private final UserRepository userProfileRepository;
    private final CacheManager cacheManager;


    public boolean save(HotelCreateRequestDto dto) {
        Hotel hotel = hotelMapper.fromHotelCreateRequestToHotel(dto);
        hotelRepository.save(hotel);
        cacheManager.getCache("find-all-hotel").clear();
        cacheManager.getCache("sort-by-point").clear();
        return true;
    }

    public boolean addAdressToHotel(HotelAddressCreateRequestDto dto) {
        Address address = addressMapper.fromHotelAddressCreateRequestToAddress(dto);
        addressRepository.save(address);
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isPresent()){
            hotel.get().setAddressId(address.getId());
            hotelRepository.save(hotel.get());
        }else {
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        }
       return true;
    }

    public boolean addFacilityFeature(FacilityFeaturesCreateRequestDto dto) {
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isPresent()){
            FacilityFeatures facilityFeatures = hotelMapper.fromFacilityFeaturesCreateRequestToFacilityFeatures(dto);
            facilityFeaturesRepository.save(facilityFeatures);
        }
        return true;
    }

    public boolean addImageToHotel(ImageCreateRequestDto dto) {
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty()){
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        }
        Images image = hotelMapper.fromImageCreateRequestToImages(dto);
        imagesRepository.save(image);
        hotel.get().getImageIdList().add(image.getId());
        hotelRepository.save(hotel.get());
        return true;
    }

    public boolean addRoomToHotel(RoomRequestDto dto) {
        Optional<Hotel> hotel = hotelRepository.findById(dto.getHotelId());
        if (hotel.isEmpty()){
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        }
        Room room = hotelMapper.fromRoomRequestToRoom(dto);
        roomRepository.save(room);
        return true;
    }
    public Boolean addImageForHotelRoom(ImageRoomRequestDto dto){
        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty())
            throw new HolidayException(ErrorType.ROOM_NOT_FOUND);
        room.get().getImageId().add(dto.getImageUrl());
        roomRepository.save(room.get());
        return true;
    }

    public Boolean addCategory(CategoryRequestDto dto){
        categoryRepository.save(hotelMapper.fromRequestDtoToCategory(dto));
        return true;
    }

    public Boolean addHotelCategory(HotelCategoryRequestDto dto){
        hotelCategoryRepository.save(hotelMapper.fromRequestDtoToHotelCategory(dto));
        return true;
    }

    @Cacheable("find-hotel-by-category")
    public List<Hotel> findHotelByCategory(String categoryId){
        List<Category> categories = categoryRepository.findByParentId(categoryId);
        categories.add(categoryRepository.findById(categoryId).get());
        List<Hotel> hotels = new ArrayList<>();
        for (Category category : categories) {
            List<HotelCategory> hotelCategories = hotelCategoryRepository.findByCategoryId(category.getId());
            for (HotelCategory hotelCategory : hotelCategories) {
                hotels.add(hotelRepository.findById(hotelCategory.getHotelId()).get());
            }
        }
        return hotels;
    }

    public Reservation addReservation(ReservationRequestDto dto){
        String authId=jwtTokenManager.getIdFromToken(dto.getToken()).get();
        Optional<UserProfile> userProfile = userProfileRepository.findByAuthId(authId);
        if (userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        Optional<Room> room = roomRepository.findById(dto.getRoomId());
        if (room.isEmpty())
            throw new HolidayException(ErrorType.ROOM_NOT_FOUND);

        Reservation reservation = hotelMapper.fromRequestDtoToReservation(dto);
        LocalDate dateStart = LocalDate.parse(reservation.getStartDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        LocalDate dateEnd = LocalDate.parse(reservation.getEndDate(), DateTimeFormatter.ofPattern("dd.MM.yyyy"));
        if (dateStart == null || dateEnd == null){
            throw new HolidayException(ErrorType.INVALID_DATE_FORMAT);
        }
        if (dateStart.isAfter(dateEnd)){
            throw new HolidayException(ErrorType.INVALID_DATE);
        }
        Period sure = Period.between(dateStart, dateEnd);
        int gunFarki = sure.getDays();
        reservation.setAuthId(authId);
        reservation.setTotalPrice((room.get().getPrice()*reservation.getNumberOfPeople())*gunFarki);
        Reservation reservation1 = reservationRepository.save(reservation);
        userProfile.get().getReservationListId().add(reservation1.getId());
        userProfileRepository.save(userProfile.get());
        return reservation1;
    }

    public Payment addPayment(PaymentRequestDto dto){
        Payment payment = hotelMapper.fromRequestDtoToPayment(dto);
        if (payment.getCouponCode().equals("Enes"))
            payment.setPaymentAmount(payment.getPaymentAmount()*0.9);
        return paymentRepository.save(payment);
    }

    public Payment checkPayment(String paymentId){
        return paymentRepository.findById(paymentId).get();
    }

    public List<GetCommentResponseDto> getHotelComment(String hotelId){
        List<Comments> comments = commentsRepository.findByHotelId(hotelId);
        List<GetCommentResponseDto> getCommentResponseDtos = new ArrayList<>();
        for (Comments comment : comments) {
            getCommentResponseDtos.add(hotelMapper.fromCommentsToGetCommentResponseDto(comment));
        }
        return getCommentResponseDtos;
    }

    public Hotel findById(String id){
        Optional<Hotel> hotel = hotelRepository.findById(id);
        if (hotel.isEmpty())
            throw new HolidayException(ErrorType.HOTEL_NOT_FOUND);
        return hotel.get();
    }

    @Cacheable("find-all-hotel") //save aldığında
    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    @Cacheable("sort-by-point") //save aldığında, yorum eklendiğinde
    public List<Hotel> sortByPoint(){
        return hotelRepository.findAllByOrderByPointDesc();
    }

    //add category to hotel

}
