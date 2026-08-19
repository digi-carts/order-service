package com.digicart.order.controller;

import com.digicart.order.dto.OrderRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import com.digicart.order.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing order HTTP APIs for <em>order-service</em>.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {

    private final OrderService orderService;

    /**
     * Creates a new {@code OrderController}.
     *
     * @param orderService order service collaborator
     */
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * Handles GET.
     *
     * @param storeId store (tenant) identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param status status
     * @param userId_header user id header
     * @param role caller role
     * @return HTTP response
     */
    @GetMapping
    public ResponseEntity<List<Order>> getAll(
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) OrderStatus status,
            @RequestHeader(value = "X-User-Id", required = false) String userId_header,
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

    /**
     * Handles {@code GET /{id}}.
     *
     * @param id resource identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param role caller role
     * @return HTTP response
     */
    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    /**
     * Handles POST.
     *
     * @param request request payload
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param role caller role
     * @return HTTP response
     */
    @PostMapping
    public ResponseEntity<Order> create(
            @Valid @RequestBody OrderRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.create(request));
    }

    /**
     * Handles {@code PUT /{id}}.
     *
     * @param id resource identifier
     * @param request request payload
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param role caller role
     * @return HTTP response
     */
    @PutMapping("/{id}")
    public ResponseEntity<Order> update(
            @PathVariable String id,
            @RequestBody OrderRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(orderService.update(id, request));
    }

    /**
     * Handles {@code DELETE /{id}}.
     *
     * @param id resource identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param role caller role
     * @return HTTP response
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        orderService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
