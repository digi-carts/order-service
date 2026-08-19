package com.digicart.order.service;

import com.digicart.order.dto.ReturnItemRequest;
import com.digicart.order.dto.ReturnRequest;
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

    /**
     * Creates a new {@code ReturnService}.
     *
     * @param returnRepository return repository collaborator
     * @param orderRepository order repository collaborator
     */
    public ReturnService(ReturnRepository returnRepository, OrderRepository orderRepository) {
        this.returnRepository = returnRepository;
        this.orderRepository = orderRepository;
    }

    /**
     * Finds all.
     * @return matching records
     */
    public List<Return> findAll() {
        return returnRepository.findAll();
    }

    /**
     * Finds by id.
     *
     * @param id resource identifier
     * @return the return
     */
    public Return findById(String id) {
        return returnRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Return not found: " + id));
    }

    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @return matching records
     */
    public List<Return> findByStoreId(String storeId) {
        return returnRepository.findByStoreId(storeId);
    }

    /**
     * Finds by user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @return matching records
     */
    public List<Return> findByUserId(String userId) {
        return returnRepository.findByUserId(userId);
    }

    /**
     * Finds by order id.
     *
     * @param orderId order identifier
     * @return matching records
     */
    public List<Return> findByOrderId(String orderId) {
        return returnRepository.findByOrder_Id(orderId);
    }

    /**
     * Creates a new record.
     *
     * @param req request payload
     * @return the return
     */
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

    /**
     * Updates an existing record.
     *
     * @param id resource identifier
     * @param req request payload
     * @return the return
     */
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

    /**
     * Deletes the record.
     *
     * @param id resource identifier
     */
    public void delete(String id) {
        findById(id);
        returnRepository.deleteById(id);
    }
}
