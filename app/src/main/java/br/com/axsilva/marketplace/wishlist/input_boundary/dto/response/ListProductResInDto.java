package br.com.axsilva.marketplace.wishlist.input_boundary.dto.response;

public record ListProductResInDto(
        String referenceCode,
        String referenceStore,
        Double selectedPrice) {
}
