package com.openorderflow.customer.controller;

import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.common.auth.model.PhoneLoginResponse;
import com.openorderflow.customer.dto.OtpVerifyResponse;
import com.openorderflow.customer.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.InvalidKeyException;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(path = "/phoneLogin")
    public ResponseEntity<PhoneLoginResponse> phoneLogin(@Valid PhoneLoginRequest phoneLoginRequest) {
        var response = authService.getOtpForPhoneNumber(phoneLoginRequest);
        log.info(response.toString());
        return ResponseEntity.ok(response);
    }

    @GetMapping(path = "/verifyOtp")
    public ResponseEntity<?> phoneLogin(@Valid OtpVerifyRequest otpVerifyRequest) {
        try {
            var response = authService.verifyOtp(otpVerifyRequest);
            log.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (InvalidKeyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
