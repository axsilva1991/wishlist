package br.com.axsilva.marketplace.wishlist.repository.mapper;


import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;

import java.util.List;
import java.util.UUID;

public class InsertWishListReqEntityMapper {

    public WishListEntity inputDtoToEntity(String clientId,InsertProductReqOutDto insertProductReqOutDto) {
        return new WishListEntity(
                UUID.randomUUID(),
                clientId,
                List.of(new InsertProductResEntityMapper().inputDtoToEntity(insertProductReqOutDto))
        );

    }


}
