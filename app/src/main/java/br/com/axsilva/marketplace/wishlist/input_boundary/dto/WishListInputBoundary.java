package br.com.axsilva.marketplace.wishlist.input_boundary.dto;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqIn;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;

import java.util.UUID;

public interface WishListInputBoundary {
    public void insertProducts(String clientId, InsertProductReqIn insertProductReqIn);
    public ListProductsResInDto getProductsBy(String clientId);
    public void checkIfIsOnBy(String clientId, UUID referenceCode);
    public void deleteProductBy(String clientId, UUID referenceCode);
}
