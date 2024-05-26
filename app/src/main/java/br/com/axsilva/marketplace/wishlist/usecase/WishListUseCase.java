package br.com.axsilva.marketplace.wishlist.usecase;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.WishListInputBoundary;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqIn;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductResInDto;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class WishListUseCase implements WishListInputBoundary {

    private static final Logger log = LoggerFactory.getLogger(WishListInputBoundary.class);

    @Override
    public void insertProducts(String clientId, InsertProductReqIn insertProductReqIn) {
        log.info("WishListUseCase.insertProducts(clientId, insertProductReqIn: {})", insertProductReqIn);
    }

    @Override
    public ListProductsResInDto getProductsBy(String clientId) {
        log.info("WishListUseCase.getProductBy(clientId)");
        return new ListProductsResInDto("clientId", List.of(new ListProductResInDto(
                "referenceCode",
                "referenceStore",
                Double.MAX_VALUE)));
    }

    @Override
    public void checkIfIsOnBy(String clientId, UUID referenceCode) {
        log.info("WishListUseCase.validateIfSelectedBy({clientId}, referenceCode: {})", referenceCode);
    }

    @Override
    public void deleteProductBy(String clientId, UUID referenceCode) {
        log.info("WishListUseCase.deleteProductBy({clientId}, referenceCode: {})", referenceCode);
    }
}
