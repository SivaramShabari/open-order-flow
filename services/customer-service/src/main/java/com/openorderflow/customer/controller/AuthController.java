package com.openorderflow.customer.controller;

import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.customer.dto.CreateCustomerProfileRequest;
import com.openorderflow.customer.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountNotFoundException;
import java.security.InvalidKeyException;

@RestController
@Slf4j
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @GetMapping(path = "/phoneLogin")
    public ResponseEntity<?> phoneLogin(@Valid PhoneLoginRequest phoneLoginRequest) {
        try {
            authService.getOtpForPhoneNumber(phoneLoginRequest);
        } catch (AccountNotFoundException ex) {
            log.error("Invalid phone number provided for OTP request. Phone number: {}", phoneLoginRequest.phone());
            ResponseEntity.badRequest().body(ex.getMessage());
        }
        return ResponseEntity.ok("OTP sent to " + phoneLoginRequest.phone());
    }

    @GetMapping(path = "/verifyOtp")
    public ResponseEntity<?> phoneLogin(@Valid OtpVerifyRequest otpVerifyRequest) {
        try {
            var response = authService.verifyOtp(otpVerifyRequest);
            log.info(response.toString());
            return ResponseEntity.status(HttpStatus.OK).body(response);
        } catch (InvalidKeyException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
            ResponseEntity.badRequest().body(ex.getMessage());
        } catch (AccountNotFoundException ex) {
            log.error("Invalid phone number provided for OTP verification. Phone number: {}", otpVerifyRequest.phone());
            ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @PostMapping(path = "/signUp")
    public ResponseEntity<?> signUpAndSendOtp(@Valid CreateCustomerProfileRequest createCustomerProfileRequest) {

    }
}
