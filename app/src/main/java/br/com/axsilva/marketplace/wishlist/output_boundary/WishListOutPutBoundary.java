package br.com.axsilva.marketplace.wishlist.output_boundary;

import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductsResOutDto;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;

public interface WishListOutPutBoundary {
    void insertProduct(String clientId, InsertProductReqOutDto insertProductReqOut);
    ListProductsResOutDto getProductsBy(String clientId) throws GenericRepositoryException;
    void checkIfIsOnBy(String clientId, String referenceCode) throws GenericRepositoryException;
    void deleteProductBy(String clientId, String referenceCode);

}
