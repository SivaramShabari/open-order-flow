package com.openorderflow.customer.controller;
import com.openorderflow.common.auth.util.CurrentUserContext;
import com.openorderflow.common.dto.order.PlaceOrderRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
public class CustomerOrderController {

    @GetMapping
    public ResponseEntity<?> test(){
        return ResponseEntity.ok(CurrentUserContext.phone());
    }

    @PostMapping
    public ResponseEntity<?> placeOrder(@Valid @RequestBody PlaceOrderRequest request) {
        var userId = CurrentUserContext.userId();
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
