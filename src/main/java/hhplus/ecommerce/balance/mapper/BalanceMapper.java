package hhplus.ecommerce.balance.mapper;

import hhplus.ecommerce.balance.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.domain.entity.Balance;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BalanceMapper {
    Balance toEntity(BalanceRequestDto dto);
    BalanceResponseDto toDto(Balance entity);

}
