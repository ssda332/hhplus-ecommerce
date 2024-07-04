package hhplus.ecommerce.balance.controller;

import hhplus.ecommerce.balance.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.dto.BalanceResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    // 잔액 충전 API
    @PostMapping("/balance/charge")
    public BalanceResponseDto chargeBalance(@RequestBody BalanceRequestDto balanceRequestDto) {
        return new BalanceResponseDto(1L, 200L);
    }

    // 잔액 조회 API
    @GetMapping("/balance")
    public BalanceResponseDto getBalance(@RequestParam("userId") Long userId) {
        return new BalanceResponseDto(1L, 200L);
    }
}
