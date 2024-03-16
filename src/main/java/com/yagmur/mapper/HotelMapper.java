package com.yagmur.mapper;

import com.yagmur.dto.request.*;
import com.yagmur.dto.response.GetCommentResponseDto;
import com.yagmur.entity.*;
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

    Category fromRequestDtoToCategory(CategoryRequestDto categoryRequestDto);

    HotelCategory fromRequestDtoToHotelCategory(HotelCategoryRequestDto hotelCategoryRequestDto);
    Reservation fromRequestDtoToReservation(ReservationRequestDto reservationRequestDto);
    Payment fromRequestDtoToPayment(PaymentRequestDto paymentRequestDto);
    Comments fromRequestDtoToComments(CommentRequestDto commentsRequestDto);
    GetCommentResponseDto fromCommentsToGetCommentResponseDto(Comments comments);

}
