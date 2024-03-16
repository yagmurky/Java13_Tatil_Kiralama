package com.yagmur.controller;

import com.yagmur.dto.request.*;
import com.yagmur.dto.response.GetCommentResponseDto;
import com.yagmur.entity.Hotel;
import com.yagmur.entity.Payment;
import com.yagmur.entity.Reservation;
import com.yagmur.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(value = "/create-hotel")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> save(HotelCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.save(dto));
    }

    @PostMapping(value = "/create-address")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addAddress(HotelAddressCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addAdressToHotel(dto));
    }

    @PostMapping(value = "/create-facility")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addFacility(FacilityFeaturesCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addFacilityFeature(dto));
    }

    @PostMapping(value = "/create-image")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addImage(ImageCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addImageToHotel(dto));
    }

    @PostMapping(value = "/create-room")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addRoom(RoomRequestDto dto){
        return ResponseEntity.ok(hotelService.addRoomToHotel(dto));
    }

    @PostMapping("/create-image-for-room")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addImageForHotelRoom(ImageRoomRequestDto dto){
        return ResponseEntity.ok(hotelService.addImageForHotelRoom(dto));
    }

    @PostMapping("/create-category")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addCategory(CategoryRequestDto dto){
        return ResponseEntity.ok(hotelService.addCategory(dto));
    }

    @PostMapping("/create-hotel-category")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addHotelCategory(HotelCategoryRequestDto dto){
        return ResponseEntity.ok(hotelService.addHotelCategory(dto));
    }

    @GetMapping("/get-hotels-by-category")
    public ResponseEntity<List<Hotel>> getByCategory(String categoryId){
        return ResponseEntity.ok(hotelService.findHotelByCategory(categoryId));
    }

    @PostMapping("/create-reservation")
    @CrossOrigin("*")
    public ResponseEntity<Reservation> addReservation(ReservationRequestDto dto){
        return ResponseEntity.ok(hotelService.addReservation(dto));
    }

    @PostMapping("/create-payment")
    @CrossOrigin("*")
    public ResponseEntity<Payment> addPayment(PaymentRequestDto dto){
        return ResponseEntity.ok(hotelService.addPayment(dto));
    }

    @GetMapping("/get-payment")
    public ResponseEntity<Payment> checkPayment(String reservationId){
        return ResponseEntity.ok(hotelService.checkPayment(reservationId));
    }

    @GetMapping("/get-all-hotels")
    public ResponseEntity<List<Hotel>> getAll(){
        return ResponseEntity.ok(hotelService.findAll());
    }

    @GetMapping("/get-hotel-by-id")
    public ResponseEntity<Hotel> getById(String id){
        return ResponseEntity.ok(hotelService.findById(id));
    }

    @GetMapping("/get-comments-by-hotel")
    public ResponseEntity<List<GetCommentResponseDto>> getComments(String hotelId){
        return ResponseEntity.ok(hotelService.getHotelComment(hotelId));
    }

    @GetMapping("/get-hotels-sort-by-point")
    public ResponseEntity<List<Hotel>> getHotelByPoint(){
        return ResponseEntity.ok(hotelService.sortByPoint());
    }
}


