package br.com.axsilva.marketplace.wishlist.usecase.utils;

public enum MessageError {
    INCONSISTENCY_REPORTED_DATA("INCONSISTENCY_REPORTED_DATA", "Inconsistency in the data reported"),
    PRODUCT_LIMIT_REACHED("PRODUCT_LIMIT_REACHED", "Product limit reached, please verify your wishlist before insert new product's."),
    PRODUCTS_DATA_NOT_SENDED("PRODUCTS_DATA_NOT_SENDED", "Please to verify products data sent and try again later."),
    WISHLIST_NOT_FOUND_ERROR("WISHLIST_NOT_FOUND_ERROR", "This wishlist was not found or empty."),
    PRODUCT_NOT_FOUND_ERROR("PRODUCT_NOT_FOUND_ERROR", "This product was not found."),
    BUSINESS_ERROR("BUSINESS_ERROR", "The server cannot process this transaction due to business validation."),
    INTERNAL_SERVER_ERROR("INTERNAL_SERVER_ERROR", "The server encountered an unexpected condition, please try again later."),
    PRODUCT_SELECTED_ERROR("PRODUCT_SELECTED_ERROR","This product already selected."),
    PRODUCT_NOT_FOUND("PRODUCT_NOT_FOUND","PRODUCT_NOT_FOUND"),
    NO_CONTENT("NO_CONTENT","NO_CONTENT");

    public String getCode() {
        return code;
    }

    private final String code;

    public String getMessage() {
        return message;
    }

    private final String message;

    MessageError(String code, String message) {
        this.code = code;
        this.message = message;
    }
}