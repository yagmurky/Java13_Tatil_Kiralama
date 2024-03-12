package com.yagmur.mapper;

import com.yagmur.dto.request.FacilityFeaturesCreateRequestDto;
import com.yagmur.dto.request.HotelCreateRequestDto;
import com.yagmur.dto.request.ImageCreateRequestDto;
import com.yagmur.dto.request.RoomRequestDto;
import com.yagmur.entity.FacilityFeatures;
import com.yagmur.entity.Hotel;
import com.yagmur.entity.Images;
import com.yagmur.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface HotelMapper {
    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);
    Hotel fromHotelCreateRequestToHotel(final HotelCreateRequestDto dto);
    FacilityFeatures fromFacilityFeaturesCreateRequestToFacilityFeatures(final FacilityFeaturesCreateRequestDto dto);
    Images fromImageCreateRequestToImages(final ImageCreateRequestDto dto);

    Room fromRoomRequestToRoom(RoomRequestDto dto);
}
