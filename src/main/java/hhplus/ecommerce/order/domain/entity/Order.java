package hhplus.ecommerce.order.domain.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "\"ORDER\"")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long memberId;
    private Long productId;
    private String productName;
    private Long productPrice;
    private Long productOptionId;
    private String productOptionName;
    private Long productCount;
    private LocalDateTime createDate;

}