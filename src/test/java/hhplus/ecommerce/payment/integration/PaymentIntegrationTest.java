package hhplus.ecommerce.payment.integration;

import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.domain.repository.BalanceRepository;
import hhplus.ecommerce.payment.application.PaymentFacade;
import hhplus.ecommerce.payment.domain.dto.PaymentDto;
import hhplus.ecommerce.payment.domain.entity.Payment;
import hhplus.ecommerce.payment.domain.repository.PaymentRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PaymentIntegrationTest {

    @Autowired
    private PaymentFacade paymentFacade;

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    private static final Long MEMBER_ID = 2L;
    private static final Long ORDER_SHEET_ID = 1L;
    private static final Long APPROVAL_NUMBER = UUID.randomUUID().getLeastSignificantBits();
    private static final Long AMOUNT = 100L;

    @BeforeEach
    void setUp() {
        Balance balance = Balance.builder()
                .memberId(MEMBER_ID)
                .amount(1000L)
                .build();

        balanceRepository.save(balance);
    }
    @AfterEach
    void tearDown() {
        paymentRepository.deleteAll();
        balanceRepository.deleteAll();
    }

    @Test
    @DisplayName("동일 승인번호로 여러 결제 요청 시도")
    void testConcurrentPaymentRequests() throws InterruptedException {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for (int i = 0; i < numberOfThreads; i++) {
            executorService.submit(() -> {
                PaymentDto paymentDto = PaymentDto.builder()
                        .memberId(MEMBER_ID)
                        .orderSheetId(ORDER_SHEET_ID)
                        .approvalNumber(APPROVAL_NUMBER)
                        .amount(AMOUNT)
                        .build();

                try {
                    paymentFacade.savePayment(paymentDto);
                } catch (Exception e) {

                }
            });
        }

        executorService.shutdown();
        executorService.awaitTermination(1, TimeUnit.MINUTES);

        List<Payment> payments = paymentRepository.findAll();
        assertEquals(1, payments.size(), "한개의 결제정보만 만들어져야함");

        Balance balance = balanceRepository.findByMemberId(MEMBER_ID).orElseThrow();
        assertEquals(1000L + AMOUNT, balance.getAmount(), "잔액이 맞는지 확인");
    }
}