package br.com.axsilva.marketplace.wishlist.usecase.mapper;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqInDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InsertProductReqOutputBoundaryMapper {
    InsertProductReqOutputBoundaryMapper INSTANCE = Mappers.getMapper(InsertProductReqOutputBoundaryMapper.class);

    InsertProductReqOutDto inputDtoToOutputBoundary(InsertProductReqInDto InsertProductReqInDto);

}
