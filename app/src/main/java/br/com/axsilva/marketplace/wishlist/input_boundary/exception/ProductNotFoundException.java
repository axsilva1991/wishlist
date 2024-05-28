package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ProductNotFoundException extends CustomException {
    public ProductNotFoundException (MessageError messageError) {
        super(messageError);
    }
}