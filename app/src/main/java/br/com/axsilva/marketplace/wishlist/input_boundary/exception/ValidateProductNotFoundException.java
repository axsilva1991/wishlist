package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class ValidateProductNotFoundException extends CustomException {
    public ValidateProductNotFoundException(MessageError messageError) {
        super(messageError);
    }
}