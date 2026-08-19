package com.digicart.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request/response DTO: Return Item Request.
 */
public class ReturnItemRequest {

    @NotBlank
    private String orderItemId;

    @NotBlank
    private String productId;

    @NotBlank
    private String productName;

    @NotNull
    private Integer qty;

    @NotNull
    private Double priceAtOrder;

    /**
     * Returns order item id.
     * @return the string
     */
    public String getOrderItemId() { return orderItemId; }
    /**
     * Sets order item id.
     *
     * @param orderItemId order item id
     */
    public void setOrderItemId(String orderItemId) { this.orderItemId = orderItemId; }
    /**
     * Returns product id.
     * @return the string
     */
    public String getProductId() { return productId; }
    /**
     * Sets product id.
     *
     * @param productId product id
     */
    public void setProductId(String productId) { this.productId = productId; }
    /**
     * Returns product name.
     * @return the string
     */
    public String getProductName() { return productName; }
    /**
     * Sets product name.
     *
     * @param productName product name
     */
    public void setProductName(String productName) { this.productName = productName; }
    /**
     * Returns qty.
     * @return the integer
     */
    public Integer getQty() { return qty; }
    /**
     * Sets qty.
     *
     * @param qty qty
     */
    public void setQty(Integer qty) { this.qty = qty; }
    /**
     * Returns price at order.
     * @return the double
     */
    public Double getPriceAtOrder() { return priceAtOrder; }
    /**
     * Sets price at order.
     *
     * @param priceAtOrder price at order
     */
    public void setPriceAtOrder(Double priceAtOrder) { this.priceAtOrder = priceAtOrder; }
}
