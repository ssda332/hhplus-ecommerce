package hhplus.ecommerce.product.domain.entity;

import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.product.exception.InsufficientStockException;
import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ProductOptionStock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long stock;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_option_id", nullable = false)
    private ProductOption productOption;

    public void decreaseStock(OrderSheetItem item) {
        Long calculate = this.stock - item.getProductCount();
        if (calculate < 0) throw new InsufficientStockException("재고 없음");

        this.stock = calculate;
    }

}