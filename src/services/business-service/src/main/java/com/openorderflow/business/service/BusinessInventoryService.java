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


    public Mono<BusinessItemDto> addBusinessItem(AddBusinessItemRequest addBusinessItemRequest) {
        return Mono.justOrEmpty(CurrentUserContext.userId())
                .map(userId -> businessUserProfileRepository.findById(UUID.fromString(userId)).get())
                .switchIfEmpty(Mono.error(new UnauthorizedException("User not found")))
                .flatMap(profile -> {
                    if (profile.getRole() != BusinessUserProfile.BusinessAdminRoleEnum.BUSINESS_ADMIN &&
                            profile.getRole() != BusinessUserProfile.BusinessAdminRoleEnum.OWNER) {
                        return Mono.error(new UnauthorizedException("Business user cannot add items"));
                    }
                    return inventoryClient.createItem(addBusinessItemRequest)
                            .doOnSuccess(res -> log.info("Business item created."));
                });
    }

    public Mono<InventoryDto> createInventoryForOutlet(InventoryRequest request) {
        return inventoryClient.addInventory(request);
    }

    public Mono<List<ItemCategoryDto>> getItemCategories() {
        return inventoryClient.getItemCategories();
    }

    // for both saving and updating
    public Mono<Void> saveInventoryItem(InventoryItemDto dto) {
        if (dto.getId() == null)
            return inventoryClient.addInventoryItem(dto);
        return inventoryClient.updateInventoryItem(dto);
    }

}
