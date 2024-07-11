package hhplus.ecommerce.exception;

public record ErrorResponse(
        String code,
        String message
) {
}
