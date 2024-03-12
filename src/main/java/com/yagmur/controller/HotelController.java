package com.yagmur.controller;

import com.yagmur.dto.request.*;
import com.yagmur.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/hotel")
public class HotelController {

    private final HotelService hotelService;

    @PostMapping(value = "/save")
    public ResponseEntity<Boolean> save(HotelCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.save(dto));
    }

    @PostMapping(value = "/addAddress")
    public ResponseEntity<Boolean> addAddress(HotelAddressCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addAdressToHotel(dto));
    }

    @PostMapping(value = "/addFacility")
    public ResponseEntity<Boolean> addFacility(FacilityFeaturesCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addFacilityFeature(dto));
    }

    @PostMapping(value = "/addImage")
    public ResponseEntity<Boolean> addImage(ImageCreateRequestDto dto){
        return ResponseEntity.ok(hotelService.addImageToHotel(dto));
    }

    @PostMapping(value = "/addRoom")
    public ResponseEntity<Boolean> addRoom(RoomRequestDto dto){
        return ResponseEntity.ok(hotelService.addRoomToHotel(dto));
    }


}
