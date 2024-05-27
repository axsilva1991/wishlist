package br.com.axsilva.marketplace.wishlist.input_boundary.dto.request;

public record InsertProductReqInDto(
        String referenceCode,
        String referenceStore,
        Double selectedPrice) {
}
