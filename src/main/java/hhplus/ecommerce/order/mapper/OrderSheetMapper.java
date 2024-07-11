package hhplus.ecommerce.order.mapper;

import hhplus.ecommerce.order.controller.dto.OrderSheetRequestDto;
import hhplus.ecommerce.order.controller.dto.OrderSheetItemRequestDto;
import hhplus.ecommerce.order.controller.dto.OrderSheetResponseDto;
import hhplus.ecommerce.order.domain.entity.OrderSheet;
import hhplus.ecommerce.order.domain.entity.OrderSheetItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderSheetMapper {
    OrderSheet toEntity(OrderSheetRequestDto dto);
    OrderSheetRequestDto toDto(OrderSheet orderSheet);
    OrderSheetResponseDto toResponseDto(OrderSheet orderSheet);
    OrderSheetItem toEntity(OrderSheetItemRequestDto dto);
    OrderSheetItemRequestDto toDto(OrderSheetItem orderSheetItem);
}