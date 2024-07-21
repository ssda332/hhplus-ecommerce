package hhplus.ecommerce.balance.application;

import hhplus.ecommerce.balance.domain.dto.BalanceCommand;
import hhplus.ecommerce.balance.domain.dto.BalanceDto;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BalanceFacade {

    private final BalanceService balanceService;

    public Balance getBalance(Long memberId) {
        Balance balance = balanceService.findBalance(memberId);
        return balance;
    }

    public Balance chargeBalance(BalanceDto dto) {
        Balance balance = balanceService.findBalance(dto.memberId());

        return balanceService.chargeBalance(new BalanceCommand.Charge(dto.amount(), balance));
    }
}
