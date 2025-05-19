package com.openorderflow.customer.service;

import com.openorderflow.common.auth.jwt.JwtUtils;
import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.common.auth.service.OtpServiceClient;
import com.openorderflow.customer.dto.CustomerProfileCreationRequest;
import com.openorderflow.customer.entity.CustomerAddress;
import com.openorderflow.customer.entity.CustomerProfile;
import com.openorderflow.customer.mapper.CustomerProfileMapper;
import com.openorderflow.customer.repository.CustomerProfileRepository;
import com.openorderflow.customer.dto.OtpVerifyResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountNotFoundException;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {
    private final OtpServiceClient otpServiceClient;
    private final CustomerProfileRepository customerProfileRepository;
    private final CustomerProfileMapper customerProfileMapper;
    private final JwtUtils jwtUtils;

    public void getOtpForPhoneNumber(PhoneLoginRequest phoneLoginRequest) throws AccountNotFoundException {
        validateUser(phoneLoginRequest.phone());
        otpServiceClient.sendOtp(phoneLoginRequest.phone());
    }

    public OtpVerifyResponse verifyOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException, AccountNotFoundException {
        validateUser(otpVerifyRequest.phone());
        validateOtp(otpVerifyRequest);

        var customerProfile = customerProfileRepository.getByPhone(otpVerifyRequest.phone());

        var jwtClaims = new HashMap<String, Object>();
        jwtClaims.put("userId", customerProfile.getId());
        jwtClaims.put("userName", customerProfile.getName());
        jwtClaims.put("email", customerProfile.getEmail());

        var jwtToken = jwtUtils.generateToken(otpVerifyRequest.phone(), jwtClaims, Duration.ofDays(7));

        return new OtpVerifyResponse(customerProfileMapper.toCustomerProfileDto(customerProfile), jwtToken);
    }

    public void createCustomerProfileRequest(CustomerProfileCreationRequest createCustomerProfileRequest) throws Exception {
        validateNewUser(createCustomerProfileRequest);
        var customerProfile = new CustomerProfile();

        customerProfile.setPhone(createCustomerProfileRequest.phone());
        customerProfile.setName((createCustomerProfileRequest.name()));
        customerProfile.setEmail((createCustomerProfileRequest.email()));

        var address = new CustomerAddress();
        address.setType("SIGN_UP_LOCATION");
        address.setGeoLatitude(createCustomerProfileRequest.location().latitude());
        address.setGeoLongitude(createCustomerProfileRequest.location().longitude());
        address.setProfile(customerProfile);

        var addresses = new ArrayList<CustomerAddress>();
        addresses.add(address);
        customerProfile.setAddresses(addresses);

        customerProfileRepository.save(customerProfile);

        try {
            getOtpForPhoneNumber(new PhoneLoginRequest(createCustomerProfileRequest.phone()));
        } catch (Exception ex) {
            log.error("Error creating new user. Message" + ex.getMessage());
            throw ex;
        }
    }

    private void validateUser(String phone) throws AccountNotFoundException {
        var isExistingUser = customerProfileRepository.existsByPhone(phone);
        if (!isExistingUser)
            throw new AccountNotFoundException("Customer phone number not registered");
    }

    private void validateNewUser(CustomerProfileCreationRequest createCustomerProfileRequest) throws Exception {
        var isExistingUser = customerProfileRepository.existsByPhone(createCustomerProfileRequest.phone());
        isExistingUser = isExistingUser || customerProfileRepository.existsByEmail(createCustomerProfileRequest.email());
        if (isExistingUser)
            throw new Exception("Customer already registered");
    }

    private void validateOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException {
        var isValidOtp = otpServiceClient.verifyOtp(otpVerifyRequest.phone(), otpVerifyRequest.otp());
        if (!isValidOtp)
            throw new InvalidKeyException("Invalid OTP");
    }


}
