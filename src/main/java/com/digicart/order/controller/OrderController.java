package com.digicart.order.controller;

import com.digicart.order.dto.CheckoutRequest;
import com.digicart.order.dto.CustomerActionRequest;
import com.digicart.order.dto.OrderRequest;
import com.digicart.order.dto.OrderReturnRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import com.digicart.order.entity.Return;
import com.digicart.order.service.OrderService;
import com.digicart.order.service.ReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * REST controller exposing order HTTP APIs for <em>order-service</em>.
 */
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final ReturnService returnService;

    public OrderController(OrderService orderService, ReturnService returnService) {
        this.orderService = orderService;
        this.returnService = returnService;
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll(
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) OrderStatus status,
            @RequestHeader(value = "X-User-Id", required = false) String userIdHeader,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        if (storeId != null && status != null) {
            return ResponseEntity.ok(orderService.findByStoreIdAndStatus(storeId, status));
        }
        if (storeId != null) {
            return ResponseEntity.ok(orderService.findByStoreId(storeId));
        }
        if (userId != null) {
            return ResponseEntity.ok(orderService.findByUserId(userId));
        }
        return ResponseEntity.ok(orderService.findAll());
    }

    @GetMapping("/analytics")
    public ResponseEntity<Map<String, Object>> getAnalytics(
            @RequestParam(defaultValue = "30") int days) {
        return ResponseEntity.ok(orderService.getAnalytics(days));
    }

    @GetMapping("/active-count")
    public ResponseEntity<Map<String, Object>> getActiveCount(
            @RequestHeader(value = "X-Store-Id") String storeId) {
        return ResponseEntity.ok(orderService.getActiveCount(storeId));
    }

    @GetMapping("/stats/by-store")
    public ResponseEntity<Map<String, Object>> getStatsByStore() {
        return ResponseEntity.ok(Map.of("stores", orderService.getStatsByStore()));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Order> create(
            @Valid @RequestBody OrderRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    @PostMapping("/checkout")
    public ResponseEntity<Order> checkout(
            @Valid @RequestBody CheckoutRequest request,
            @RequestHeader(value = "X-User-Id") String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.checkout(request, userId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(
            @PathVariable String id,
            @RequestBody OrderRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    @PatchMapping("/{id}/customer-action")
    public ResponseEntity<Order> customerAction(
            @PathVariable String id,
            @Valid @RequestBody CustomerActionRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        return ResponseEntity.ok(orderService.applyCustomerAction(id, request.getAction()));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Order> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(orderService.updateStatus(id, body.get("status"), body.get("comment")));
    }

    @PostMapping("/{id}/return")
    public ResponseEntity<Return> createReturn(
            @PathVariable String id,
            @Valid @RequestBody OrderReturnRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(returnService.createForOrder(id, request, userId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
