package com.yagmur.service;

import com.yagmur.dto.request.*;
import com.yagmur.entity.*;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.mapper.AddressMaper;
import com.yagmur.mapper.HotelMapper;
import com.yagmur.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

    public boolean save(HotelCreateRequestDto dto) {
        Hotel hotel = hotelMapper.fromHotelCreateRequestToHotel(dto);
        hotelRepository.save(hotel);
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

    public List<Hotel> findAll(){
        return hotelRepository.findAll();
    }

    public Hotel findById(String id){
        return hotelRepository.findById(id).get();
    }

    //add category to hotel
    public boolean addCategoryToHotel(CategoryRequestDto dto){

    }





}
