package hhplus.ecommerce.balance.dto;

import lombok.Builder;

@Builder
public record BalanceRequestDto(
        Long memberId,
        Long amount
) {
}
