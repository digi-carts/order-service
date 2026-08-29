package com.digicart.order.repository;

import com.digicart.order.entity.Return;
import com.digicart.order.entity.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Spring Data JPA repository for return  persistence.
 */
@Repository
public interface ReturnRepository extends JpaRepository<Return, UUID> {
    List<Return> findByStoreId(String storeId);
    List<Return> findByUserId(String userId);
    List<Return> findByOrder_Id(UUID orderId);
    List<Return> findByStoreIdAndStatus(String storeId, ReturnStatus status);
}
