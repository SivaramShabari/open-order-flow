package com.openorderflow.customer.mapper;

import com.openorderflow.common.dto.customer.CustomerProfileDto;
import com.openorderflow.customer.entity.CustomerAddress;
import com.openorderflow.customer.entity.CustomerProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CustomerProfileMapper {
    CustomerProfileDto toCustomerProfileDto(CustomerProfile customerProfile);
}
