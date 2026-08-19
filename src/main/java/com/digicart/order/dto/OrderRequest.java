package com.digicart.order.dto;

import com.digicart.order.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

public class OrderRequest {

    @NotBlank
    private String storeId;

    @NotBlank
    private String userId;

    private OrderStatus status;

    private String paymentMethod;

    @NotNull
    private Double total;

    @NotBlank
    private String shippingAddress;

    private String trackingId;
    private String courierProvider;
    private String awbNumber;
    private String labelUrl;
    private String trackingStatus;
    private String preferredCourierId;
    private String preferredCourierName;
    private String adminComment;
    private Instant deliveredAt;

    private List<OrderItemRequest> items;

    public String getStoreId() { return storeId; }
    public void setStoreId(String storeId) { this.storeId = storeId; }
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
    public String getPaymentMethod() { return paymentMethod; }
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    public Double getTotal() { return total; }
    public void setTotal(Double total) { this.total = total; }
    public String getShippingAddress() { return shippingAddress; }
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    public String getTrackingId() { return trackingId; }
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }
    public String getCourierProvider() { return courierProvider; }
    public void setCourierProvider(String courierProvider) { this.courierProvider = courierProvider; }
    public String getAwbNumber() { return awbNumber; }
    public void setAwbNumber(String awbNumber) { this.awbNumber = awbNumber; }
    public String getLabelUrl() { return labelUrl; }
    public void setLabelUrl(String labelUrl) { this.labelUrl = labelUrl; }
    public String getTrackingStatus() { return trackingStatus; }
    public void setTrackingStatus(String trackingStatus) { this.trackingStatus = trackingStatus; }
    public String getPreferredCourierId() { return preferredCourierId; }
    public void setPreferredCourierId(String preferredCourierId) { this.preferredCourierId = preferredCourierId; }
    public String getPreferredCourierName() { return preferredCourierName; }
    public void setPreferredCourierName(String preferredCourierName) { this.preferredCourierName = preferredCourierName; }
    public String getAdminComment() { return adminComment; }
    public void setAdminComment(String adminComment) { this.adminComment = adminComment; }
    public Instant getDeliveredAt() { return deliveredAt; }
    public void setDeliveredAt(Instant deliveredAt) { this.deliveredAt = deliveredAt; }
    public List<OrderItemRequest> getItems() { return items; }
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
