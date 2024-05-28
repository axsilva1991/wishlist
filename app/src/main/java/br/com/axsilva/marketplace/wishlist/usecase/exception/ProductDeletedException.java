package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ProductDeletedException extends CustomException {
    public ProductDeletedException(MessageError message) {
        super(message);
    }
}