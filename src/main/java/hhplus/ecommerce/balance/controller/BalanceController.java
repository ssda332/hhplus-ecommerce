package hhplus.ecommerce.balance.controller;

import hhplus.ecommerce.balance.controller.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.controller.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.mapper.BalanceMapper;
import hhplus.ecommerce.balance.domain.service.BalanceService;
import hhplus.ecommerce.exception.MemberNotFoundException;
import hhplus.ecommerce.balance.controller.dto.PaymentRequestDto;
import hhplus.ecommerce.balance.controller.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/balance")
@RequiredArgsConstructor
public class BalanceController {

    private final BalanceMapper balanceMapper;
    private final BalanceService balanceService;

    // 잔액 충전 API
    @PostMapping("/charge")
    public BalanceResponseDto chargeBalance(@RequestBody BalanceRequestDto dto) throws MemberNotFoundException {
        return balanceMapper.toDto(balanceService.chargeBalance(balanceMapper.toEntity(dto)));
    }

    // 잔액 조회 API
    @GetMapping("")
    public BalanceResponseDto getBalance(@RequestParam("memberId") Long memberId) throws MemberNotFoundException {
        return balanceMapper.toDto(balanceService.findBalance(memberId));
    }

    // 결제 API
    @PostMapping("/payment")
    public PaymentResponseDto payment(@RequestBody PaymentRequestDto paymentRequest) {
        Long paymentId = 1L;
        String status = "SUCCESS";
        LocalDateTime paymentDate = LocalDateTime.now();

        return new PaymentResponseDto(paymentId, paymentRequest.userId(), paymentRequest.orderSheetId(),
                paymentRequest.amount(), status, paymentDate);
    }
}
