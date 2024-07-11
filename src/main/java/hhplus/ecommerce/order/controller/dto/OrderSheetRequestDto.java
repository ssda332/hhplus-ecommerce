package hhplus.ecommerce.order.controller.dto;

import java.util.List;

public record OrderSheetRequestDto(
        Long memberId,
        String address,
        String phone,
        String comment,
        List<OrderSheetItemRequestDto> items
) {
}