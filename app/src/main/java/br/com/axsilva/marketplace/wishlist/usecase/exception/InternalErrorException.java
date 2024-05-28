package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class InternalErrorException extends CustomException {
    public InternalErrorException (MessageError messageError) {
        super(messageError);
    }
}