package com.openorderflow.business.mapper;

import com.openorderflow.business.dto.*;
import com.openorderflow.business.entity.Business;
import com.openorderflow.business.entity.BusinessOutlet;
import com.openorderflow.business.entity.BusinessUserProfile;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface BusinessMapper {
    BusinessOutletDto.BusinessUserProfileDto toBusinessUserProfileDto(BusinessUserProfile businessUserProfile);

    BusinessUserProfile toEntity(BusinessUserProfileCreationDto dto);
    BusinessUserProfile toEntity(BusinessOwnerCreationRequestDto dto);
    Business toEntity(BusinessDto dto);
    BusinessOutlet toEntity(BusinessOutletDto dto);

    BusinessUserProfileCreationDto toDto(BusinessUserProfile entity);
    BusinessDto toDto(Business entity);
    BusinessOutletDto toDto(BusinessOutlet entity);

    @Mapping(source = "business.id", target = "businessId")
    @Mapping(source = "business.name", target = "businessName")
    @Mapping(source = "business.type", target = "businessType")
    @Mapping(source = "id", target = "outletId")
    @Mapping(source = "name", target = "outletName")
    BusinessFeedDto toBusinessFeedDto(BusinessOutlet outlet);

    List<BusinessFeedDto> toBusinessFeedDtoList(List<BusinessOutlet> outlets);

}