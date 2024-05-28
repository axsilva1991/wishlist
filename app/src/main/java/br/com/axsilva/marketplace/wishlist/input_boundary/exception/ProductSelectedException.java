package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ProductSelectedException extends BusinessException {
    public ProductSelectedException(MessageError messageError) {
        super(messageError);
    }
}
