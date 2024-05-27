package br.com.axsilva.marketplace.wishlist.repository.mapper;

import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductResOutDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductsResOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;


public class InsertProductResOutputBoundaryMapper {
    public ListProductsResOutDto entityToOutputBoundaryResponse(WishListEntity wishListEntity) {
        return new ListProductsResOutDto(
                wishListEntity.clientId(),
                wishListEntity.products().stream().map(productEntity -> new ListProductResOutDto(
                        productEntity.referenceCode(),
                        productEntity.referenceStore(),
                        productEntity.selectedPrice()
                )).toList()
        );
    }

}
