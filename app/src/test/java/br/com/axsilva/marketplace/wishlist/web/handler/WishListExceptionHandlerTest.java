package br.com.axsilva.marketplace.wishlist.web.handler;

import br.com.axsilva.marketplace.wishlist.usecase.exception.CustomException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductSelectedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ErrorResWebDto;
import jakarta.validation.UnexpectedTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
class WishListExceptionHandlerTest {

    private WishListExceptionHandler handler;

    @BeforeEach
    public void setUp() {
        handler = new WishListExceptionHandler();
    }

    @Test
    public void testHandleUnpronounceableEntity() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnpronounceableEntity(
                new IllegalStateException("Illegal State"), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("INCONSISTENCE_REPORTED_DATA", errorResWebDto.code());
        assertEquals("Inconsistency in the data reported", errorResWebDto.title());
    }

    @Test
    public void testHandleHandleUnexpectedTypeException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnexpectedTypeException(
                new UnexpectedTypeException("Illegal State"), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCTS_DATA_NOT_SENDED", errorResWebDto.code());
        assertEquals("Please to verify products data sent and try again later.", errorResWebDto.title());
    }
    @Test
    public void testHandleProductSelectedException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnpronounceableEntity(
                new ProductSelectedException(), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCT_SELECTED_ERROR", errorResWebDto.code());
        assertEquals("This product already selected.", errorResWebDto.title());

    }

    @Test
    public void testHandleProductNotFoundErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleProductNotFoundError(
                new WishListNotFoundException(), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("WISHLIST_NOT_FOUND_ERROR", errorResWebDto.code());
        assertEquals("This wishlist was not found.", errorResWebDto.title());
    }


    @Test
    public void testHandleWishListNotFoundErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleWishListNotFoundError(
                new ProductNotFoundException(), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCT_NOT_FOUND_ERROR", errorResWebDto.code());
        assertEquals("This product was not found.", errorResWebDto.title());
    }

    @Test
    public void testHandleValidateProductNotFoundErrorException() {
        ResponseEntity<HttpStatus> responseEntity = handler.handleValidateProductNotFoundError(
                new ValidateProductNotFoundException(), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    public void testHandleValidateProductProductDeletedErrorException() {
        ResponseEntity<HttpStatus> responseEntity = handler.handleValidateProductProductDeletedError(
                new ProductDeletedException(), null);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleBusinessErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleBusinessError(
                new CustomException(), null);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("BUSINESS_ERROR", errorResWebDto.code());
        assertEquals("The server cannot process this transaction due to business validation.", errorResWebDto.title());
    }


    @Test
    public void testHandleInternalErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleInternalError(
                new RuntimeException(), null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("INTERNAL_SERVER_ERROR", errorResWebDto.code());
        assertEquals("The server encountered an unexpected condition, please try again later.", errorResWebDto.title());
    }

    @Test
    public void testHandleProductLimitException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleProductLimitException(
                new ProductSelectedException(), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCT_LIMIT_REACHED", errorResWebDto.code());
        assertEquals("Product limit reached, please verify your wishlist to insert new product's.", errorResWebDto.title());
    }

}