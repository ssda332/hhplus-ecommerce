package hhplus.ecommerce.balance.controller.dto;

import lombok.Builder;

@Builder
public record BalanceRequestDto(
        Long memberId,
        Long amount
) {
}
