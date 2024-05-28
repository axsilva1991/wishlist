package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class CustomException extends RuntimeException {
    public CustomException(MessageError message) {
        super(message.getCode());
    }
}