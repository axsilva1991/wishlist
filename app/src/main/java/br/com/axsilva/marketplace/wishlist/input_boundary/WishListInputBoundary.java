package br.com.axsilva.marketplace.wishlist.input_boundary;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqInDto;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;

public interface WishListInputBoundary {
    void insertProduct(String clientId, InsertProductReqInDto insertProductReqInDto);
    ListProductsResInDto getProductsBy(String clientId);
    void checkIfIsOnBy(String clientId, String referenceCode);
    void deleteProductBy(String clientId, String referenceCode);
}
