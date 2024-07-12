package hhplus.ecommerce.ordersheet.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record OrderSheetItemDto(
        Long productId,
        String productName,
        Long productPrice,
        Long productOptionId,
        String productOptionName,
        Long productCount,
        LocalDateTime createDate
) {}