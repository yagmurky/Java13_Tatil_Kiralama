package com.yagmur.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationRequestDto {
    private String hotelId;
    private String roomId;
    private String token;
    private String startDate;
    private String endDate;
    private Integer numberOfPeople;
}
