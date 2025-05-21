package com.openorderflow.business.controller;

import com.openorderflow.business.service.BusinessFeedService;
import com.openorderflow.common.dto.business.FeedRequestDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/feed")
public class BusinessFeedController {
    private final BusinessFeedService feedService;

    @PostMapping
    public ResponseEntity<?> getFeedForUser(@Valid @RequestBody FeedRequestDto feedRequest){
        var businesses = feedService.getBusinessesInProximity(feedRequest);
        return ResponseEntity.ok(businesses);
    }
}
