package hhplus.ecommerce.product.domain.service;

import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional
    public Product getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));


        return product;
    }
}