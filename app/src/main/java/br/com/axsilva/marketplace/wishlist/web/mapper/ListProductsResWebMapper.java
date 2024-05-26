package br.com.axsilva.marketplace.wishlist.web.mapper;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ListProductsResWebDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListProductsResWebMapper {
    ListProductsResWebMapper INSTANCE = Mappers.getMapper(ListProductsResWebMapper.class);

    ListProductsResWebDto inputBoundaryToWebDto(ListProductsResInDto listProductsResInDto);

}
