package hhplus.ecommerce.order.dto;

import java.time.LocalDateTime;

public record OrderResponseDto(
    Long orderId,
    Long userId,
    Long productId,
    String productName,
    Long productOptionId,
    String productOptionName,
    Long productCount,
    Long productPrice,
    LocalDateTime createDate
) {
}
