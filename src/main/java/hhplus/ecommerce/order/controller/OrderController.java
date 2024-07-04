package hhplus.ecommerce.order.controller;

import hhplus.ecommerce.order.dto.OrderRequestDto;
import hhplus.ecommerce.order.dto.OrderResponseDto;
import hhplus.ecommerce.order.dto.PaymentRequestDto;
import hhplus.ecommerce.order.dto.PaymentResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    // 주문 API
    @PostMapping("")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequest) {
        Long orderId = 1L;
        String productName = "Sample Product";
        String productOptionName = "Sample Option";
        Long productPrice = 2000L;
        LocalDateTime createDate = LocalDateTime.now();

        return new OrderResponseDto(orderId, orderRequest.userId(), orderRequest.productId(), productName,
                orderRequest.productOptionId(), productOptionName, orderRequest.productCount(),
                productPrice, createDate);
    }

    // 결제 API
    @PostMapping("/payment")
    public PaymentResponseDto payment(@RequestBody PaymentRequestDto paymentRequest) {
        Long paymentId = 1L;
        String status = "SUCCESS";
        LocalDateTime paymentDate = LocalDateTime.now();

        return new PaymentResponseDto(paymentId, paymentRequest.userId(), paymentRequest.orderId(),
                paymentRequest.amount(), status, paymentDate);
    }
}
