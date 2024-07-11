package hhplus.ecommerce.product.mapper;

import hhplus.ecommerce.balance.controller.dto.BalanceRequestDto;
import hhplus.ecommerce.balance.controller.dto.BalanceResponseDto;
import hhplus.ecommerce.balance.domain.entity.Balance;
import hhplus.ecommerce.product.domain.entity.Product;
import hhplus.ecommerce.product.domain.entity.ProductOption;
import hhplus.ecommerce.product.dto.ProductResponseDto;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductResponseDto toDto(Product product);

}