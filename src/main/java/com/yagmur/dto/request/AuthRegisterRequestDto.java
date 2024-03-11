package com.yagmur.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthRegisterRequestDto {
    @Email
    @NotNull
    private String email;
    @NotNull
    private String password;
    @NotNull
    private String phone;
}
