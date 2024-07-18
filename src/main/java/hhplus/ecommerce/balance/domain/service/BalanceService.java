package hhplus.ecommerce.balance.domain.service;

import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.balance.infrastructure.ExternalPaymentStub;
import hhplus.ecommerce.balance.infrastructure.PaymentInfraResponseDto;
import hhplus.ecommerce.balance.mapper.BalanceMapper;
import hhplus.ecommerce.balance.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BalanceService {

    private final BalanceRepository balanceRepository;
    private final ExternalPaymentStub externalPaymentStub;
    private final BalanceMapper balanceMapper;

    @Transactional
    public Balance findBalance(Long memberId) throws MemberNotFoundException {
        return balanceRepository.findByMemberId(memberId).orElseThrow(() -> new MemberNotFoundException("사용자가 존재하지 않습니다."));
    }

    @Transactional
    public Balance chargeBalance(Balance entity) throws MemberNotFoundException {
        Long memberId = entity.getMemberId();

        Balance current = balanceRepository.findByMemberId(memberId).orElseThrow(() -> new MemberNotFoundException("사용자가 존재하지 않습니다."));
        Long sum = current.getAmount() + entity.getAmount();

        current.chargeAmount(sum);
        return current;
    }

    @Transactional
    public Balance processPayment(Balance balance) throws Exception {
        PaymentInfraResponseDto paymentInfraResponseDto = externalPaymentStub.processPayment(balanceMapper.toInfraDto(balance));

        if (paymentInfraResponseDto.status().equals("SUCCESS")) {
            Balance result = chargeBalance(balance);

            return result;
        } else {
            throw new Exception();
        }
    }
}
