package hhplus.ecommerce.ordersheet.controller;

import hhplus.ecommerce.ordersheet.controller.dto.OrderSheetRequestDto;
import hhplus.ecommerce.ordersheet.controller.dto.OrderSheetResponseDto;
import hhplus.ecommerce.ordersheet.domain.service.OrderSheetService;
import hhplus.ecommerce.ordersheet.mapper.OrderSheetMapper;
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
        return orderSheetMapper.toResponseDto(orderSheetService.getOrderSheetById(id));
    }
}