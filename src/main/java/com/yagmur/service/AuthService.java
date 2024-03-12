package com.yagmur.service;

import com.yagmur.dto.request.AuthActivationRequestDto;
import com.yagmur.dto.request.AuthLoginRequestDto;
import com.yagmur.dto.request.AuthRegisterRequestDto;
import com.yagmur.entity.Auth;
import com.yagmur.entity.UserProfile;
import com.yagmur.exception.ErrorType;
import com.yagmur.exception.HolidayException;
import com.yagmur.mapper.AuthMapper;
import com.yagmur.repository.AuthRepository;
import com.yagmur.utility.JwtTokenManager;
import com.yagmur.utility.enums.EStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final AuthRepository authRepository;
    private final AuthMapper authMapper;
    private final UserService userProfileService;
    private final JwtTokenManager jwtTokenManager;
    private final MailSenderService mailService;
    private final CacheManager cacheManager;

    public String register(AuthRegisterRequestDto dto){

        Optional<Auth> authRepositoryByEmail = authRepository.findByEmail(dto.getEmail());
        if (authRepositoryByEmail.isPresent()) {
            throw new HolidayException(ErrorType.USER_ALREADY_EXISTS);
        }
        Auth auth = authRepository.save(authMapper.fromAuthRegisterRequestToAuth(dto));
        mailService.sendMail(auth.getActivationCode());
        userProfileService.createUser(UserProfile.builder()
                .authId(auth.getId())
                .email(auth.getEmail())
                .phone(auth.getPhone())
                .build());
        Objects.requireNonNull(cacheManager.getCache("find-all-auth")).clear();

        return jwtTokenManager.createToken(auth.getId()).get();
    }

    public Boolean activation(AuthActivationRequestDto dto){
        Optional<Auth> auth = authRepository.findByIdAndActivationCode(jwtTokenManager.getIdFromToken(dto.getToken()).get(),dto.getActivationCode());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        if (auth.get().getActivationCode().equals(dto.getActivationCode())) {
            auth.get().setStatus(EStatus.ACTIVE);
            authRepository.save(auth.get());
            userProfileService.activation(auth.get().getId());
            return true;
        }
        return false;
    }

    public String login(AuthLoginRequestDto dto){
        Optional<Auth> auth = authRepository.findByEmailAndPassword(dto.getEmail(),dto.getPassword());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.LOGIN_FAILED);
        if (!auth.get().getStatus().equals(EStatus.ACTIVE))
            throw new HolidayException(ErrorType.ACCOUNT_NOT_ACTIVATED);
        return jwtTokenManager.createToken(auth.get().getId()).get();
    }

    public Boolean delete(String token){
        Optional<Auth> auth = authRepository.findById(jwtTokenManager.getIdFromToken(token).get());
        if(auth.isEmpty())
            throw new HolidayException(ErrorType.USER_NOT_FOUND);

        auth.get().setStatus(EStatus.DELETED);
        authRepository.save(auth.get());
        userProfileService.delete(auth.get().getId());
        return true;
    }

    public Optional<Auth> findById(String id){
        return authRepository.findById(id);
    }

    @Cacheable("find-all-auth")
    public List<Auth> getAll(){
        return authRepository.findAll();
    }


}
