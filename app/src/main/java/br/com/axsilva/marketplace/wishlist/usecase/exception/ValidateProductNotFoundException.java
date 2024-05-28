package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ValidateProductNotFoundException extends CustomException {
    public ValidateProductNotFoundException(MessageError messageError) {
        super(messageError);
    }
}