package com.digicart.order.controller;

import com.digicart.order.dto.ReturnRequest;
import com.digicart.order.entity.Return;
import com.digicart.order.service.ReturnService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * REST controller exposing return HTTP APIs for <em>order-service</em>.
 */
@RestController
@RequestMapping("/api/returns")
public class ReturnController {

    private final ReturnService returnService;

    public ReturnController(ReturnService returnService) {
        this.returnService = returnService;
    }

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

    @GetMapping("/{id}")
    public ResponseEntity<Return> getById(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(returnService.findById(id));
    }

    @PostMapping
    public ResponseEntity<Return> create(
            @Valid @RequestBody ReturnRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.status(HttpStatus.CREATED).body(returnService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Return> update(
            @PathVariable String id,
            @RequestBody ReturnRequest request,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        return ResponseEntity.ok(returnService.update(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable String id,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        returnService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<?> updateStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> body,
            @RequestHeader(value = "X-User-Id", required = false) String userId,
            @RequestHeader(value = "X-User-Role", required = false) String role) {
        try {
            return ResponseEntity.ok(returnService.updateStatus(id, body.get("status"), body.get("comment")));
        } catch (NoSuchElementException | com.digicart.order.exception.EntityNotFoundException e) {
            return ResponseEntity.status(404).body(Map.of("error", "Return not found"));
        }
    }
}
