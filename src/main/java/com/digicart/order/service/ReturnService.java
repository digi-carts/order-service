package com.digicart.order.service;

import com.digicart.order.dto.ReturnItemRequest;
import com.digicart.order.dto.ReturnRequest;
import com.digicart.order.dto.OrderReturnItemRequest;
import com.digicart.order.dto.OrderReturnRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.Return;
import com.digicart.order.entity.ReturnItem;
import com.digicart.order.entity.ReturnStatus;
import com.digicart.order.exception.EntityNotFoundException;
import com.digicart.order.repository.OrderRepository;
import com.digicart.order.repository.ReturnRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Application service implementing return use cases for <em>order-service</em>.
 */
@Service
public class ReturnService {

    private final ReturnRepository returnRepository;
    private final OrderRepository orderRepository;

    public ReturnService(ReturnRepository returnRepository, OrderRepository orderRepository) {
        this.returnRepository = returnRepository;
        this.orderRepository = orderRepository;
    }

    public List<Return> findAll() {
        return returnRepository.findAll();
    }

    public Return findById(String id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Return not found: " + id));
    }

    public List<Return> findByStoreId(String storeId) {
        return returnRepository.findByStoreId(storeId);
    }

    public List<Return> findByUserId(String userId) {
        return returnRepository.findByUserId(userId);
    }

    public List<Return> findByOrderId(String orderId) {
        return returnRepository.findByOrder_Id(orderId);
    }

    @Transactional
    public Return create(ReturnRequest req) {
        Order order = orderRepository.findById(req.getOrderId())
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + req.getOrderId()));

        Return ret = new Return();
        ret.setStoreId(req.getStoreId());
        ret.setUserId(req.getUserId());
        ret.setOrder(order);
        ret.setReason(req.getReason());
        ret.setRefundMethod(req.getRefundMethod());
        if (req.getStatus() != null) ret.setStatus(req.getStatus());
        if (req.getComment() != null) ret.setComment(req.getComment());
        if (req.getAdminComment() != null) ret.setAdminComment(req.getAdminComment());
        if (req.getRefundStatus() != null) ret.setRefundStatus(req.getRefundStatus());
        if (req.getRefundAmount() != null) ret.setRefundAmount(req.getRefundAmount());

        if (req.getItems() != null) {
            for (ReturnItemRequest itemReq : req.getItems()) {
                ReturnItem item = new ReturnItem();
                item.setReturnRequest(ret);
                item.setOrderItemId(itemReq.getOrderItemId());
                item.setProductId(itemReq.getProductId());
                item.setProductName(itemReq.getProductName());
                item.setQty(itemReq.getQty());
                item.setPriceAtOrder(itemReq.getPriceAtOrder());
                ret.getItems().add(item);
            }
        }

        return returnRepository.save(ret);
    }

    @Transactional
    public Return update(String id, ReturnRequest req) {
        Return ret = findById(id);
        if (req.getStoreId() != null) ret.setStoreId(req.getStoreId());
        if (req.getUserId() != null) ret.setUserId(req.getUserId());
        if (req.getStatus() != null) ret.setStatus(req.getStatus());
        if (req.getReason() != null) ret.setReason(req.getReason());
        if (req.getComment() != null) ret.setComment(req.getComment());
        if (req.getAdminComment() != null) ret.setAdminComment(req.getAdminComment());
        if (req.getRefundMethod() != null) ret.setRefundMethod(req.getRefundMethod());
        if (req.getRefundStatus() != null) ret.setRefundStatus(req.getRefundStatus());
        if (req.getRefundAmount() != null) ret.setRefundAmount(req.getRefundAmount());
        return returnRepository.save(ret);
    }

    public void delete(String id) {
        findById(id);
        returnRepository.deleteById(id);
    }

    @Transactional
    public Return updateStatus(String id, String newStatus, String comment) {
        Return ret = findById(id);
        ReturnStatus current = ret.getStatus();
        ReturnStatus target = ReturnStatus.valueOf(newStatus.toUpperCase());

        boolean valid = switch (current) {
            case REQUESTED -> target == ReturnStatus.APPROVED || target == ReturnStatus.REJECTED;
            case APPROVED -> target == ReturnStatus.PICKED_UP || target == ReturnStatus.REJECTED;
            case PICKED_UP -> target == ReturnStatus.REFUNDED;
            case REFUNDED -> target == ReturnStatus.COMPLETED;
            default -> false;
        };

        if (!valid) {
            throw new IllegalStateException("Cannot transition from " + current + " to " + target);
        }

        ret.setStatus(target);
        if (comment != null) ret.setAdminComment(comment);
        return returnRepository.save(ret);
    }

    @Transactional
    public Return createForOrder(String orderId, OrderReturnRequest req, String userId) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + orderId));

        Return ret = new Return();
        ret.setStoreId(order.getStoreId());
        ret.setUserId(userId != null ? userId : order.getUserId());
        ret.setOrder(order);
        ret.setReason(req.getReason());
        ret.setRefundMethod("ORIGINAL_PAYMENT");

        if (req.getItems() != null) {
            for (OrderReturnItemRequest itemReq : req.getItems()) {
                ReturnItem item = new ReturnItem();
                item.setReturnRequest(ret);
                item.setProductId(itemReq.getProductId());
                item.setProductName(itemReq.getProductName() != null ? itemReq.getProductName() : itemReq.getProductId());
                item.setOrderItemId(itemReq.getOrderItemId() != null ? itemReq.getOrderItemId() : "UNKNOWN");
                item.setQty(itemReq.getQty());
                item.setPriceAtOrder(0.0);
                ret.getItems().add(item);
            }
        }

        return returnRepository.save(ret);
    }
}
