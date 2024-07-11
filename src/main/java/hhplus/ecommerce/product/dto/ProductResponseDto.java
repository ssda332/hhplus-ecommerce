package hhplus.ecommerce.product.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDto {
    private Long id;
    private String name;
    private Long price;
    private Long totalStock;
}