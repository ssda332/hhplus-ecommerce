package hhplus.ecommerce.order.dto;

import java.time.LocalDateTime;

public record PaymentResponseDto(
        Long paymentId,
        Long userId,
        Long orderId,
        Long amount,
        String status,
        LocalDateTime paymentDate
) {

}
