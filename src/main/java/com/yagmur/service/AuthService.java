package com.yagmur.service;

import com.yagmur.dto.request.AuthRegisterRequestDto;
import com.yagmur.entity.Auth;
import com.yagmur.entity.UserProfile;
import com.yagmur.mapper.AuthMapper;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final UserService userService;

    public String register(AuthRegisterRequestDto authRegisterRequestDto) {
        Auth auth = AuthMapper.INSTANCE.fromAuthRegisterRequestToAuth(authRegisterRequestDto);
        authRepository.save(auth);

        userService.createUser(UserProfile.builder()
                        .authId(auth.getId())
                        .email(auth.getEmail())
                        .phone(auth.getPhone())
                .build());
        return auth.getActivationCode();
    }

    public Boolean activateAccount(String activationCode, String id) {
        Optional<Auth> optionalAuth = authRepository.findByIdAndActivationCode(activationCode, id);
        if (optionalAuth.isEmpty()) {
            throw new RuntimeException("Activation code not found");
        }
        if(optionalAuth.get().getActivationCode().equals(activationCode)) {
            optionalAuth.get().setStatus(EStatus.ACTIVE);
            authRepository.save(optionalAuth.get());
            return true;
        }
        return false;
    }
}
