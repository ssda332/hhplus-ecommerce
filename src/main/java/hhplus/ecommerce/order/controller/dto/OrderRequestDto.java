package hhplus.ecommerce.order.controller.dto;

public record OrderRequestDto(
        Long userId,
        Long productId,
        Long productOptionId,
        Long productCount
) {
}
