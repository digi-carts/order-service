package com.digicart.order.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity mapped in this service schema (Order).
 */
@Entity
@Table(name = "orders", schema = "order_svc")
@EntityListeners(AuditingEntityListener.class)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private OrderStatus status = OrderStatus.PENDING;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod = "ONLINE";

    @Column(name = "total", nullable = false)
    private Double total;

    @Column(name = "shipping_address", columnDefinition = "jsonb", nullable = false)
    private String shippingAddress;

    @Column(name = "tracking_id")
    private String trackingId;

    @Column(name = "courier_provider")
    private String courierProvider;

    @Column(name = "awb_number")
    private String awbNumber;

    @Column(name = "label_url")
    private String labelUrl;

    @Column(name = "tracking_status")
    private String trackingStatus;

    @Column(name = "preferred_courier_id")
    private String preferredCourierId;

    @Column(name = "preferred_courier_name")
    private String preferredCourierName;

    @Column(name = "admin_comment")
    private String adminComment;

    @Column(name = "delivered_at")
    private Instant deliveredAt;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Return> returns = new ArrayList<>();

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    /**
     * Returns id.
     * @return the string
     */
    public String getId() { return id; }
    /**
     * Sets id.
     *
     * @param id resource identifier
     */
    public void setId(String id) { this.id = id; }
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
    public List<OrderItem> getItems() { return items; }
    /**
     * Sets items.
     *
     * @param items items
     */
    public void setItems(List<OrderItem> items) { this.items = items; }
    /**
     * Returns returns.
     * @return matching records
     */
    public List<Return> getReturns() { return returns; }
    /**
     * Sets returns.
     *
     * @param returns returns
     */
    public void setReturns(List<Return> returns) { this.returns = returns; }
    /**
     * Returns created at.
     * @return the instant
     */
    public Instant getCreatedAt() { return createdAt; }
    /**
     * Returns updated at.
     * @return the instant
     */
    public Instant getUpdatedAt() { return updatedAt; }
}
