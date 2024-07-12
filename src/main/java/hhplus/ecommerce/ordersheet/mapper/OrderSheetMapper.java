package hhplus.ecommerce.ordersheet.mapper;

import hhplus.ecommerce.ordersheet.controller.dto.OrderSheetRequestDto;
import hhplus.ecommerce.ordersheet.controller.dto.OrderSheetItemRequestDto;
import hhplus.ecommerce.ordersheet.controller.dto.OrderSheetResponseDto;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheet;
import hhplus.ecommerce.ordersheet.domain.entity.OrderSheetItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSheetMapper {
    OrderSheet toEntity(OrderSheetRequestDto dto);
    OrderSheetRequestDto toDto(OrderSheet orderSheet);
    OrderSheetResponseDto toResponseDto(OrderSheet orderSheet);
    OrderSheetItem toEntity(OrderSheetItemRequestDto dto);
    OrderSheetItemRequestDto toDto(OrderSheetItem orderSheetItem);
}