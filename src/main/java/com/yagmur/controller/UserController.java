package com.yagmur.controller;

import com.yagmur.entity.Hotel;
import com.yagmur.entity.UserProfile;
import com.yagmur.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    //FİND-BY-TOKEN
    @GetMapping("/find-by-token")
    public ResponseEntity<Optional<UserProfile>> findByToken(String token){
        return ResponseEntity.ok(userService.findByToken(token));
    }

    //add hotel to users favorite list
    @GetMapping("/add-favorite")
    public ResponseEntity<Boolean> addFavorite(String token, String hotelId){
        return ResponseEntity.ok(userService.addHotelToFavorites(token, hotelId));
    }

    //get favorite hotels
    @GetMapping("/get-favorites")
    public ResponseEntity<List<Hotel>> getFavoriteHotels(String token){
        return ResponseEntity.ok(userService.getFavoriteHotels(token));
    }

    @PostMapping("/update-email")
    public ResponseEntity<Boolean> updateEmail(String token, String email){
        return ResponseEntity.ok(userService.updateEmail(token, email));
    }

    @PostMapping("/update-phone")
    public ResponseEntity<Boolean> updatePhone(String token, String phone){
        return ResponseEntity.ok(userService.updatePhone(token, phone));
    }

    @PostMapping("/update-passport")
    public ResponseEntity<Boolean> updatePassport(String token, String passportNo, String passportExpiry){
        return ResponseEntity.ok(userService.updatePassport(token, passportNo, passportExpiry));
    }



}
