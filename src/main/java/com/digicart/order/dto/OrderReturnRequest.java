package com.digicart.order.dto;

import jakarta.validation.constraints.NotBlank;

import java.util.List;

/**
 * Request/response DTO: Order Return Request (customer-facing return creation).
 */
public class OrderReturnRequest {

    @NotBlank
    private String reason;

    private List<OrderReturnItemRequest> items;

    public String getReason() { return reason; }
    public void setReason(String reason) { this.reason = reason; }
    public List<OrderReturnItemRequest> getItems() { return items; }
    public void setItems(List<OrderReturnItemRequest> items) { this.items = items; }
}
