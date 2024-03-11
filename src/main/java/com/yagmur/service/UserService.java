package com.yagmur.service;

import com.yagmur.entity.UserProfile;
import com.yagmur.repository.UserRepository;
import com.yagmur.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public Boolean createUser(UserProfile userProfile){
        userRepository.save(userProfile);
        return true;
    }

    public Boolean activation(String authId){
        Optional<UserProfile> userProfile = userRepository.findByAuthId(authId);
        if (userProfile.isEmpty()) {
            return false;
        }
        userProfile.get().setStatus(EStatus.ACTIVE);
        userRepository.save(userProfile.get());
        return true;
    }
}
