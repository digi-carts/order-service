package com.digicart.order.repository;

import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    List<Order> findByStoreId(String storeId);
    List<Order> findByUserId(String userId);
    List<Order> findByStoreIdAndStatus(String storeId, OrderStatus status);
    List<Order> findByUserIdAndStatus(String userId, OrderStatus status);
}
