package com.yagmur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comments {
    @Id
    private String id;
    private String userId;
    private String hotelId;
    private String comment;
    private String commentDate;
}
