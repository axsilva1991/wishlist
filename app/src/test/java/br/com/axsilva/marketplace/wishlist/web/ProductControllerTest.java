package br.com.axsilva.marketplace.wishlist.web;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.WishListInputBoundary;
import br.com.axsilva.marketplace.wishlist.web.dto.InsertProductReqWeb;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ListProductsResWebDto;
import br.com.axsilva.marketplace.wishlist.web.mapper.InsertProductReqInputBoundaryMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.doNothing;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    WishListInputBoundary wishListInputBoundary;

    @InjectMocks
    private ProductController productController;

    private final UUID productReferenceCode = UUID.randomUUID();

    InsertProductReqWeb insertProductReqWeb = new InsertProductReqWeb("1", "3", Double.MIN_VALUE);

    private final String testClientId = "testClientId";

    @Test
    void getProductsBy_clientId_when_return_products() {

        when(wishListInputBoundary.getProductsBy(testClientId)).thenReturn(any());

        ResponseEntity<ListProductsResWebDto> responseEntity = productController.getProductsBy(testClientId);

        verify(wishListInputBoundary, times(1)).getProductsBy(testClientId);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void checkIfIsOnBy_clientId_and_productId_when_return_products() {

        doNothing().when(wishListInputBoundary).checkIfIsOnBy(testClientId, productReferenceCode);

        ResponseEntity<HttpStatus> responseEntity = productController.checkIfIsOnBy(testClientId, productReferenceCode);
        verify(wishListInputBoundary, times(1)).checkIfIsOnBy(testClientId, productReferenceCode);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void deleteProductBy_when_delete_product_by_client_and_product_id() {

        doNothing().when(wishListInputBoundary).deleteProductBy(testClientId, productReferenceCode);

        ResponseEntity<HttpStatus> responseEntity = productController.deleteProductBy(testClientId, productReferenceCode);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        verify(wishListInputBoundary, times(1)).deleteProductBy(testClientId, productReferenceCode);

    }

    @Test
    void insertProducts_when_insert_product_by_client_and_product_id() {

        doNothing().when(wishListInputBoundary).insertProduct(testClientId,
                InsertProductReqInputBoundaryMapper.INSTANCE.webDtoToInputBoundary(insertProductReqWeb));

        ResponseEntity<HttpStatus> responseEntity = productController.insertProduct(
                testClientId,
                insertProductReqWeb);

        verify(wishListInputBoundary, times(1)).insertProduct(testClientId,
                InsertProductReqInputBoundaryMapper.INSTANCE.webDtoToInputBoundary(insertProductReqWeb));
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());

    }

}