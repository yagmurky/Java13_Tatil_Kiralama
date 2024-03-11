package com.yagmur.entity;

import com.yagmur.utility.enums.EStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class UserProfile {

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

    private List<Reservation> reservationList;
    private List<String> favoriteHotelIds;
    //private List<String> registeredPersons;

    @Builder.Default
    private EStatus status= EStatus.PENDING;



}
