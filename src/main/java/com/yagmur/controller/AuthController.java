package com.yagmur.controller;

import com.yagmur.dto.request.AuthRegisterRequestDto;
import com.yagmur.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "/register")
    public ResponseEntity<String> register(@RequestBody AuthRegisterRequestDto authRegisterRequestDto) {
        return ResponseEntity.ok(authService.register(authRegisterRequestDto));
    }

    @PostMapping(value = "/activate")
    public ResponseEntity<Boolean> activate(String code, String id) {
        return ResponseEntity.ok(authService.activateAccount(code, id));
    }

}
