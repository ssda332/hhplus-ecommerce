package hhplus.ecommerce.ordersheet.controller.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record OrderSheetResponseDto(
        Long id,
        Long memberId,
        String address,
        String phone,
        String comment,
        Long totalPrice,
        LocalDateTime createDate,
        List<OrderSheetItemDto> orderSheetItems
) {}