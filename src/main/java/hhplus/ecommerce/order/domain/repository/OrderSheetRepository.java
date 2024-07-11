package hhplus.ecommerce.order.domain.repository;

import hhplus.ecommerce.order.domain.entity.OrderSheet;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderSheetRepository extends JpaRepository<Long, OrderSheet> {
}
