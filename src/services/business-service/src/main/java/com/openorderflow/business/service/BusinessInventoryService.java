package com.openorderflow.business.service;

import com.openorderflow.business.entity.BusinessUserProfile;
import com.openorderflow.business.repository.BusinessUserProfileRepository;
import com.openorderflow.common.auth.exception.UnauthorizedException;
import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.inventory.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class BusinessInventoryService {

    private final InventoryClient inventoryClient;
    private final BusinessUserProfileRepository businessUserProfileRepository;

    public BusinessItemDto addBusinessItem(AddBusinessItemRequest addBusinessItemRequest) throws UnauthorizedException {
        var userId = UUID.fromString(CurrentUserContext.userId());
        var profile = businessUserProfileRepository.findById(userId).get();

        if (businessUserProfileRepository.findById(UUID.fromString(CurrentUserContext.userId())).isEmpty())
            throw new UnauthorizedException("User not found");

        if (profile.getRole() != BusinessUserProfile.BusinessAdminRoleEnum.BUSINESS_ADMIN &&
                profile.getRole() != BusinessUserProfile.BusinessAdminRoleEnum.OWNER) {
            throw new UnauthorizedException("Business user cannot add items");
        }
        addBusinessItemRequest.setBusinessId(profile.getBusiness().getId());
        return inventoryClient.createItem(addBusinessItemRequest);
    }

    public InventoryDto createInventoryForOutlet(InventoryRequest request) {
        return inventoryClient.addInventory(request);
    }

    public List<ItemCategoryDto> getItemCategories() {
        return inventoryClient.getItemCategories();
    }

    // for both saving and updating
    public void saveInventoryItem(InventoryItemDto dto) {
        if (dto.getId() == null)
            inventoryClient.addInventoryItem(dto);
        else
            inventoryClient.updateInventoryItem(dto);
    }

    public BusinessItemDto updateBusinessItem(BusinessItemDto dto) {
        return inventoryClient.updateBusinessItem(dto);
    }

    public List<BusinessItemDto> getBusinessItems(UUID businessId) {
        return inventoryClient.getBusinessItems(businessId);
    }
}
