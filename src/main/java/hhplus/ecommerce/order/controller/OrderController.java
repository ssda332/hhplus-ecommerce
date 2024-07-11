package hhplus.ecommerce.order.controller;

import hhplus.ecommerce.order.controller.dto.OrderRequestDto;
import hhplus.ecommerce.order.controller.dto.OrderResponseDto;
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

}
