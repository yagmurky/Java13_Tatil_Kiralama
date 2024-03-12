package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelAddressCreateRequestDto {
    private String hotelId;
    private String address;
    private String district;
    private String city;
    private String country;
}
