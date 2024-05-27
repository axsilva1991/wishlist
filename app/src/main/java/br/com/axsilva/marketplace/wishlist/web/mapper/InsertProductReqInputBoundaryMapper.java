package br.com.axsilva.marketplace.wishlist.web.mapper;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqInDto;
import br.com.axsilva.marketplace.wishlist.web.dto.InsertProductReqWeb;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface InsertProductReqInputBoundaryMapper {
    InsertProductReqInputBoundaryMapper INSTANCE = Mappers.getMapper(InsertProductReqInputBoundaryMapper.class);

    InsertProductReqInDto webDtoToInputBoundary(InsertProductReqWeb insertProductReqWeb);


}
