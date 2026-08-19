package com.digicart.order.repository;

import com.digicart.order.entity.ReturnItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Spring Data JPA repository for return item  persistence.
 */
@Repository
public interface ReturnItemRepository extends JpaRepository<ReturnItem, String> {
    /**
     * Finds by return request id.
     *
     * @param returnId return id
     * @return matching records
     */
    List<ReturnItem> findByReturnRequest_Id(String returnId);
}
