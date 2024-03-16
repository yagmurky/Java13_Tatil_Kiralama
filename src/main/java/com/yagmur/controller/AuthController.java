package com.yagmur.controller;

import com.yagmur.dto.request.AuthActivationRequestDto;
import com.yagmur.dto.request.AuthLoginRequestDto;
import com.yagmur.dto.request.AuthRegisterRequestDto;
import com.yagmur.entity.Auth;
import com.yagmur.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    @CrossOrigin("*")
    public ResponseEntity<String> register(@RequestBody @Validated AuthRegisterRequestDto authRegisterRequestDto) {
        return ResponseEntity.ok(authService.register(authRegisterRequestDto));
    }

    @GetMapping ("/account-activation")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> activation(AuthActivationRequestDto dto) {
        return ResponseEntity.ok(authService.activation(dto));
    }

    @PostMapping("/login")
    @CrossOrigin("*")
    public ResponseEntity<String> login(@RequestBody @Valid AuthLoginRequestDto dto) {
        return ResponseEntity.ok(authService.login(dto));
    }

    @DeleteMapping("/delete-account")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> delete(String token) {
        return ResponseEntity.ok(authService.delete(token));
    }

    @GetMapping("/find-auth-by-id")
    public ResponseEntity<Auth> getById(String id) {
        return ResponseEntity.ok(authService.findById(id).get());
    }

    @GetMapping("/get-all")
    public ResponseEntity<List<Auth>> getAll() {
        return ResponseEntity.ok(authService.getAll());
    }

    @PutMapping("/update-phone")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updatePhone(String token, String phone) {
        return ResponseEntity.ok(authService.updatePhone(token, phone));
    }

    @PutMapping("/update-email")
    @CrossOrigin("*")
    public ResponseEntity<Boolean> updateEmail(String token, String email) {
        return ResponseEntity.ok(authService.updateEmail(token, email));
    }


}