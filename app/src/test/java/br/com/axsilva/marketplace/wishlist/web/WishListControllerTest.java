package br.com.axsilva.marketplace.wishlist.web;

import br.com.axsilva.marketplace.wishlist.web.helper.TransactionIdentification;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertEquals;


@ExtendWith(MockitoExtension.class)
class WishListControllerTest {
    @Mock
    private TransactionIdentification transactionIdentification;

    @InjectMocks
    private WishListController wishListController;

    @Test
    void getProductsBy_when_return_products() {

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("correlationId","testCorrelationId");
        when(transactionIdentification.generateCorrelationId()).thenReturn(responseHeaders);

        ResponseEntity<String> responseEntity = wishListController.productsBy("testClientId");

        assertEquals(200, responseEntity.getStatusCodeValue());
        assertEquals("testCorrelationId", responseEntity.getHeaders().getFirst("correlationId"));

        verify(transactionIdentification, times(1)).generateCorrelationId();
    }
}