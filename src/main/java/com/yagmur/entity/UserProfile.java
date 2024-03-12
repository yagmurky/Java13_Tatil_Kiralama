package com.yagmur.entity;

import com.yagmur.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserProfile implements Serializable {

    @Id
    private String id;
    private String authId;
    private String addressId;
    private String email;
    private String nameSurname;
    private String phone;
    private String passportNo;
    private String passportExpiry;
    private String trIdNo;
    private String couponCode;

    @Builder.Default
    private List<Reservation> reservationList= new ArrayList<>();
    @Builder.Default
    private List<String> favoriteHotelIds= new ArrayList<>();
    //private List<String> registeredPersons;
    @Builder.Default
    private EStatus status= EStatus.PENDING;



}
