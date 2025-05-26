package com.openorderflow.business.controller;

import com.openorderflow.business.service.BusinessOrderService;
import com.openorderflow.common.auth.exception.UnauthorizedException;
import com.openorderflow.common.dto.business.OrderQueueActionRequestDto;
import com.openorderflow.common.dto.business.OrderQueueResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/order")
public class BusinessOrderController {
    private final BusinessOrderService businessOrderService;

    @GetMapping("/pending")
    public ResponseEntity<List<OrderQueueResponseDto>> getPendingOrders(UUID businessOutletId) throws UnauthorizedException {
        try {
            var response = businessOrderService.getPendingOrders(businessOutletId);
            return ResponseEntity.ok(response);
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(403).build();
        }
    }

    @PutMapping
    public ResponseEntity<?> updateOrderStatus(@RequestBody @Valid OrderQueueActionRequestDto requestDto) throws Exception {
        try {
            businessOrderService.orderQueueAction(requestDto);
            return ResponseEntity.ok().build();
        } catch (UnauthorizedException ex) {
            return ResponseEntity.status(403).build();
        }
    }
}
