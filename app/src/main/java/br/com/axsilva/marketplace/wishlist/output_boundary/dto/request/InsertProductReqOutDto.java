package br.com.axsilva.marketplace.wishlist.output_boundary.dto.request;

public record InsertProductReqOutDto(
        String referenceCode,
        String referenceStore,
        Double selectedPrice) {
}
