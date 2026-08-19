package com.digicart.order.repository;

import com.digicart.order.entity.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnItemRepository extends JpaRepository<ReturnItem, String> {
    List<ReturnItem> findByReturnRequest_Id(String returnId);
}
