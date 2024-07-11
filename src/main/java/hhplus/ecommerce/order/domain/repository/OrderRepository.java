package hhplus.ecommerce.order.domain.repository;

import hhplus.ecommerce.order.domain.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Long, Order> {
}
