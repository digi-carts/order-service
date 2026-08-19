package com.digicart.order.repository;

import com.digicart.order.entity.Return;
import com.digicart.order.entity.ReturnStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnRepository extends JpaRepository<Return, String> {
    List<Return> findByStoreId(String storeId);
    List<Return> findByUserId(String userId);
    List<Return> findByOrder_Id(String orderId);
    List<Return> findByStoreIdAndStatus(String storeId, ReturnStatus status);
}
