package com.yagmur.dto.request;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
    private String email;
    @Size(min = 8, max = 16)
    private String password;
    @Size(min = 10, max = 10, message = "Phone number must be 10 digits")
    private String phone;
}
