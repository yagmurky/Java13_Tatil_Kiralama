package com.yagmur.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GetCommentResponseDto {
    private String userId;
    private String hotelId;
    private String comment;
    private Double point;
    private String commentDate;
}
