package com.openorderflow.business.controller;

import com.openorderflow.business.service.BusinessFeedService;
import com.openorderflow.common.dto.business.FeedRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class BusinessFeedController {
    private final BusinessFeedService feedService;

    @GetMapping
    public ResponseEntity<?> getFeedForUser(@Valid FeedRequestDto feedRequest){
        var businesses = feedService.getBusinessesInProximity(feedRequest);
        return ResponseEntity.ok(businesses);
    }
}
