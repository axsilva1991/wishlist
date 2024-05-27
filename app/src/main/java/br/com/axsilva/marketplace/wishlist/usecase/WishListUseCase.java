package br.com.axsilva.marketplace.wishlist.usecase;

import br.com.axsilva.marketplace.wishlist.input_boundary.WishListInputBoundary;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqInDto;
import br.com.axsilva.marketplace.wishlist.input_boundary.dto.response.ListProductsResInDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.WishListOutPutBoundary;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductAlreadySelectedException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.exception.WishHasTwentyProductRegisteredException;
import br.com.axsilva.marketplace.wishlist.repository.exception.WishListEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.InternalErrorException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductLimitException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductSelectedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.mapper.InsertProductReqOutputBoundaryMapper;
import br.com.axsilva.marketplace.wishlist.usecase.mapper.ListProductsResInputBoundaryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WishListUseCase implements WishListInputBoundary {

    private static final Logger log = LoggerFactory.getLogger(WishListInputBoundary.class);

    private final WishListOutPutBoundary wishListOutputBoundary;

    public WishListUseCase(WishListOutPutBoundary wishListOutputBoundary) {
        this.wishListOutputBoundary = wishListOutputBoundary;
    }

    @Override
    public void insertProduct(String clientId, InsertProductReqInDto insertProductReqInDto) {
        try {
            log.info("WishListUseCase.insertProducts(clientId, insertProductReqIn: {})", insertProductReqInDto);
            wishListOutputBoundary.insertProduct(
                    clientId,
                    InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto)
            );
        } catch (ProductAlreadySelectedException e) {
            log.error("WishListUseCase.insertProducts(clientId, insertProductReqIn: {}), ProductAlreadySelectedException {}", insertProductReqInDto, e.getStackTrace().toString());
            throw new ProductSelectedException();
        } catch (WishHasTwentyProductRegisteredException e) {
            log.error("WishListUseCase.insertProducts(clientId, insertProductReqIn: {}), WishHasTwentyProductRegisteredException {}", insertProductReqInDto, e.getStackTrace().toString());
            throw new ProductLimitException();

        } catch (GenericRepositoryException e) {
            log.error("WishListUseCase.insertProducts(clientId, insertProductReqIn: {}), GenericRepositoryException {}", insertProductReqInDto, e.getStackTrace().toString());
            throw new InternalErrorException();
        }

    }

    @Override
    public ListProductsResInDto getProductsBy(String clientId) {
        try {
            log.info("WishListUseCase.getProductBy(clientId)");
            return ListProductsResInputBoundaryMapper.INSTANCE.outputBoundaryToInput(wishListOutputBoundary.getProductsBy(clientId));
        } catch (WishListEntityNotFoundException e) {
            log.error("WishListUseCase.getProductBy(clientId), WishListEntityNotFoundException {}", e.getStackTrace().toString());
            throw new WishListNotFoundException();
        } catch (GenericRepositoryException e) {
            log.error("WishListUseCase.getProductBy(clientId), GenericRepositoryException {}", e.getStackTrace().toString());
            throw new InternalErrorException();
        }
    }

    @Override
    public void checkIfIsOnBy(String clientId, String referenceCode) {
        try {
            log.info("WishListUseCase.validateIfSelectedBy({clientId}, referenceCode: {})", referenceCode);
            wishListOutputBoundary.checkIfIsOnBy(clientId, referenceCode);
        } catch (ProductEntityNotFoundException e) {
            log.error("WishListUseCase.validateIfSelectedBy({clientId}, referenceCode: {}) , ProductEntityNotFoundException {}", referenceCode,  e.getStackTrace().toString());
            throw new ValidateProductNotFoundException();
        } catch (GenericRepositoryException e) {
            log.error("WishListUseCase.validateIfSelectedBy({clientId}, referenceCode: {}) , GenericRepositoryException {}", referenceCode,  e.getStackTrace().toString());
            throw new InternalErrorException();
        }
    }

    @Override
    public void deleteProductBy(String clientId, String referenceCode) {
        try {
            log.info("WishListUseCase.deleteProductBy({clientId}, referenceCode: {})", referenceCode);
            wishListOutputBoundary.deleteProductBy(clientId, referenceCode);
        } catch (ProductEntityNotFoundException e) {
            log.error("WishListUseCase.deleteProductBy({clientId}, referenceCode: {}) , ProductEntityNotFoundException {}", referenceCode,  e.getStackTrace().toString());
            throw new ProductDeletedException();
        }
    }
}
