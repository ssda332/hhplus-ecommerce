package hhplus.ecommerce.order.domain.repository;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

    @Query("SELECT new hhplus.ecommerce.order.domain.entity.OrderItem(oi.productId, oi.productName, SUM(oi.productPrice), SUM(oi.productCount)) " +
            "FROM OrderItem oi " +
            "GROUP BY oi.productId, oi.productName " +
            "ORDER BY SUM(oi.productCount) DESC")
    List<OrderItem> findTopProducts(Pageable pageable);
}