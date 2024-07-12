package hhplus.ecommerce.order.domain.service;

import hhplus.ecommerce.order.domain.entity.OrderSheet;
import hhplus.ecommerce.order.domain.repository.OrderSheetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderSheetService {

    private final OrderSheetRepository orderSheetRepository;

    @Transactional
    public OrderSheet createOrderSheet(OrderSheet orderSheet) {



        return orderSheetRepository.save(orderSheet);
    }

    @Transactional(readOnly = true)
    public OrderSheet getOrderSheetById(Long id) {
        return orderSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderSheet not found"));
    }
}