package com.digicart.order.dto;

import com.digicart.order.entity.ReturnStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

/**
 * Request/response DTO: Return Request.
 */
public class ReturnRequest {

    @NotBlank
    private String storeId;

    @NotBlank
    private String userId;

    @NotBlank
    private String orderId;

    private ReturnStatus status;

    @NotBlank
    private String reason;

    private String comment;
    private String adminComment;

    @NotBlank
    private String refundMethod;

    private String refundStatus;
    private Double refundAmount;

    private List<ReturnItemRequest> items;

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
     * Returns order id.
     * @return the string
     */
    public String getOrderId() { return orderId; }
    /**
     * Sets order id.
     *
     * @param orderId order identifier
     */
    public void setOrderId(String orderId) { this.orderId = orderId; }
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
    public List<ReturnItemRequest> getItems() { return items; }
    /**
     * Sets items.
     *
     * @param items items
     */
    public void setItems(List<ReturnItemRequest> items) { this.items = items; }
}
