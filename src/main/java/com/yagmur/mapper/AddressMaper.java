package com.yagmur.mapper;

import com.yagmur.dto.request.HotelAddressCreateRequestDto;
import com.yagmur.entity.Address;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AddressMaper {
    AddressMaper INSTANCE = Mappers.getMapper(AddressMaper.class);
    Address fromHotelAddressCreateRequestToAddress(HotelAddressCreateRequestDto dto);
}
