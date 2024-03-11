package com.yagmur.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Document
public class Room {

    private String id;
    private String hotelId;
    private String name;
    private String description;
    private Double price;

    private List<String> imageId;
}
