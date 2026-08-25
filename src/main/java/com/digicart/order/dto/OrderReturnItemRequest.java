package com.digicart.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request/response DTO: Order Return Item Request.
 */
public class OrderReturnItemRequest {

    @NotBlank
    private String productId;

    private String productName;

    private String orderItemId;

    @NotNull
    private Integer qty;

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public String getOrderItemId() { return orderItemId; }
    public void setOrderItemId(String orderItemId) { this.orderItemId = orderItemId; }
    public Integer getQty() { return qty; }
    public void setQty(Integer qty) { this.qty = qty; }
}
