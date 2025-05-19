package com.openorderflow.business.service;

import com.openorderflow.business.dto.BusinessCreationRequest;
import com.openorderflow.business.dto.BusinessUserCreationRequest;
import com.openorderflow.business.dto.OtpVerifyResponse;
import com.openorderflow.business.entity.Business;
import com.openorderflow.business.entity.BusinessOutlet;
import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.business.mapper.BusinessUserProfileMapper;
import com.openorderflow.business.repository.BusinessOutletRepository;
import com.openorderflow.business.repository.BusinessRepository;
import com.openorderflow.business.repository.BusinessUserProfileRepository;
import com.openorderflow.common.auth.jwt.JwtUtils;
import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.common.auth.service.OtpServiceClient;
import com.openorderflow.common.auth.util.CurrentUserContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationException;
import javax.naming.NameNotFoundException;
import javax.security.auth.login.AccountNotFoundException;
import java.security.InvalidKeyException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthService {
    private final BusinessUserProfileRepository businessUserProfileRepository;
    private final BusinessRepository businessRepository;
    private final BusinessOutletRepository businessOutletRepository;
    private final OtpServiceClient otpServiceClient;
    private final BusinessUserProfileMapper businessUserProfileMapper;
    private final JwtUtils jwtUtils;

    public void getOtpForPhoneNumber(PhoneLoginRequest phoneLoginRequest) throws AccountNotFoundException {
        validateUser(phoneLoginRequest.phone());
        otpServiceClient.sendOtp(phoneLoginRequest.phone());
    }

    public OtpVerifyResponse verifyOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException, AccountNotFoundException {
        validateUser(otpVerifyRequest.phone());
        validateOtp(otpVerifyRequest);

        var businessUserProfile = businessUserProfileRepository.getByPhone(otpVerifyRequest.phone());

        var jwtClaims = new HashMap<String, Object>();
        jwtClaims.put("userId", businessUserProfile.getId());
        jwtClaims.put("userName", businessUserProfile.getName());
        jwtClaims.put("email", businessUserProfile.getEmail());

        var jwtToken = jwtUtils.generateToken(otpVerifyRequest.phone(), jwtClaims, Duration.ofDays(7));

        return new OtpVerifyResponse(businessUserProfileMapper.toBusinessUserProfileDto(businessUserProfile), jwtToken);
    }

    public void createBusinessUserProfileRequest(BusinessCreationRequest businessCreationRequest) throws Exception {
        validateNewBusiness(businessCreationRequest);
        var businessOwner = new BusinessUserProfile().toBuilder()
                .name(businessCreationRequest.getOwnerName())
                .email(businessCreationRequest.getOwnerEmail())
                .phone(businessCreationRequest.getOwnerPhone())
                .role(BusinessUserProfile.BusinessAdminRoleEnum.OWNER)
                .build();

        var createdProfile = businessUserProfileRepository.save(businessOwner);

        var business = new Business().toBuilder()
                .createdBy(createdProfile)
                .updatedBy(createdProfile)
                .name(businessCreationRequest.getBusinessName())
                .type(businessCreationRequest.getBusinessType())
                .build();
        var createdBusiness = businessRepository.save(business);

        var businessOutlet = new BusinessOutlet().toBuilder()
                .name(businessCreationRequest.getBusinessOutletName())
                .business(createdBusiness)
                .city(businessCreationRequest.getBusinessOutletCity())
                .postalCode(businessCreationRequest.getBusinessOutletPostalCode())
                .addressLine1(businessCreationRequest.getBusinessOutletAddressLine1())
                .addressLine2(businessCreationRequest.getBusinessOutletAddressLine2())
                .addressLine3(businessCreationRequest.getBusinessOutletAddressLine3())
                .latitude(businessCreationRequest.getBusinessOutletLatitude())
                .longitude(businessCreationRequest.getBusinessOutletLongitude())
                .build();

        businessOutletRepository.save(businessOutlet);

        try {
            getOtpForPhoneNumber(new PhoneLoginRequest(createdProfile.getPhone()));
        } catch (Exception ex) {
            log.error("Error creating new user. Message" + ex.getMessage());
            throw ex;
        }
    }

    public void createNewBusinessUser(BusinessUserCreationRequest businessUserCreationRequest) throws AuthenticationException, NameNotFoundException {
        var currentUser = businessUserProfileRepository.findById(UUID.fromString(CurrentUserContext.userId()));
        var businessOutlet = businessOutletRepository.findById(businessUserCreationRequest.getBusinessOutletId());

        if(currentUser.isEmpty() || businessOutlet.isEmpty())
            throw new NameNotFoundException("Invalid user or business outlet Id");

        if (currentUser.get().getBusiness().getId() != businessOutlet.get().getBusiness().getId())
            throw new AuthenticationException("Unauthorized Business outlet");

        var allowedRoles = new ArrayList<BusinessUserProfile.BusinessAdminRoleEnum>();
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.OWNER);
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.BUSINESS_ADMIN);
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.OUTLET_ADMIN);

        if(!allowedRoles.contains(currentUser.get().getRole()))
            throw new AuthenticationException("Not authorized exception");

        var newBusinessUser = new BusinessUserProfile().toBuilder()
                .businessOutlet(businessOutlet.get())
                .business(currentUser.get().getBusiness())
                .phone(businessUserCreationRequest.getPhone())
                .email(businessUserCreationRequest.getEmail())
                .role(businessUserCreationRequest.getRole())
                .build();

        businessUserProfileRepository.save(newBusinessUser);
    }

    private void validateUser(String phone) throws AccountNotFoundException {
        var isExistingUser = businessUserProfileRepository.existsByPhone(phone);
        if (!isExistingUser)
            throw new AccountNotFoundException("Customer phone number not registered");
    }

    private void validateOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException {
        var isValidOtp = otpServiceClient.verifyOtp(otpVerifyRequest.phone(), otpVerifyRequest.otp());
        if (!isValidOtp)
            throw new InvalidKeyException("Invalid OTP");
    }

    private void validateNewBusiness(BusinessCreationRequest businessCreationRequest) throws Exception {
        return;
    }
}
