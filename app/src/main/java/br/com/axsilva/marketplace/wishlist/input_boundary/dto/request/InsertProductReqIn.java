package br.com.axsilva.marketplace.wishlist.input_boundary.dto.request;

public record InsertProductReqIn(
        String referenceCode,
        String referenceStore,
        Double selectedPrice) {
}
