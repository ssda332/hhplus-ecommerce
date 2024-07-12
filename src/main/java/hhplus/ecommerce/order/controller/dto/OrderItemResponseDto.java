package hhplus.ecommerce.order.controller.dto;

import java.time.LocalDateTime;

public record OrderItemResponseDto(
        Long id,
        String productName,
        Long productOptionId,
        String productOptionName,
        Long productPrice,
        Long productCount,
        LocalDateTime createDate
) {}