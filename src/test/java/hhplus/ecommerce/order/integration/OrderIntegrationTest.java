package hhplus.ecommerce.order.integration;

import hhplus.ecommerce.balance.exception.InsufficientBalanceException;
import hhplus.ecommerce.order.application.OrderFacade;
import hhplus.ecommerce.order.domain.dto.OrderAppRequest;
import hhplus.ecommerce.order.domain.entity.Order;
import hhplus.ecommerce.order.domain.repository.OrderRepository;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import hhplus.ecommerce.ordersheet.domain.entity.PaymentStatus;
import hhplus.ecommerce.ordersheet.domain.repository.OrderSheetRepository;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.domain.entity.ProductOptionStock;
import hhplus.ecommerce.product.domain.repository.ProductOptionRepository;
import hhplus.ecommerce.product.domain.repository.ProductOptionStockRepository;
import hhplus.ecommerce.product.domain.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderIntegrationTest {

    @Autowired
    private OrderFacade orderFacade;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderSheetRepository orderSheetRepository;

    @Autowired
    private ProductOptionStockRepository productOptionStockRepository;

    @Autowired
    private ProductOptionRepository productOptionRepository;

    @Autowired
    private ProductRepository productRepository;

    private static final Long MEMBER_ID = 1L;
    private static final Long ORDER_SHEET_ID = 1L;
    private static final Long PRODUCT_OPTION_ID = 1L;
    private static final Long PRODUCT_ID = 1L;
    private static final int INITIAL_STOCK = 100;
    private static final int ORDER_QUANTITY = 10;

    @BeforeEach
    void setUp() {
        // Product 저장
        Product product = Product.builder()
                .name("Sample Product")
                .price(1000L)
                .createDate(LocalDateTime.now())
                .build();
        product = productRepository.save(product); // Save and retrieve the product to generate ID

        // ProductOption 저장
        ProductOption productOption = ProductOption.builder()
                .product(product)
                .name("Sample Product Option")
                .createDate(LocalDateTime.now())
                .build();

        List<ProductOption> productOptions = new ArrayList<>();
        productOptions.add(productOption);
        product.setProductOptions(productOptions); // Set the product options list in the product

        productOption = productOptionRepository.save(productOption); // Save and retrieve the product option to generate ID

        // 초기 재고 설정
        ProductOptionStock stock = ProductOptionStock.builder()
                .productOption(productOption)
                .stock((long) INITIAL_STOCK)
                .build();
        productOptionStockRepository.save(stock);

        // 주문서 설정
        OrderSheet orderSheet = OrderSheet.builder()
                .memberId(MEMBER_ID)
                .address("Sample Address")
                .phone("010-1234-5678")
                .comment("Sample Comment")
                .paymentStatus(PaymentStatus.PENDING)
                .createDate(LocalDateTime.now())
                .build();
        orderSheet = orderSheetRepository.save(orderSheet); // Save and retrieve the order sheet to generate ID

        List<OrderSheetItem> orderSheetItems = new ArrayList<>();
        orderSheetItems.add(OrderSheetItem.builder()
                .orderSheet(orderSheet) // Assign the saved order sheet
                .productId(product.getId())
                .productName(product.getName()) // Set the product name here
                .productOptionId(productOption.getId())
                .productOptionName(productOption.getName()) // Set the product option name here
                .productCount((long) ORDER_QUANTITY)
                .productPrice(product.getPrice())
                .createDate(LocalDateTime.now())
                .build());

        orderSheet.setOrderSheetItems(orderSheetItems); // Set the order sheet items in the order sheet
        orderSheetRepository.save(orderSheet); // Update the order sheet with the items
    }

    @Test
    void testConcurrentOrders() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                OrderAppRequest appRequest = OrderAppRequest.builder()
                        .orderSheetId(ORDER_SHEET_ID)
                        .memberId(MEMBER_ID)
                        .build();

                try {
                    orderFacade.createOrder(appRequest);
                } catch (Exception e) {
                    // Handle exceptions if needed
                } catch (InsufficientBalanceException e) {
                    throw new RuntimeException(e);
                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        List<Order> orders = orderRepository.findAll();
        assertEquals(numberOfThreads, orders.size(), "All orders should be created");

        ProductOptionStock stock = productOptionStockRepository.findByProductOptionIdForUpdate(PRODUCT_OPTION_ID).orElseThrow();
        assertEquals(INITIAL_STOCK - ORDER_QUANTITY * numberOfThreads, stock.getStock(), "Stock should be correctly updated");
    }
}