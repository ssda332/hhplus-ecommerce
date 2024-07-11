package hhplus.ecommerce.order.domain.service;

import hhplus.ecommerce.order.controller.dto.OrderSheetRequestDto;
import hhplus.ecommerce.order.domain.entity.OrderSheet;
import hhplus.ecommerce.order.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.order.mapper.OrderSheetMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderSheetService {

    @Autowired
    private OrderSheetRepository orderSheetRepository;

    @Autowired
    private OrderSheetMapper orderSheetMapper;

    @Transactional
    public OrderSheet createOrderSheet(OrderSheet orderSheet) {
        OrderSheet savedOrderSheet = orderSheetRepository.save(orderSheet);
        return savedOrderSheet;
    }

    @Transactional
    public OrderSheet getOrderSheetById(Long id) {
        OrderSheet orderSheet = orderSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order sheet not found"));
        return orderSheetMapper.toEntity(orderSheet);
    }
}