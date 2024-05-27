package br.com.axsilva.marketplace.wishlist.input_boundary.dto.response;

import java.util.List;

public record ListProductsResInDto(
        String clientId,
        List<ListProductResInDto> products
) {
}