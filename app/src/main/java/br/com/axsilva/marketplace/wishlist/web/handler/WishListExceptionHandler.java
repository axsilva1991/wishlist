package br.com.axsilva.marketplace.wishlist.web.handler;

import br.com.axsilva.marketplace.wishlist.input_boundary.exception.BusinessException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.CustomException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.InternalErrorException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductDeletedException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductLimitException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ProductSelectedException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.ValidateProductNotFoundException;
import br.com.axsilva.marketplace.wishlist.input_boundary.exception.WishListNotFoundException;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ErrorResWebDto;
import jakarta.validation.UnexpectedTypeException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

@ControllerAdvice
public class WishListExceptionHandler {
    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ErrorResWebDto> handleUnpronounceableEntity(
            RuntimeException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "INCONSISTENCE_REPORTED_DATA",
                "Inconsistency in the data reported"),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }
    @ExceptionHandler(value = {ProductLimitException.class, ProductSelectedException.class})
    protected ResponseEntity<ErrorResWebDto> handleUnpronounceableEntity(
            CustomException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                ex.getCustomErrorMessage().getCode(),
                ex.getCustomErrorMessage().getMessage()),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {UnexpectedTypeException.class})
    protected ResponseEntity<ErrorResWebDto> handleUnexpectedTypeException(
            UnexpectedTypeException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                "PRODUCTS_DATA_NOT_SENDED",
                "Please to verify products data sent and try again later."),
                HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(value = {ProductNotFoundException.class, WishListNotFoundException.class})
    protected ResponseEntity<ErrorResWebDto> handleCustomNotFoundError(
            CustomException ex, WebRequest request) {
        return new ResponseEntity<ErrorResWebDto>(new ErrorResWebDto(
                ex.getCustomErrorMessage().getCode(),
                ex.getCustomErrorMessage().getMessage()),
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

    @ExceptionHandler(value = {BusinessException.class, ValidationException.class, CustomException.class, HandlerMethodValidationException.class})
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
