package hhplus.ecommerce.balance.infrastructure;

import hhplus.ecommerce.balance.controller.dto.PaymentRequestDto;
import hhplus.ecommerce.balance.controller.dto.PaymentResponseDto;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ExternalPaymentStub {

    public PaymentInfraResponseDto processPayment(PaymentInfraRequestDto paymentInfraRequestDto) {
        return new PaymentInfraResponseDto(
                1L,
                paymentInfraRequestDto.userId(),
                paymentInfraRequestDto.orderSheetId(),
                paymentInfraRequestDto.amount(),
                "SUCCESS",
                LocalDateTime.now());
    }
}