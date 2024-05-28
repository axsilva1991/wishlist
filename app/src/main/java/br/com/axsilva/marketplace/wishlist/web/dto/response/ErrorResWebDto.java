package br.com.axsilva.marketplace.wishlist.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ErrorResWebDto(
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                examples = "ERROR_CODE",
                description = "Endpoint error code."
        )
        String code,
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                examples = "error message.",
                description = "Functional description of the error."
        )
        String message
) {
}
