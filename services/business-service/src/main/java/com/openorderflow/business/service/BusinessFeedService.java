package com.openorderflow.business.service;

import com.openorderflow.business.dto.BusinessFeedDto;
import com.openorderflow.business.mapper.BusinessMapper;
import com.openorderflow.business.repository.BusinessOutletRepository;
import com.openorderflow.common.common.GeoLocation;
import com.openorderflow.common.dto.business.FeedRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessFeedService {

    private final BusinessOutletRepository businessOutletRepository;
    private final BusinessMapper businessMapper;
    private final Float RADIUS_KM = 10.0f;

    public List<BusinessFeedDto> getBusinessesInProximity(FeedRequestDto feedRequest) {
        var outlets = businessOutletRepository.findByCityAndState(feedRequest.getCity(), feedRequest.getState());
        return outlets.stream()
                .filter(outlet -> {
                    var outletLocation = new GeoLocation(outlet.getLatitude(), outlet.getLongitude());
                    float distance = getDistanceBetween(feedRequest.getLocation(), outletLocation);
                    return distance <= RADIUS_KM;
                }).map(businessMapper::toBusinessFeedDto)
                .toList();
    }

    private float getDistanceBetween(GeoLocation p1, GeoLocation p2) {
        final int R = 6371; // Earth radius in km

        double latDistance = Math.toRadians(p2.latitude().doubleValue() - p1.latitude().doubleValue());
        double lonDistance = Math.toRadians(p2.longitude().doubleValue() - p1.longitude().doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(p1.latitude().doubleValue())) * Math.cos(Math.toRadians(p2.latitude().doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c;

        return (float) distance;
    }

}
