package hhplus.ecommerce.product.controller;

import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.service.ProductService;
import hhplus.ecommerce.product.dto.ProductDetailResponseDto;
import hhplus.ecommerce.product.dto.ProductResponseDto;
import hhplus.ecommerce.product.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;
    private final ProductMapper productMapper;

    // 상위 상품 조회 API
    @GetMapping("/top")
    public List<ProductDetailResponseDto> getProducts() {
        return List.of(
                new ProductDetailResponseDto(1L, "Product1", 1000L, "Option1", 100L, LocalDateTime.now().minusDays(2), 50L),
                new ProductDetailResponseDto(2L, "Product2", 2000L, "Option2", 200L, LocalDateTime.now().minusDays(3), 150L),
                new ProductDetailResponseDto(3L, "Product3", 3000L, "Option3", 300L, LocalDateTime.now().minusDays(1), 30L),
                new ProductDetailResponseDto(4L, "Product4", 4000L, "Option4", 400L, LocalDateTime.now().minusDays(1), 70L),
                new ProductDetailResponseDto(5L, "Product5", 5000L, "Option5", 500L, LocalDateTime.now().minusDays(1), 10L)
        );
    }

    // 상품 정보 조회 API
    @GetMapping("/{id}")
    public ProductResponseDto getProductDetail(@PathVariable Long id) {
        return productMapper.toProductResponseDto(productService.getProductById(id));
    }

}
