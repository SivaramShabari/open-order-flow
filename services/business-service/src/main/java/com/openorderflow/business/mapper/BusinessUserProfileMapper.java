package com.openorderflow.business.mapper;

import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.common.dto.business.BusinessUserProfileDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BusinessUserProfileMapper {
    BusinessUserProfileDto toBusinessUserProfileDto(BusinessUserProfile businessUserProfile);
}
