package br.com.axsilva.marketplace.wishlist.input_boundary.dto;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqIn;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;

import java.util.UUID;

public interface WishListInputBoundary {
    void insertProduct(String clientId, InsertProductReqIn insertProductReqIn);
    ListProductsResInDto getProductsBy(String clientId);
    void checkIfIsOnBy(String clientId, UUID referenceCode);
    void deleteProductBy(String clientId, UUID referenceCode);
}
