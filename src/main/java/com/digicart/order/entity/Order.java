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

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
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
    public List<OrderItem> getItems() { return items; }
    public void setItems(List<OrderItem> items) { this.items = items; }
    public List<Return> getReturns() { return returns; }
    public void setReturns(List<Return> returns) { this.returns = returns; }
    public Instant getCreatedAt() { return createdAt; }
    public Instant getUpdatedAt() { return updatedAt; }
}
