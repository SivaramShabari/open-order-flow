package com.openorderflow.customer.service;

import com.openorderflow.common.auth.jwt.JwtUtils;
import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.common.auth.service.OtpServiceClient;
import com.openorderflow.customer.repository.CustomerProfileRepository;
import com.openorderflow.common.auth.model.PhoneLoginResponse;
import com.openorderflow.customer.dto.OtpVerifyResponse;
import com.openorderflow.customer.entity.CustomerProfile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final OtpServiceClient otpServiceClient;
    private final CustomerProfileRepository customerProfileRepository;
    private final JwtUtils jwtUtils;

    public PhoneLoginResponse getOtpForPhoneNumber(PhoneLoginRequest phoneLoginRequest) {
        var isExistingUser = customerProfileRepository.existsByPrimaryPhoneNumber(phoneLoginRequest.phone());
        var isOtpSent = true;
        try {
            otpServiceClient.sendOtp(phoneLoginRequest.phone());
        } catch (Exception ex) {
            isOtpSent = false;
        }
        return new PhoneLoginResponse(isOtpSent, !isExistingUser, phoneLoginRequest.phone());
    }

    public OtpVerifyResponse verifyOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException {
        var isValidOtp = otpServiceClient.verifyOtp(otpVerifyRequest.phone(), otpVerifyRequest.otp());
        if (!isValidOtp)
            throw new InvalidKeyException("Invalid OTP");
        var customerProfile = customerProfileRepository.getByPrimaryPhoneNumber(otpVerifyRequest.phone());
        if (customerProfile == null) {
            var claims = new HashMap<String, Object>();
            claims.put("userId", "NEW_USER");
            claims.put("userName", "NEW_USER");
            var jwtToken = jwtUtils.generateToken(otpVerifyRequest.phone(), claims, Duration.ofDays(7));
            return new OtpVerifyResponse(true, null, jwtToken);
        }
        var claims = new HashMap<String, Object>();
        var jwtToken = jwtUtils.generateToken(otpVerifyRequest.phone(), claims, Duration.ofDays(7));
        claims.put("userId", customerProfile.getId());
        claims.put("userName", customerProfile.getName());
        return new OtpVerifyResponse(false, customerProfile, jwtToken);
    }
}
