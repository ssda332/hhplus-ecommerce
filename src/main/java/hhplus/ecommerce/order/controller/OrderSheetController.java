package hhplus.ecommerce.order.controller;

import hhplus.ecommerce.order.controller.dto.OrderSheetRequestDto;
import hhplus.ecommerce.order.controller.dto.OrderSheetResponseDto;
import hhplus.ecommerce.order.domain.service.OrderSheetService;
import hhplus.ecommerce.order.mapper.OrderSheetMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ordersheet")
@RequiredArgsConstructor
public class OrderSheetController {

    private final OrderSheetService orderSheetService;
    private final OrderSheetMapper orderSheetMapper;

    @PostMapping
    public OrderSheetResponseDto createOrderSheet(@RequestBody OrderSheetRequestDto dto) {
        return orderSheetMapper.toResponseDto(orderSheetService.createOrderSheet(orderSheetMapper.toEntity(dto)));
    }

    @GetMapping("/{id}")
    public OrderSheetResponseDto getOrderSheet(@PathVariable Long id) {
        return orderSheetService.getOrderSheetById(id);
    }
}