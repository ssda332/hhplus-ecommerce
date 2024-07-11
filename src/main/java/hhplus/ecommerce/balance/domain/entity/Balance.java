package hhplus.ecommerce.balance.domain.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "balance")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Balance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long memberId;

    @Column
    private Long amount;
}
