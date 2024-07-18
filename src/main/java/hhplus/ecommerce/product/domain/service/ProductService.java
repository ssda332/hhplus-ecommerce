package hhplus.ecommerce.product.domain.service;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;

    @Transactional
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        return product;
    }

    @Transactional(readOnly = true)
    public List<Product> getTopProducts() {

        List<Long> topProductIds = orderItemRepository.findTopProductIds(PageRequest.of(0, 5));

        return topProductIds.stream()
                .map(productRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

    }
}