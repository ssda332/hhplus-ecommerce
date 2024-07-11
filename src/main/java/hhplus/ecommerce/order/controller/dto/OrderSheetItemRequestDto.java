package hhplus.ecommerce.order.controller.dto;

public record OrderSheetItemRequestDto(
        Long productId,
        String productName,
        Long productPrice,
        Long productOptionId,
        String productOptionName,
        Long productCount
) {
}