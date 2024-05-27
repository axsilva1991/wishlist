package br.com.axsilva.marketplace.wishlist.web.dto.response;

import io.swagger.v3.oas.annotations.Parameter;

import java.util.List;

public record ListProductsResWebDto(
        @Parameter(description = "Identification code of customer", required = true)
        String clientId,
        @Parameter(description = "list of products selected by customer.", required = true)
        List<ListProductResWebDto> products
) {
}