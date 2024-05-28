package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class InternalErrorException extends CustomException {
    public InternalErrorException (MessageError messageError) {
        super(messageError);
    }
}