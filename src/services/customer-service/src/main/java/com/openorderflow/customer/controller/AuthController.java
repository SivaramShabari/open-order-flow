package com.openorderflow.customer.controller;

import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.customer.dto.CustomerProfileCreationRequest;
import com.openorderflow.customer.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import java.security.InvalidKeyException;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(path = "/phone-login")
    public ResponseEntity<?> phoneLogin(@Valid PhoneLoginRequest phoneLoginRequest) {
        try {
            authService.getOtpForPhoneNumber(phoneLoginRequest);
        } catch (AccountNotFoundException ex) {
            log.error("Invalid phone number provided for OTP request. Phone number: {}", phoneLoginRequest.phone());
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("OTP sent to " + phoneLoginRequest.phone());
    }

    @GetMapping(path = "/verify-otp")
    public ResponseEntity<?> phoneLogin(@Valid OtpVerifyRequest otpVerifyRequest) {
        try {
            var response = authService.verifyOtp(otpVerifyRequest);
            log.info(response.toString());
            return ResponseEntity.ok(response);
        } catch (InvalidKeyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        } catch (AccountNotFoundException ex) {
            log.error("Invalid phone number provided for OTP verification. Phone number: {}", otpVerifyRequest.phone());
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(path = "/sign-up")
    public ResponseEntity<?> signUpAndSendOtp(@Valid @RequestBody CustomerProfileCreationRequest createCustomerProfileRequest) throws Exception {
        authService.createCustomerProfileRequest(createCustomerProfileRequest);
        return ResponseEntity.ok("OTP Sent");
    }
}
