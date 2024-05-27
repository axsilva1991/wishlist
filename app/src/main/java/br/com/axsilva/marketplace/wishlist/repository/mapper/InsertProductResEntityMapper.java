package br.com.axsilva.marketplace.wishlist.repository.mapper;


import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.ProductEntity;

import java.util.UUID;

public class InsertProductResEntityMapper {

    public ProductEntity inputDtoToEntity(InsertProductReqOutDto insertProductReqOutDto) {
        return new ProductEntity(
                UUID.randomUUID(),
                insertProductReqOutDto.referenceCode(),
                insertProductReqOutDto.referenceStore(),
                insertProductReqOutDto.selectedPrice()
                );
    }
}
