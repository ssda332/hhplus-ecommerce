package hhplus.ecommerce.order.dto;

public record PaymentRequestDto(
        Long userId,
        Long orderId,
        Long amount
) {
}
