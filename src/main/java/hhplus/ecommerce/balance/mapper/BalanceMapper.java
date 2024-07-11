package hhplus.ecommerce.balance.mapper;

import hhplus.ecommerce.balance.controller.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.controller.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.balance.infrastructure.PaymentInfraRequestDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    Balance toEntity(BalanceRequestDto dto);
    BalanceResponseDto toDto(Balance balance);
    PaymentInfraRequestDto toInfraDto(Balance balance);

}
