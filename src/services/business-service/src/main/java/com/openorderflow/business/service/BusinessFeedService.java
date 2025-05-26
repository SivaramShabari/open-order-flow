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
    private final Double RADIUS_KM = 10_000.0;

    public List<BusinessFeedDto> getBusinessesInProximity(FeedRequestDto feedRequest) {
        var outlets = businessOutletRepository.findByCityAndState(feedRequest.getCity(), feedRequest.getState());
        return outlets.stream()
                .filter(outlet -> {
                    var outletLocation = new GeoLocation(outlet.getLocation().getLatitude(), outlet.getLocation().getLatitude());
                    double distance = getDistanceBetween(feedRequest.getLocation(), outletLocation);
                    return distance <= RADIUS_KM;
                }).map(businessMapper::toBusinessFeedDto)
                .toList();
    }

    private double getDistanceBetween(GeoLocation p1, GeoLocation p2) {
        final double R = 6371.00; // Earth radius in km

        double latDistance = Math.toRadians(p2.getLatitude().doubleValue() - p1.getLatitude().doubleValue());
        double lonDistance = Math.toRadians(p2.getLongitude().doubleValue() - p1.getLongitude().doubleValue());

        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(p1.getLatitude().doubleValue())) * Math.cos(Math.toRadians(p2.getLatitude().doubleValue()))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

        return R * c;
    }

}
