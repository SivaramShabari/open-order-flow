package com.openorderflow.common.dto.business;

import com.openorderflow.common.common.GeoLocation;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class FeedRequestDto {

    @NotNull
    private GeoLocation location;

    @NotNull
    private String city;

    @NotNull
    private String state;
}
