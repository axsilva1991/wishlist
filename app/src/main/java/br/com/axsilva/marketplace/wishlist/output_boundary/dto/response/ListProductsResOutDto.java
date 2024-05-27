package br.com.axsilva.marketplace.wishlist.output_boundary.dto.response;

import java.util.List;

public record ListProductsResOutDto(
        String clientId,
        List<ListProductResOutDto> products
) {
}