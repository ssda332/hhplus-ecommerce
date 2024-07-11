package hhplus.ecommerce.balance.controller.dto;

import lombok.Builder;

@Builder
public record BalanceResponseDto(
        Long memberId,
        Long amount
) {
}
