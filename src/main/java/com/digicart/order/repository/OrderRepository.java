package com.digicart.order.repository;

import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for order  persistence.
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {
    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @return matching records
     */
    List<Order> findByStoreId(String storeId);
    /**
     * Finds by user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @return matching records
     */
    List<Order> findByUserId(String userId);
    /**
     * Finds by store id and status.
     *
     * @param storeId store (tenant) identifier
     * @param status status
     * @return matching records
     */
    List<Order> findByStoreIdAndStatus(String storeId, OrderStatus status);
    /**
     * Finds by user id and status.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @param status status
     * @return matching records
     */
    List<Order> findByUserIdAndStatus(String userId, OrderStatus status);
}
