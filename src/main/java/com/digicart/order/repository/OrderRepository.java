package com.digicart.order.repository;

import com.digicart.order.entity.Order;
import com.digicart.order.entity.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.storeId = :storeId")
    List<Order> findByStoreId(@Param("storeId") String storeId);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.userId = :userId")
    List<Order> findByUserId(@Param("userId") String userId);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.storeId = :storeId AND o.status = :status")
    List<Order> findByStoreIdAndStatus(@Param("storeId") String storeId, @Param("status") OrderStatus status);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items WHERE o.userId = :userId AND o.status = :status")
    List<Order> findByUserIdAndStatus(@Param("userId") String userId, @Param("status") OrderStatus status);

    @Query("SELECT DISTINCT o FROM Order o LEFT JOIN FETCH o.items")
    List<Order> findAllWithItems();

    @Query("SELECT o.storeId, COUNT(o), SUM(o.total) FROM Order o GROUP BY o.storeId")
    List<Object[]> getStatsByStore();

    @Query("SELECT COUNT(o), SUM(o.total), AVG(o.total) FROM Order o WHERE o.storeId = :storeId AND o.createdAt >= :cutoff")
    List<Object[]> getAnalytics(@Param("storeId") String storeId, @Param("cutoff") Instant cutoff);

    @Query(value = "SELECT CAST(o.created_at AS date) AS day, COUNT(o.id), COALESCE(SUM(o.total), 0) FROM order_svc.orders o WHERE o.store_id = :storeId AND o.created_at >= :cutoff GROUP BY day ORDER BY day", nativeQuery = true)
    List<Object[]> getOrdersByDay(@Param("storeId") String storeId, @Param("cutoff") Instant cutoff);

    @Query(value = "SELECT oi.product_name, COALESCE(SUM(oi.qty), 0) AS total_qty FROM order_svc.order_items oi JOIN order_svc.orders o ON o.id = oi.order_id WHERE o.store_id = :storeId AND o.created_at >= :cutoff GROUP BY oi.product_name ORDER BY total_qty DESC LIMIT 10", nativeQuery = true)
    List<Object[]> getTopProducts(@Param("storeId") String storeId, @Param("cutoff") Instant cutoff);

    @Query("SELECT COUNT(o) FROM Order o WHERE o.storeId = :storeId AND o.status IN :statuses")
    long countActiveByStoreId(@Param("storeId") String storeId, @Param("statuses") List<OrderStatus> statuses);
}
