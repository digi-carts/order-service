package com.digicart.order.repository;

import com.digicart.order.entity.Return;
import com.digicart.order.entity.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for return  persistence.
 */
@Repository
public interface ReturnRepository extends JpaRepository<Return, String> {
    /**
     * Finds by store id.
     *
     * @param storeId store (tenant) identifier
     * @return matching records
     */
    List<Return> findByStoreId(String storeId);
    /**
     * Finds by user id.
     *
     * @param userId caller user id from the gateway ({@code X-User-Id})
     * @return matching records
     */
    List<Return> findByUserId(String userId);
    /**
     * Finds by order id.
     *
     * @param orderId order identifier
     * @return matching records
     */
    List<Return> findByOrder_Id(String orderId);
    /**
     * Finds by store id and status.
     *
     * @param storeId store (tenant) identifier
     * @param status status
     * @return matching records
     */
    List<Return> findByStoreIdAndStatus(String storeId, ReturnStatus status);
}
