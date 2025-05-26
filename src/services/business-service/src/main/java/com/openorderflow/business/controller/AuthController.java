package com.openorderflow.business.controller;

import com.openorderflow.business.dto.BusinessCreationRequestDto;
import com.openorderflow.business.dto.BusinessOwnerCreationRequestDto;
import com.openorderflow.business.dto.BusinessUserProfileCreationDto;
import com.openorderflow.business.dto.OtpVerifyResponse;
import com.openorderflow.business.service.AuthService;
import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/send-otp")
    public ResponseEntity<Void> sendOtp(@RequestBody @Valid PhoneLoginRequest request) {
        try {
            authService.getOtpForPhoneNumber(request);
            return ResponseEntity.ok().build();
        } catch (Exception ex) {
            log.error("Error sending OTP: {}", ex.getMessage());
            return ResponseEntity.badRequest().build(); // Customize with error details if needed
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<OtpVerifyResponse> verifyOtp(@RequestBody @Valid OtpVerifyRequest request) {
        try {
            OtpVerifyResponse response = authService.verifyOtp(request);
            return ResponseEntity.ok(response);
        } catch (Exception ex) {
            log.error("Error verifying OTP: {}", ex.getMessage());
            return ResponseEntity.status(401).build(); // 401 or 400 based on use case
        }
    }

    @PostMapping("/create-business")
    public Mono<ResponseEntity<Void>> createBusiness(@RequestBody @Valid BusinessCreationRequestDto request) {
        return authService.createBusinessUserProfileRequest(request)
                .thenReturn(ResponseEntity.ok().build());
    }


}
