package hhplus.ecommerce.kafka;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class KafkaSubRequestDto {
    private Long messageId;
    private String message;
    private LocalDateTime timestamp;

}