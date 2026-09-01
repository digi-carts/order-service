package com.digicart.order.service;

import com.digicart.order.dto.CheckoutItemRequest;
import com.digicart.order.dto.CheckoutRequest;
import com.digicart.order.dto.OrderItemRequest;
import com.digicart.order.dto.OrderRequest;
import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderItem;
import com.digicart.order.entity.OrderStatus;
import com.digicart.order.exception.EntityNotFoundException;
import com.digicart.order.repository.OrderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        return orderRepository.findAllWithItems();
    }

    public Order findById(String id) {
        try {
            return orderRepository.findById(UUID.fromString(id))
                    .orElseThrow(() -> new EntityNotFoundException("Order not found: " + id));
        } catch (IllegalArgumentException e) {
            throw new EntityNotFoundException("Order not found: " + id);
        }
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
        orderRepository.deleteById(UUID.fromString(id));
    }

    public List<Map<String, Object>> getStatsByStore() {
        return orderRepository.getStatsByStore().stream().map(row -> {
            Map<String, Object> m = new LinkedHashMap<>();
            m.put("storeId", row[0]);
            m.put("orders", row[1]);
            m.put("revenue", row[2] != null ? row[2] : 0.0);
            return m;
        }).toList();
    }

    public Map<String, Object> getAnalytics(String storeId, int days) {
        Instant cutoff = Instant.now().minus(days, ChronoUnit.DAYS);

        List<Object[]> totals = orderRepository.getAnalytics(storeId, cutoff);
        long totalOrders = 0;
        double totalRevenue = 0.0;
        if (!totals.isEmpty() && totals.get(0)[0] != null) {
            Object[] row = totals.get(0);
            totalOrders = ((Number) row[0]).longValue();
            totalRevenue = row[1] != null ? ((Number) row[1]).doubleValue() : 0.0;
        }

        List<Map<String, Object>> ordersByDay = orderRepository.getOrdersByDay(storeId, cutoff).stream()
            .map(r -> {
                Map<String, Object> d = new LinkedHashMap<>();
                d.put("date", r[0] != null ? r[0].toString() : "");
                d.put("orders", r[1] != null ? ((Number) r[1]).longValue() : 0L);
                d.put("revenue", r[2] != null ? ((Number) r[2]).doubleValue() : 0.0);
                return d;
            }).toList();

        List<Map<String, Object>> topProducts = orderRepository.getTopProducts(storeId, cutoff).stream()
            .map(r -> {
                Map<String, Object> p = new LinkedHashMap<>();
                p.put("name", r[0] != null ? r[0].toString() : "Unknown");
                p.put("qty", r[1] != null ? ((Number) r[1]).longValue() : 0L);
                return p;
            }).toList();

        Map<String, Object> m = new LinkedHashMap<>();
        m.put("totalOrders", totalOrders);
        m.put("totalRevenue", totalRevenue);
        m.put("ordersByDay", ordersByDay);
        m.put("topProducts", topProducts);
        return m;
    }

    public Map<String, Object> getActiveCount(String storeId) {
        List<OrderStatus> activeStatuses = List.of(OrderStatus.CONFIRMED, OrderStatus.PROCESSING, OrderStatus.SHIPPED);
        long count = orderRepository.countActiveByStoreId(storeId, activeStatuses);
        return Map.of("count", count);
    }

    @Transactional
    public Order checkout(CheckoutRequest req, String userId) {
        double total = req.getCartItems().stream()
                .mapToDouble(item -> item.getPrice() * item.getQty())
                .sum();
        Order order = new Order();
        order.setStoreId(req.getStoreId());
        order.setUserId(userId);
        order.setTotal(total);
        order.setShippingAddress(req.getAddressId());
        order.setPaymentMethod(req.getPaymentMethod());
        order.setStatus(OrderStatus.PENDING);
        for (CheckoutItemRequest item : req.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProductId(item.getProductId());
            orderItem.setProductName(item.getProductName() != null ? item.getProductName() : item.getProductId());
            orderItem.setQty(item.getQty());
            orderItem.setPriceAtOrder(item.getPrice());
            order.getItems().add(orderItem);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order applyCustomerAction(String id, String action) {
        Order order = findById(id);
        switch (action.toUpperCase()) {
            case "CANCEL" -> order.setStatus(OrderStatus.CANCELLED);
            case "CONFIRM_DELIVERY" -> order.setStatus(OrderStatus.DELIVERED);
            default -> throw new IllegalArgumentException("Unknown action: " + action);
        }
        return orderRepository.save(order);
    }

    @Transactional
    public Order updateStatus(String id, String status, String comment) {
        Order order = findById(id);
        order.setStatus(OrderStatus.valueOf(status.toUpperCase()));
        return orderRepository.save(order);
    }
}
