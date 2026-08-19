package com.digicart.order.controller;

import com.digicart.order.dto.ReturnRequest;
import com.digicart.order.entity.Return;
import com.digicart.order.service.ReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller exposing return HTTP APIs for <em>order-service</em>.
 */
@RestController
@RequestMapping("/returns")
public class ReturnController {

    private final ReturnService returnService;

    /**
     * Creates a new {@code ReturnController}.
     *
     * @param returnService return service collaborator
     */
    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

    /**
     * Handles GET.
     *
     * @param storeId store (tenant) identifier
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param orderId order identifier
     * @param userId_header user id header
     * @param role caller role
     * @return HTTP response
     */
    @GetMapping
    public ResponseEntity<List<Return>> getAll(
            @RequestParam(required = false) String storeId,
            @RequestParam(required = false) String userId,
            @RequestParam(required = false) String orderId,
            @RequestHeader(value = "X-User-Id", required = false) String userId_header,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        if (storeId != null) {
            return ResponseEntity.ok(returnService.findByStoreId(storeId));
        }
        if (userId != null) {
            return ResponseEntity.ok(returnService.findByUserId(userId));
        }
        if (orderId != null) {
            return ResponseEntity.ok(returnService.findByOrderId(orderId));
        }
        return ResponseEntity.ok(returnService.findAll());
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
    public ResponseEntity<Return> getById(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(returnService.findById(id));
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
    public ResponseEntity<Return> create(
            @Valid @RequestBody ReturnRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(returnService.create(request));
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
    public ResponseEntity<Return> update(
            @PathVariable String id,
            @RequestBody ReturnRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(returnService.update(id, request));
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
        returnService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
