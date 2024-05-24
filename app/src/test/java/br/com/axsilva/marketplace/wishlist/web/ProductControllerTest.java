package br.com.axsilva.marketplace.wishlist.web;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    @InjectMocks
    private ProductController productController;

    @Test
    void getProductsBy_clientId_when_return_products() {

        ResponseEntity<String> responseEntity = productController.getProductsBy("testClientId");

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void getProductBy_clientId_and_productId_when_return_products() {

        ResponseEntity<String> responseEntity = productController.getProductBy("testClientId", "testProductId");

        assertEquals(200, responseEntity.getStatusCodeValue());
    }

    @Test
    void deleteProductBy_when_delete_product_by_client_and_product_id() {

        ResponseEntity<HttpStatus> responseEntity = productController.deleteProductBy("testClientId", "testProductId");

        assertEquals(204, responseEntity.getStatusCodeValue());

    }

    @Test
    void insertProducts_when_insert_product_by_client_and_product_id() {

        ResponseEntity<HttpStatus> responseEntity = productController.insertProducts("testClientId");

        assertEquals(201, responseEntity.getStatusCodeValue());

    }

}