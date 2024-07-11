package hhplus.ecommerce.balance.domain.repository;

import hhplus.ecommerce.balance.domain.entity.Balance;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BalanceRepository extends JpaRepository<Balance, Long> {
    Optional<Balance> findByMemberId(Long memberId);
}
