package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HotelCreateRequestDto {

    private String name;
    private String mainImageUrl;
    private String latitude;
    private String longitude;
}
