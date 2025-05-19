package com.openorderflow.customer.controller;
import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.order.CustomerOrderCreationRequest;
import com.openorderflow.customer.service.UserOrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class CustomerOrderController {
    private final UserOrderService userOrderService;

    @GetMapping("/user-check")
    public ResponseEntity<?> test(){
        return ResponseEntity.ok(CurrentUserContext.get());
    }

    @PostMapping("/order-creation-request")
    public ResponseEntity<?> placeOrder(@Valid @RequestBody CustomerOrderCreationRequest request) throws AccountNotFoundException {
        userOrderService.requestOrderCreation(request);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
