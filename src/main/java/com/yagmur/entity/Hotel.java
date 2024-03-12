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
public class Hotel {
    @Id
    private String id;
    private String name;
    private String mainImageUrl;
    //image id list null olduğu için hata alıyor ama veritabanına işliyor
    private List<String> imageIdList;
    private String addressId;
    private String latitude;
    private String longitude;
}
