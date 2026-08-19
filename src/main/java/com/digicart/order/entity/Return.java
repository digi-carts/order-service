package com.digicart.order.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * JPA entity mapped in this service schema (Return).
 */
@Entity
@Table(name = "returns", schema = "order_svc")
@EntityListeners(AuditingEntityListener.class)
public class Return {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "store_id", nullable = false)
    private String storeId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private ReturnStatus status = ReturnStatus.REQUESTED;

    @Column(name = "reason", nullable = false)
    private String reason;

    @Column(name = "comment")
    private String comment;

    @Column(name = "admin_comment")
    private String adminComment;

    @Column(name = "refund_method", nullable = false)
    private String refundMethod;

    @Column(name = "refund_status", nullable = false)
    private String refundStatus = "PENDING";

    @Column(name = "refund_amount", nullable = false)
    private Double refundAmount = 0.0;

    @OneToMany(mappedBy = "returnRequest", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReturnItem> items = new ArrayList<>();

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
     * Returns order.
     * @return the order
     */
    public Order getOrder() { return order; }
    /**
     * Sets order.
     *
     * @param order order
     */
    public void setOrder(Order order) { this.order = order; }
    /**
     * Returns status.
     * @return the return status
     */
    public ReturnStatus getStatus() { return status; }
    /**
     * Sets status.
     *
     * @param status status
     */
    public void setStatus(ReturnStatus status) { this.status = status; }
    /**
     * Returns reason.
     * @return the string
     */
    public String getReason() { return reason; }
    /**
     * Sets reason.
     *
     * @param reason reason
     */
    public void setReason(String reason) { this.reason = reason; }
    /**
     * Returns comment.
     * @return the string
     */
    public String getComment() { return comment; }
    /**
     * Sets comment.
     *
     * @param comment comment
     */
    public void setComment(String comment) { this.comment = comment; }
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
     * Returns refund method.
     * @return the string
     */
    public String getRefundMethod() { return refundMethod; }
    /**
     * Sets refund method.
     *
     * @param refundMethod refund method
     */
    public void setRefundMethod(String refundMethod) { this.refundMethod = refundMethod; }
    /**
     * Returns refund status.
     * @return the string
     */
    public String getRefundStatus() { return refundStatus; }
    /**
     * Sets refund status.
     *
     * @param refundStatus refund status
     */
    public void setRefundStatus(String refundStatus) { this.refundStatus = refundStatus; }
    /**
     * Returns refund amount.
     * @return the double
     */
    public Double getRefundAmount() { return refundAmount; }
    /**
     * Sets refund amount.
     *
     * @param refundAmount refund amount
     */
    public void setRefundAmount(Double refundAmount) { this.refundAmount = refundAmount; }
    /**
     * Returns items.
     * @return matching records
     */
    public List<ReturnItem> getItems() { return items; }
    /**
     * Sets items.
     *
     * @param items items
     */
    public void setItems(List<ReturnItem> items) { this.items = items; }
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
