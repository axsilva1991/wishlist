package br.com.axsilva.marketplace.wishlist.web.handler;

import br.com.axsilva.marketplace.wishlist.input_boundary.exception.CustomException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductSelectedException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ErrorResWebDto;
import jakarta.validation.UnexpectedTypeException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        assertEquals("Inconsistency in the data reported", errorResWebDto.message());
    }

    @Test
    public void testHandleHandleUnexpectedTypeException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnexpectedTypeException(
                new UnexpectedTypeException("Illegal State"), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCTS_DATA_NOT_SENDED", errorResWebDto.code());
        assertEquals("Please to verify products data sent and try again later.", errorResWebDto.message());
    }
    @Test
    public void testHandleProductSelectedException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnpronounceableEntity(
                new ProductSelectedException(MessageError.PRODUCT_SELECTED_ERROR), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals(MessageError.PRODUCT_SELECTED_ERROR.getCode(), errorResWebDto.code());
        assertEquals(MessageError.PRODUCT_SELECTED_ERROR.getMessage(), errorResWebDto.message());

    }


    @Test
    public void testHandleProductLimitException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleUnpronounceableEntity(
                new ProductSelectedException(MessageError.PRODUCT_LIMIT_REACHED), null);
        assertEquals(HttpStatus.UNPROCESSABLE_ENTITY, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals(MessageError.PRODUCT_LIMIT_REACHED.getCode(), errorResWebDto.code());
        assertEquals(MessageError.PRODUCT_LIMIT_REACHED.getMessage(), errorResWebDto.message());

    }
    @Test
    public void testHandleProductNotFoundErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleCustomNotFoundError(
                new WishListNotFoundException(MessageError.WISHLIST_NOT_FOUND_ERROR), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals(MessageError.WISHLIST_NOT_FOUND_ERROR.getCode(), errorResWebDto.code());
        assertEquals(MessageError.WISHLIST_NOT_FOUND_ERROR.getMessage(), errorResWebDto.message());
    }


    @Test
    public void testHandleWishListNotFoundErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleCustomNotFoundError(
                new ProductNotFoundException(MessageError.PRODUCT_NOT_FOUND_ERROR), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("PRODUCT_NOT_FOUND_ERROR", errorResWebDto.code());
        assertEquals("This product was not found.", errorResWebDto.message());
    }

    @Test
    public void testHandleValidateProductNotFoundErrorException() {
        ResponseEntity<HttpStatus> responseEntity = handler.handleValidateProductNotFoundError(
                new ValidateProductNotFoundException(MessageError.PRODUCT_NOT_FOUND_ERROR), null);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }


    @Test
    public void testHandleValidateProductProductDeletedErrorException() {
        ResponseEntity<HttpStatus> responseEntity = handler.handleValidateProductProductDeletedError(
                new ProductDeletedException(MessageError.NO_CONTENT), null);
        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
    }

    @Test
    public void testHandleBusinessErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleBusinessError(
                new CustomException(MessageError.BUSINESS_ERROR), null);
        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("BUSINESS_ERROR", errorResWebDto.code());
        assertEquals("The server cannot process this transaction due to business validation.", errorResWebDto.message());
    }


    @Test
    public void testHandleInternalErrorException() {
        ResponseEntity<ErrorResWebDto> responseEntity = handler.handleInternalError(
                new RuntimeException(), null);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        ErrorResWebDto errorResWebDto = responseEntity.getBody();
        assertNotNull(errorResWebDto);
        assertEquals("INTERNAL_SERVER_ERROR", errorResWebDto.code());
        assertEquals("The server encountered an unexpected condition, please try again later.", errorResWebDto.message());
    }


}