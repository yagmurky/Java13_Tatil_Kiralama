package com.yagmur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Reservation implements Serializable {
    @Id
    private String id;
    private String paymentId;
    private String hotelId;
    private String roomId;
    private String authId;
    private String startDate;
    private String endDate;
    private Integer numberOfPeople;
    private Double totalPrice;
    //rezervasyonda kullanılan kupon kodunu belirtmeli miyiz?
}
