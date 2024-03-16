package com.yagmur.controller;

import com.yagmur.dto.request.CommentRequestDto;
import com.yagmur.entity.Hotel;
import com.yagmur.entity.Reservation;
import com.yagmur.entity.UserProfile;
import com.yagmur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //FİND-BY-TOKEN
    @GetMapping("/find-user-by-token")
    public ResponseEntity<Optional<UserProfile>> findByToken(String token){
        return ResponseEntity.ok(userService.findByToken(token));
    }

    //add hotel to users favorite list
    @PostMapping("/add-hotel-to-favorites")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addFavorite(String token, String hotelId){
        return ResponseEntity.ok(userService.addHotelToFavorites(token, hotelId));
    }

    //get favorite hotels
    @GetMapping("/get-favorite-hotels")
    public ResponseEntity<List<Hotel>> getFavoriteHotels(String token){
        return ResponseEntity.ok(userService.getFavoriteHotels(token));
    }


    @PostMapping("/update-user-passport")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updatePassport(String token, String passportNo, String passportExpiry){
        return ResponseEntity.ok(userService.updatePassport(token, passportNo, passportExpiry));
    }

    @GetMapping("/check-user-reservation")
    public ResponseEntity<List<Reservation>> checkReservation(String token){
        return ResponseEntity.ok(userService.checkUrReservation(token));
    }

    @PostMapping("/add-comment-to-hotel")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> addComment(CommentRequestDto dto){
        return ResponseEntity.ok(userService.doComment(dto));
    }



}
