package com.yagmur.mapper;

import com.yagmur.dto.request.AuthRegisterRequestDto;
import com.yagmur.entity.Auth;
import com.yagmur.entity.UserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)public interface
AuthMapper {

    AuthMapper INSTANCE = Mappers.getMapper(AuthMapper.class);

    Auth fromAuthRegisterRequestToAuth(final AuthRegisterRequestDto dto);

}
