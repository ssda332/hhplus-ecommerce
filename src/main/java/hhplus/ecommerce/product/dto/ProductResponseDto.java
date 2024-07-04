package hhplus.ecommerce.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public record ProductResponseDto(
    long id

) {

}
