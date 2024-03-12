package com.yagmur.entity;

import com.yagmur.utility.CodeGenerator;
import com.yagmur.utility.enums.EStatus;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Auth implements Serializable {

    @Id
    private String id;
    @Indexed(unique = true)
    @Email
    private String email;
    private String password;
    private String phone;
    @Builder.Default
    private String activationCode= CodeGenerator.generateCode();
    @Builder.Default
    private EStatus status= EStatus.PENDING;

}
