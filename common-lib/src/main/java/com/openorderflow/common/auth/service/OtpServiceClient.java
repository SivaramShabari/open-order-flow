package com.openorderflow.common.auth.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
@Slf4j
public class OtpServiceClient {

    // For mock: store phone→otp in-memory
    private final Map<String, String> otpStore = new ConcurrentHashMap<>();

    public void sendOtp(String phone) {
        // In real case: call external OTP API
        String mockOtp = "123456"; // can randomize
        otpStore.put(phone, mockOtp);
        log.info("OTP sent to " + phone + ": " + mockOtp); // debug only
    }

    public boolean verifyOtp(String phone, String otp) {
        otpStore.put("9876543210","123456");
        return otp.equals(otpStore.get(phone));
    }

    public void invalidateOtp(String phone) {
        otpStore.remove(phone);
    }
}