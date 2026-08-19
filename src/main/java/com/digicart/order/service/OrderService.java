package com.digicart.order.service;

import com.digicart.order.dto.OrderItemRequest;
import com.digicart.order.dto.OrderRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderItem;
import com.digicart.order.entity.OrderStatus;
import com.digicart.order.exception.EntityNotFoundException;
import com.digicart.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Application service implementing order use cases for <em>order-service</em>.
 */
@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public Order findById(String id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
    }

    public List<Order> findByStoreId(String storeId) {
        return orderRepository.findByStoreId(storeId);
    }

    public List<Order> findByUserId(String userId) {
        return orderRepository.findByUserId(userId);
    }

    public List<Order> findByStoreIdAndStatus(String storeId, OrderStatus status) {
        return orderRepository.findByStoreIdAndStatus(storeId, status);
    }

    @Transactional
    public Order create(OrderRequest req) {
        Order order = new Order();
        order.setStoreId(req.getStoreId());
        order.setUserId(req.getUserId());
        order.setTotal(req.getTotal());
        order.setShippingAddress(req.getShippingAddress());
        if (req.getStatus() != null) order.setStatus(req.getStatus());
        if (req.getPaymentMethod() != null) order.setPaymentMethod(req.getPaymentMethod());
        if (req.getTrackingId() != null) order.setTrackingId(req.getTrackingId());
        if (req.getCourierProvider() != null) order.setCourierProvider(req.getCourierProvider());
        if (req.getAwbNumber() != null) order.setAwbNumber(req.getAwbNumber());
        if (req.getLabelUrl() != null) order.setLabelUrl(req.getLabelUrl());
        if (req.getTrackingStatus() != null) order.setTrackingStatus(req.getTrackingStatus());
        if (req.getPreferredCourierId() != null) order.setPreferredCourierId(req.getPreferredCourierId());
        if (req.getPreferredCourierName() != null) order.setPreferredCourierName(req.getPreferredCourierName());
        if (req.getAdminComment() != null) order.setAdminComment(req.getAdminComment());
        if (req.getDeliveredAt() != null) order.setDeliveredAt(req.getDeliveredAt());

        if (req.getItems() != null) {
            for (OrderItemRequest itemReq : req.getItems()) {
                OrderItem item = new OrderItem();
                item.setOrder(order);
                item.setProductId(itemReq.getProductId());
                item.setProductName(itemReq.getProductName());
                item.setQty(itemReq.getQty());
                item.setPriceAtOrder(itemReq.getPriceAtOrder());
                order.getItems().add(item);
            }
        }

        return orderRepository.save(order);
    }

    @Transactional
    public Order update(String id, OrderRequest req) {
        Order order = findById(id);
        if (req.getStoreId() != null) order.setStoreId(req.getStoreId());
        if (req.getUserId() != null) order.setUserId(req.getUserId());
        if (req.getStatus() != null) order.setStatus(req.getStatus());
        if (req.getPaymentMethod() != null) order.setPaymentMethod(req.getPaymentMethod());
        if (req.getTotal() != null) order.setTotal(req.getTotal());
        if (req.getShippingAddress() != null) order.setShippingAddress(req.getShippingAddress());
        if (req.getTrackingId() != null) order.setTrackingId(req.getTrackingId());
        if (req.getCourierProvider() != null) order.setCourierProvider(req.getCourierProvider());
        if (req.getAwbNumber() != null) order.setAwbNumber(req.getAwbNumber());
        if (req.getLabelUrl() != null) order.setLabelUrl(req.getLabelUrl());
        if (req.getTrackingStatus() != null) order.setTrackingStatus(req.getTrackingStatus());
        if (req.getPreferredCourierId() != null) order.setPreferredCourierId(req.getPreferredCourierId());
        if (req.getPreferredCourierName() != null) order.setPreferredCourierName(req.getPreferredCourierName());
        if (req.getAdminComment() != null) order.setAdminComment(req.getAdminComment());
        if (req.getDeliveredAt() != null) order.setDeliveredAt(req.getDeliveredAt());
        return orderRepository.save(order);
    }

    public void delete(String id) {
        findById(id);
        orderRepository.deleteById(id);
    }
}
