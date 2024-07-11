package hhplus.ecommerce.order.dto;

public record OrderRequestDto(
        Long userId,
        Long productId,
        Long productOptionId,
        Long productCount
) {
}
