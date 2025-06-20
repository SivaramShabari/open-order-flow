package com.openorderflow.business.service;

import com.openorderflow.business.dto.BusinessCreationRequestDto;
import com.openorderflow.business.dto.BusinessUserProfileCreationDto;
import com.openorderflow.business.dto.OtpVerifyResponse;
import com.openorderflow.business.entity.Business;
import com.openorderflow.business.entity.BusinessOutlet;
import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.business.mapper.BusinessMapper;
import com.openorderflow.business.repository.BusinessOutletRepository;
import com.openorderflow.business.repository.BusinessRepository;
import com.openorderflow.business.repository.BusinessUserProfileRepository;
import com.openorderflow.common.auth.jwt.JwtUtils;
import com.openorderflow.common.auth.model.OtpVerifyRequest;
import com.openorderflow.common.auth.model.PhoneLoginRequest;
import com.openorderflow.common.auth.service.OtpServiceClient;
import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.inventory.InventoryRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

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
    private final JwtUtils jwtUtils;
    private final BusinessMapper businessMapper;
    private final BusinessInventoryService inventoryService;

    public void getOtpForPhoneNumber(PhoneLoginRequest phoneLoginRequest) throws AccountNotFoundException {
        validateUser(phoneLoginRequest.phone());
        otpServiceClient.sendOtp(phoneLoginRequest.phone());
    }

    public OtpVerifyResponse verifyOtp(OtpVerifyRequest otpVerifyRequest) throws InvalidKeyException, AccountNotFoundException {
        validateUser(otpVerifyRequest.phone());
        validateOtp(otpVerifyRequest);

        var businessUserProfile = businessUserProfileRepository.findByPhone(otpVerifyRequest.phone());

        var jwtClaims = new HashMap<String, Object>();
        jwtClaims.put("userId", businessUserProfile.getId());
        jwtClaims.put("userName", businessUserProfile.getName());
        jwtClaims.put("email", businessUserProfile.getEmail());

        var jwtToken = jwtUtils.generateToken(otpVerifyRequest.phone(), jwtClaims, Duration.ofDays(7));

        return new OtpVerifyResponse(businessMapper.toBusinessUserProfileDto(businessUserProfile), jwtToken);
    }

    public void createBusinessUserProfileRequest(BusinessCreationRequestDto dto) {
       var wrapper = createNewBusinessEntities(dto);
       var inventoryRequest = getInventoryRequest(wrapper);
       inventoryService.createInventoryForOutlet(inventoryRequest);
       sendOtpForNewUser(wrapper.profile().getPhone());
    }

    // <editor-fold desc="Business Creation Logic">
    private InventoryRequestWrapper createNewBusinessEntities(BusinessCreationRequestDto dto)  {
        validateNewBusiness(dto);



        var business = businessMapper.toEntity(dto.getBusiness())
                .toBuilder()
                .build();

        var createdBusiness = businessRepository.save(business);

        var businessOutlet = businessMapper.toEntity(dto.getOutlet())
                .toBuilder()
                .business(createdBusiness)
                .build();

        var createdOutlet = businessOutletRepository.save(businessOutlet);

        var businessOwner = businessMapper.toEntity(dto.getOwner())
                .toBuilder()
                .role(BusinessUserProfile.BusinessAdminRoleEnum.OWNER)
                .business(createdBusiness)
                .businessOutlet(createdOutlet)
                .build();
        var createdProfile = businessUserProfileRepository.save(businessOwner);

        return new InventoryRequestWrapper(createdProfile, createdBusiness, createdOutlet);
    }

    private void validateNewBusiness(BusinessCreationRequestDto businessCreationRequestDto) {
        return;
    }

    private Mono<Void> sendOtpForNewUser(String phone) {
        return Mono.fromRunnable(() -> {
            try {
                getOtpForPhoneNumber(new PhoneLoginRequest(phone));
            } catch (Exception ex) {
                log.error("Error while sending otp", ex);
                throw new RuntimeException("Failed to send OTP", ex);
            }
        });
    }

    private InventoryRequest getInventoryRequest(InventoryRequestWrapper wrapper) {
        return InventoryRequest.builder()
                .businessOutletId(wrapper.outlet().getId())
                .businessId(wrapper.business.getId())
                .name(wrapper.outlet().getName() + " - Inventory")
                .locationName("DEFAULT_LOCATION")
                .inventoryType("DEFAULT")
                .createdBy(wrapper.profile().getId())
                .build();
    }

    public record InventoryRequestWrapper(
            BusinessUserProfile profile,
            Business business,
            BusinessOutlet outlet
    ) {
    }
    // </editor-fold>

    public void createNewBusinessUser(BusinessUserProfileCreationDto businessUserProfileCreationDto) throws AuthenticationException, NameNotFoundException {
        var currentUser = businessUserProfileRepository.findById(UUID.fromString(CurrentUserContext.userId()));
        var businessOutlet = businessOutletRepository.findById(businessUserProfileCreationDto.getBusinessOutletId());

        if (currentUser.isEmpty() || businessOutlet.isEmpty())
            throw new NameNotFoundException("Invalid user or business outlet Id");

        if (currentUser.get().getBusiness().getId() != businessOutlet.get().getBusiness().getId())
            throw new AuthenticationException("Unauthorized Business outlet");

        var allowedRoles = new ArrayList<BusinessUserProfile.BusinessAdminRoleEnum>();
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.OWNER);
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.BUSINESS_ADMIN);
        allowedRoles.add(BusinessUserProfile.BusinessAdminRoleEnum.OUTLET_ADMIN);

        if (!allowedRoles.contains(currentUser.get().getRole()))
            throw new AuthenticationException("Not authorized exception");

        var newBusinessUser = new BusinessUserProfile().toBuilder()
                .businessOutlet(businessOutlet.get())
                .business(currentUser.get().getBusiness())
                .phone(businessUserProfileCreationDto.getPhone())
                .email(businessUserProfileCreationDto.getEmail())
                .role(businessUserProfileCreationDto.getRole())
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

}
