package com.yagmur.entity;

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
    private String auth_id;
    private String address_id;
    private String nameSurname;
    private String phone;
    private String passportNo;
    private String passportExpiry;
    private String trIdNo;
    private String couponCode;

    private List<Reservation> reservationList;
    //private List<Hotel> favoriteHotels; -> favourite kısmını nasıl tutalım?
    // private List<String>registeredPerson kısmını nasıl yapalım?



}
