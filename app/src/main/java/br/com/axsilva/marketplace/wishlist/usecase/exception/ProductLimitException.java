package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ProductLimitException extends BusinessException {

    public ProductLimitException (MessageError messageError) {
        super(messageError);
    }
}