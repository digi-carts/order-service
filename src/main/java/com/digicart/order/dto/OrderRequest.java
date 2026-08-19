package com.digicart.order.dto;

import com.digicart.order.entity.OrderStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.List;

/**
 * Request/response DTO: Order Request.
 */
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

    /**
     * Returns store id.
     * @return the string
     */
    public String getStoreId() { return storeId; }
    /**
     * Sets store id.
     *
     * @param storeId store (tenant) identifier
     */
    public void setStoreId(String storeId) { this.storeId = storeId; }
    /**
     * Returns user id.
     * @return the string
     */
    public String getUserId() { return userId; }
    /**
     * Sets user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     */
    public void setUserId(String userId) { this.userId = userId; }
    /**
     * Returns status.
     * @return the order status
     */
    public OrderStatus getStatus() { return status; }
    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(OrderStatus status) { this.status = status; }
    /**
     * Returns payment method.
     * @return the string
     */
    public String getPaymentMethod() { return paymentMethod; }
    /**
     * Sets payment method.
     *
     * @param paymentMethod payment method
     */
    public void setPaymentMethod(String paymentMethod) { this.paymentMethod = paymentMethod; }
    /**
     * Returns total.
     * @return the double
     */
    public Double getTotal() { return total; }
    /**
     * Sets total.
     *
     * @param total total
     */
    public void setTotal(Double total) { this.total = total; }
    /**
     * Returns shipping address.
     * @return the string
     */
    public String getShippingAddress() { return shippingAddress; }
    /**
     * Sets shipping address.
     *
     * @param shippingAddress shipping address
     */
    public void setShippingAddress(String shippingAddress) { this.shippingAddress = shippingAddress; }
    /**
     * Returns tracking id.
     * @return the string
     */
    public String getTrackingId() { return trackingId; }
    /**
     * Sets tracking id.
     *
     * @param trackingId tracking id
     */
    public void setTrackingId(String trackingId) { this.trackingId = trackingId; }
    /**
     * Returns courier provider.
     * @return the string
     */
    public String getCourierProvider() { return courierProvider; }
    /**
     * Sets courier provider.
     *
     * @param courierProvider courier provider
     */
    public void setCourierProvider(String courierProvider) { this.courierProvider = courierProvider; }
    /**
     * Returns awb number.
     * @return the string
     */
    public String getAwbNumber() { return awbNumber; }
    /**
     * Sets awb number.
     *
     * @param awbNumber awb number
     */
    public void setAwbNumber(String awbNumber) { this.awbNumber = awbNumber; }
    /**
     * Returns label url.
     * @return the string
     */
    public String getLabelUrl() { return labelUrl; }
    /**
     * Sets label url.
     *
     * @param labelUrl label url
     */
    public void setLabelUrl(String labelUrl) { this.labelUrl = labelUrl; }
    /**
     * Returns tracking status.
     * @return the string
     */
    public String getTrackingStatus() { return trackingStatus; }
    /**
     * Sets tracking status.
     *
     * @param trackingStatus tracking status
     */
    public void setTrackingStatus(String trackingStatus) { this.trackingStatus = trackingStatus; }
    /**
     * Returns preferred courier id.
     * @return the string
     */
    public String getPreferredCourierId() { return preferredCourierId; }
    /**
     * Sets preferred courier id.
     *
     * @param preferredCourierId preferred courier id
     */
    public void setPreferredCourierId(String preferredCourierId) { this.preferredCourierId = preferredCourierId; }
    /**
     * Returns preferred courier name.
     * @return the string
     */
    public String getPreferredCourierName() { return preferredCourierName; }
    /**
     * Sets preferred courier name.
     *
     * @param preferredCourierName preferred courier name
     */
    public void setPreferredCourierName(String preferredCourierName) { this.preferredCourierName = preferredCourierName; }
    /**
     * Returns admin comment.
     * @return the string
     */
    public String getAdminComment() { return adminComment; }
    /**
     * Sets admin comment.
     *
     * @param adminComment admin comment
     */
    public void setAdminComment(String adminComment) { this.adminComment = adminComment; }
    /**
     * Returns delivered at.
     * @return the instant
     */
    public Instant getDeliveredAt() { return deliveredAt; }
    /**
     * Sets delivered at.
     *
     * @param deliveredAt delivered at
     */
    public void setDeliveredAt(Instant deliveredAt) { this.deliveredAt = deliveredAt; }
    /**
     * Returns items.
     * @return matching records
     */
    public List<OrderItemRequest> getItems() { return items; }
    /**
     * Sets items.
     *
     * @param items items
     */
    public void setItems(List<OrderItemRequest> items) { this.items = items; }
}
