package br.com.axsilva.marketplace.wishlist.output_boundary.dto.response;

public record ListProductResOutDto(
        String referenceCode,
        String referenceStore,
        Double selectedPrice) {
}
