package com.yagmur.service;

import com.yagmur.entity.Auth;
import com.yagmur.entity.Hotel;
import com.yagmur.entity.UserProfile;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.repository.UserRepository;
import com.yagmur.utility.JwtTokenManager;
import com.yagmur.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final HotelService hotelService;
    private final JwtTokenManager jwtTokenManager;

    public Boolean createUser(UserProfile userProfile) {
        userRepository.save(userProfile);
        return true;
    }

    public Boolean activation(String authId) {
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId);
        if (userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        userProfile.get().setStatus(EStatus.ACTIVE);
        userRepository.save(userProfile.get());
        return true;
    }

    public Boolean delete(String authId) {
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId);
        if (userProfile.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        userProfile.get().setStatus(EStatus.DELETED);
        userRepository.save(userProfile.get());
        return true;
    }

    public Optional<UserProfile> findById(String id) {
        return userRepository.findById(id);
    }

    public List<UserProfile> getAll() {
        return userRepository.findAll();
    }

    //find-by-token
    public Optional<UserProfile> findByToken(String token){
        return userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get());
    }

    public boolean addHotelToFavorites(String token, String hotelId) {
        UserProfile userProfile = userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.getFavoriteHotelIds().add(hotelId);
        userRepository.save(userProfile);
        //hotel e bir puan gibi bir field ekleyip kullancı favorisine eklediğinde puan mı arttırsaqk favori hotelleri sıralamak için
        return true;
    }

    //favori otel listesi kullanıcın
    public List<Hotel> getFavoriteHotels(String token) {
        UserProfile userProfile = userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        List<String> favoriteHotelIds = userProfile.getFavoriteHotelIds();

        List<Hotel> favoriteHotels = new ArrayList<>();
        for (String hotelId : favoriteHotelIds) {
            favoriteHotels.add(hotelService.findById(hotelId));
        }
        return favoriteHotels;
    }

    //update passwprd email, phone, passport bilgileri

    public Boolean updateEmail(String token, String email) {
        UserProfile userProfile = userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setEmail(email);
        userRepository.save(userProfile);
        return true;
    }

    public Boolean updatePhone(String token, String phone) {
        UserProfile userProfile = userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setPhone(phone);
        userRepository.save(userProfile);
        return true;
    }

    public Boolean updatePassport(String token, String passportNo, String passportExpiry) {
        UserProfile userProfile = userRepository.findByAuthId(jwtTokenManager.getIdFromToken(token).get()).get();
        userProfile.setPassportNo(passportNo);
        userProfile.setPassportExpiry(passportExpiry);
        userRepository.save(userProfile);
        return true;
    }



}