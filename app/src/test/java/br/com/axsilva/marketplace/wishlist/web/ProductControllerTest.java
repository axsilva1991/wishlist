package br.com.axsilva.marketplace.wishlist.web;

import br.com.axsilva.marketplace.wishlist.input_boundary.dto.WishListInputBoundary;
import br.com.axsilva.marketplace.wishlist.web.dto.InsertProductReqWeb;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ListProductsResWebDto;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @Mock
    WishListInputBoundary wishListInputBoundary;

    @InjectMocks
    private ProductController productController;

    private final UUID productReferenceCode = UUID.randomUUID();

    InsertProductReqWeb insertProductReqWeb = new InsertProductReqWeb("1", "3", Double.MIN_VALUE);

    @Test
    void getProductsBy_clientId_when_return_products() {

        ResponseEntity<ListProductsResWebDto> responseEntity = productController.getProductsBy("testClientId");

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void validateIfSelectedBy_clientId_and_productId_when_return_products() {

        ResponseEntity<HttpStatus> responseEntity = productController.checkIfIsOnBy("testClientId", productReferenceCode);

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteProductBy_when_delete_product_by_client_and_product_id() {

        ResponseEntity<HttpStatus> responseEntity = productController.deleteProductBy("testClientId", productReferenceCode);

        assertEquals(204, responseEntity.getStatusCodeValue());

    }

    @Test
    void insertProducts_when_insert_product_by_client_and_product_id() {


        ResponseEntity<HttpStatus> responseEntity = productController.insertProduct(
                "clientId",
                insertProductReqWeb);

        assertEquals(201, responseEntity.getStatusCodeValue());

    }

}