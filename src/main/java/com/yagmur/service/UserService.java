package com.yagmur.service;

import com.yagmur.entity.UserProfile;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.repository.UserRepository;
import com.yagmur.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

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
}