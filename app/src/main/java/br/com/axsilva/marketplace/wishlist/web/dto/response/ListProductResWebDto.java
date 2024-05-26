package br.com.axsilva.marketplace.wishlist.web.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

public record ListProductResWebDto(
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Reference code registered in the store."
        )
        String referenceCode,
        @Schema(
                requiredMode = Schema.RequiredMode.REQUIRED,
                description = "Reference code of the store."
        )
        String referenceStore,
        @Schema(requiredMode = Schema.RequiredMode.NOT_REQUIRED,
                description = "Price selected when the product was added to the wish list."
        )
        Double priceSelected
) {

}
