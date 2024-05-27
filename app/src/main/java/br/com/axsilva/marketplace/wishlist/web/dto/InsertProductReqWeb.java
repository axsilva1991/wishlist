package br.com.axsilva.marketplace.wishlist.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

public record InsertProductReqWeb(
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Reference code registered in the store."
        )
        @NotNull(message = "Reference code is required.")
        String referenceCode,
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Reference code of the store."
        )
        @NotNull(message = "reference Store code is required.")
        String referenceStore,
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Price selected when the product was added to the wish list."
        )
        @NotNull(message = "Selected Price Store code is required.")
        Double selectedPrice) {
}
