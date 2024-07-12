package hhplus.ecommerce.order.controller;

import hhplus.ecommerce.order.controller.dto.OrderRequestDto;
import hhplus.ecommerce.order.controller.dto.OrderResponseDto;
import hhplus.ecommerce.order.domain.service.OrderService;
import hhplus.ecommerce.order.mapper.OrderMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;
    private final OrderMapper orderMapper;

    // 주문 API
    @PostMapping("")
    public OrderResponseDto createOrder(@RequestBody OrderRequestDto orderRequestDto) {

        return orderMapper.toDto(orderService.createOrder(orderMapper.toAppDto(orderRequestDto)));
    }

}
