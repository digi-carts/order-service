package com.digicart.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request/response DTO: Checkout Request (storefront order creation).
 */
public class CheckoutRequest {

    @NotNull
    @NotEmpty
    private List<CheckoutItemRequest> cartItems;

    @NotBlank
    private String addressId;

    @NotBlank
    private String paymentMethod;

    @NotBlank
    private String storeId;

    public List<CheckoutItemRequest> getCartItems() { return cartItems; }
    public void setCartItems(List<CheckoutItemRequest> cartItems) { this.cartItems = cartItems; }
    public String getAddressId() { return addressId; }
    public void setAddressId(String addressId) { this.addressId = addressId; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
}
