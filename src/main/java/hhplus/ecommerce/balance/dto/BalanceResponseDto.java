package hhplus.ecommerce.balance.dto;

import lombok.Builder;

@Builder
public record BalanceResponseDto(
        Long memberId,
        Long amount
) {
}
