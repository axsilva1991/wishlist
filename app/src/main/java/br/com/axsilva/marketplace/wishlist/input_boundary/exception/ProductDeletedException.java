package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ProductDeletedException extends CustomException {
    public ProductDeletedException(MessageError message) {
        super(message);
    }
}