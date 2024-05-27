package br.com.axsilva.marketplace.wishlist.usecase.mapper;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductsResOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ListProductsResInputBoundaryMapper {

    ListProductsResInputBoundaryMapper INSTANCE = Mappers.getMapper(ListProductsResInputBoundaryMapper.class);

    ListProductsResInDto outputBoundaryToInput(ListProductsResOutDto listProductsResOutDto);

}
