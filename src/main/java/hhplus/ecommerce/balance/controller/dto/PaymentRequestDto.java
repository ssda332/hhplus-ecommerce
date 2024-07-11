package hhplus.ecommerce.balance.controller.dto;

import lombok.Builder;

@Builder
public record PaymentRequestDto(
        Long userId,
        Long orderSheetId,
        Long amount
) {
}
