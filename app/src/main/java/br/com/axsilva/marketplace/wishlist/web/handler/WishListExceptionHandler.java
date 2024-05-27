package br.com.axsilva.marketplace.wishlist.web.handler;

import br.com.axsilva.marketplace.wishlist.usecase.exception.BusinessException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.CustomException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.InternalErrorException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductLimitException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ProductSelectedException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.usecase.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ErrorResWebDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class WishListExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ErrorResWebDto> handleUnpronounceableEntity(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "INCONSISTENCE_REPORTED_DATA",
                "inconsistency in the data reported"),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ProductSelectedException.class})
    protected ResponseEntity<ErrorResWebDto> handleUnpronounceableEntity(
            ProductSelectedException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "PRODUCT_SELECTED_ERROR",
                "This product already selected."),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = {ProductLimitException.class})
    protected ResponseEntity<ErrorResWebDto> handleProductLimitException(
            ProductSelectedException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "PRODUCT_LIMIT_REACHED",
                "Product limit reached, please verify your wishlist to insert new product's."),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {WishListNotFoundException.class})
    protected ResponseEntity<ErrorResWebDto> handleProductNotFoundError(
            WishListNotFoundException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "WISHLIST_NOT_FOUND_ERROR",
                "This wishlist was not found."),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class})
    protected ResponseEntity<ErrorResWebDto> handleWishListNotFoundError(
            ProductNotFoundException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "PRODUCT_NOT_FOUND_ERROR",
                "This product was not found."),
                HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ValidateProductNotFoundException.class})
    protected ResponseEntity<HttpStatus> handleValidateProductNotFoundError(
            ValidateProductNotFoundException ex, WebRequest request) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = {ProductDeletedException.class})
    protected ResponseEntity<HttpStatus> handleValidateProductProductDeletedError(
            ProductDeletedException ex, WebRequest request) {
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

    @ExceptionHandler(value = {BusinessException.class, CustomException.class})
    protected ResponseEntity<ErrorResWebDto> handleBusinessError(
            CustomException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "BUSINESS_ERROR",
                "The server cannot process this transaction due to business validation."),
                HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = {InternalErrorException.class, RuntimeException.class})
    protected ResponseEntity<ErrorResWebDto> handleInternalError(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "INTERNAL_SERVER_ERROR",
                "The server encountered an unexpected condition, please try again later."),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
