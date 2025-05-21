package com.openorderflow.business.controller;

import com.openorderflow.business.dto.BusinessUserProfileCreationDto;
import com.openorderflow.business.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/user")
@RequiredArgsConstructor
public class BusinessUserController {
    private final AuthService authService;

    @PostMapping()
    public ResponseEntity<Void> createBusinessUser(@RequestBody @Valid BusinessUserProfileCreationDto request) {
        try {
            authService.createNewBusinessUser(request);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("Error creating new business user: {}", ex.getMessage());
            return ResponseEntity.status(403).build(); // Unauthorized access
        }
    }
}
