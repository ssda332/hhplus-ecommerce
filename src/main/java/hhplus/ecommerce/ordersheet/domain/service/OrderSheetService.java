package hhplus.ecommerce.ordersheet.domain.service;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderSheetService {

    private final OrderSheetRepository orderSheetRepository;
    private final ProductRepository productRepository;

    @Transactional
    public OrderSheet createOrderSheet(OrderSheet orderSheet) {

        // 재고확인
        // 재고확인
        List<OrderSheetItem> orderSheetItems = orderSheet.getOrderSheetItems();

        for (OrderSheetItem item : orderSheetItems) {
            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() -> new RuntimeException("상품 없음"));

            for (ProductOption productOption : product.getProductOptions()) {
                if (!productOption.getId().equals(item.getProductOptionId())) continue;
                ProductOptionStock productOptionStock = productOption.getProductOptionStock();

                Long stock = productOptionStock.getStock();
                Long calculate = stock - item.getProductCount();
                if (calculate < 0) throw new RuntimeException("재고 없음");
            }
        }

        return orderSheetRepository.save(orderSheet);
    }

    @Transactional(readOnly = true)
    public OrderSheet getOrderSheetById(Long id) {
        return orderSheetRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("OrderSheet not found"));
    }
}