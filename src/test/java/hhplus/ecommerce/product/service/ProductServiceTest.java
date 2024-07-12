package hhplus.ecommerce.product.service;

import hhplus.ecommerce.order.domain.entity.OrderItem;
import hhplus.ecommerce.order.domain.repository.OrderItemRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import hhplus.ecommerce.product.domain.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @InjectMocks
    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderItemRepository orderItemRepository;

    private Product product;
    private List<OrderItem> orderItems;

    @BeforeEach
    void setUp() {
        product = Product.builder()
                .id(1L)
                .name("Test Product")
                .price(1000L)
                .build();

        orderItems = Arrays.asList(
                OrderItem.builder().productId(5L).productName("Product 5").productPrice(500L).productCount(6L).build(),
                OrderItem.builder().productId(1L).productName("Product 1").productPrice(100L).productCount(10L).build(),
                OrderItem.builder().productId(3L).productName("Product 3").productPrice(300L).productCount(8L).build(),
                OrderItem.builder().productId(7L).productName("Product 7").productPrice(700L).productCount(4L).build(),
                OrderItem.builder().productId(2L).productName("Product 2").productPrice(200L).productCount(9L).build(),
                OrderItem.builder().productId(6L).productName("Product 6").productPrice(600L).productCount(5L).build(),
                OrderItem.builder().productId(9L).productName("Product 9").productPrice(900L).productCount(2L).build(),
                OrderItem.builder().productId(10L).productName("Product 10").productPrice(1000L).productCount(1L).build(),
                OrderItem.builder().productId(4L).productName("Product 4").productPrice(400L).productCount(7L).build(),
                OrderItem.builder().productId(8L).productName("Product 8").productPrice(800L).productCount(3L).build()
        );

        // Shuffle the list to ensure it is not in order
        Collections.shuffle(orderItems);
    }

    @Test
    @DisplayName("상품 조회 - 성공")
    void getProductByIdSuccess() {
        // given
        given(productRepository.findById(1L)).willReturn(Optional.of(product));

        // when
        Product foundProduct = productService.getProductById(1L);

        // then
        assertThat(foundProduct).isNotNull();
        assertThat(foundProduct.getId()).isEqualTo(product.getId());
        assertThat(foundProduct.getName()).isEqualTo(product.getName());
        assertThat(foundProduct.getPrice()).isEqualTo(product.getPrice());
    }

    @Test
    @DisplayName("상품 조회 - 실패 (존재하지 않는 ID)")
    void getProductByIdFail() {
        // given
        given(productRepository.findById(1L)).willReturn(Optional.empty());

        // when & then
        assertThatThrownBy(() -> productService.getProductById(1L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Product not found");
    }

    @Test
    @DisplayName("상위 상품 조회 - 성공")
    void getTopProductsSuccess() {
        // given
        List<OrderItem> topOrderItems = orderItems.subList(0, 5); // 상위 5개 아이템만 반환하도록 설정
        given(orderItemRepository.findTopProducts(PageRequest.of(0, 5))).willReturn(topOrderItems);

        // when
        List<OrderItem> topProducts = productService.getTopProducts();

        // then
        assertThat(topProducts).isNotNull();
        assertThat(topProducts.size()).isEqualTo(5);


        // 상위 5개의 아이템을 예상 순서대로 정렬
        List<Long> expectedProductIds = Arrays.asList(1L, 2L, 3L, 4L, 5L);
        for (int i = 0; i < 5; i++) {
            assertThat(topProducts.get(i).getProductId()).isEqualTo(expectedProductIds.get(i));
        }
    }
}