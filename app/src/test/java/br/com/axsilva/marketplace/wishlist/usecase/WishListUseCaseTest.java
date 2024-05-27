package br.com.axsilva.marketplace.wishlist.usecase;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.request.InsertProductReqInDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.WishListOutPutBoundary;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductAlreadySelectedException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.exception.WishListEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.BusinessException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.InternalErrorException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.mapper.InsertProductReqOutputBoundaryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class WishListUseCaseTest {
    @Mock
    WishListOutPutBoundary wishListOutPutBoundary;

    @InjectMocks
    WishListUseCase wishListUseCase;

    private final String clientId = "client123";
    private final InsertProductReqInDto insertProductReqInDto = new InsertProductReqInDto("referenceCode", "referenceStore", Double.MAX_VALUE);
    private final String referenceCode = UUID.randomUUID().toString();

    @Test
    void insert_one_Product_when_insert_product_by_client_and_product_id_is_Ok() {

        wishListUseCase.insertProduct(clientId, insertProductReqInDto);

        verify(wishListOutPutBoundary, times(1)).insertProduct(clientId, InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto));
    }


    @Test
    void insert_one_Product_when_insert_product_by_client_and_product_when_return_ProductAlreadySelectedException() {

        doThrow(ProductAlreadySelectedException.class).when(wishListOutPutBoundary).insertProduct(clientId, InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto));

        assertThrows(BusinessException.class, () -> wishListUseCase.insertProduct(clientId, insertProductReqInDto));

        verify(wishListOutPutBoundary, times(1)).insertProduct(clientId, InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto));
    }

    @Test
    void insert_one_Product_when_insert_product_by_client_and_product_when_has_system_exception() {

        doThrow(GenericRepositoryException.class).when(wishListOutPutBoundary).insertProduct(clientId, InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto));

        assertThrows(InternalErrorException.class, () -> wishListUseCase.insertProduct(clientId, insertProductReqInDto));

        verify(wishListOutPutBoundary, times(1)).insertProduct(clientId, InsertProductReqOutputBoundaryMapper.INSTANCE.inputDtoToOutputBoundary(insertProductReqInDto));
    }
    @Test
    void getProductsBy_clientId_when_return_products_is_Ok() {

        wishListUseCase.getProductsBy(clientId);

        verify(wishListOutPutBoundary, times(1)).getProductsBy(clientId);
    }

    @Test
    void getProductsBy_clientId_when_return_products_is_not_found() {

        doThrow(WishListEntityNotFoundException.class).when(wishListOutPutBoundary).getProductsBy(clientId);

        assertThrows(WishListNotFoundException.class, () -> wishListUseCase.getProductsBy(clientId));

        verify(wishListOutPutBoundary, times(1)).getProductsBy(clientId);
    }

    @Test
    void getProductsBy_clientId_when_return_products_has_system_exception() {

        doThrow(GenericRepositoryException.class).when(wishListOutPutBoundary).getProductsBy(clientId);

        assertThrows(InternalErrorException.class, () -> wishListUseCase.getProductsBy(clientId));

        verify(wishListOutPutBoundary, times(1)).getProductsBy(clientId);
    }

    @Test
    void checkIfIsOnBy_clientId_and_productId_when_has_system_exception() throws ProductNotFoundException {

        doThrow(GenericRepositoryException.class).when(wishListOutPutBoundary).checkIfIsOnBy(clientId, referenceCode);

        assertThrows(InternalErrorException.class, () -> wishListUseCase.checkIfIsOnBy(clientId, referenceCode));

        verify(wishListOutPutBoundary, times(1)).checkIfIsOnBy(clientId, referenceCode);
    }

    @Test
    void checkIfIsOnBy_clientId_and_productId_when_return_products_Ok() throws ProductNotFoundException {

        wishListUseCase.checkIfIsOnBy(clientId, referenceCode);

        verify(wishListOutPutBoundary, times(1)).checkIfIsOnBy(clientId, referenceCode);
    }

    @Test
    void checkIfIsOnBy_clientId_and_productId_when_the_product_is_not_found() throws ProductNotFoundException {

        doThrow(ProductEntityNotFoundException.class).when(wishListOutPutBoundary).checkIfIsOnBy(clientId, referenceCode);

        assertThrows(ValidateProductNotFoundException.class, () -> wishListUseCase.checkIfIsOnBy(clientId, referenceCode));

        verify(wishListOutPutBoundary, times(1)).checkIfIsOnBy(clientId, referenceCode);
    }

    @Test
    void deleteProductBy_when_delete_product_by_client_and_product_id_Ok() {

        wishListUseCase.deleteProductBy(clientId, referenceCode);

        verify(wishListOutPutBoundary, times(1)).deleteProductBy(clientId, referenceCode);
    }

    @Test
    void deleteProductBy_when_delete_product_by_client_and_product_not_found() {

        doThrow(ProductEntityNotFoundException.class).when(wishListOutPutBoundary).deleteProductBy(clientId, referenceCode);

        assertThrows(ProductDeletedException.class, () -> wishListUseCase.deleteProductBy(clientId, referenceCode));

        verify(wishListOutPutBoundary, times(1)).deleteProductBy(clientId, referenceCode);
    }
}