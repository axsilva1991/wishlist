package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class CustomException extends RuntimeException {
    
    private final MessageError customErrorMessage;
    
    public CustomException(MessageError message) {
        super(message.getCode());
        customErrorMessage = message;
    }

    public MessageError getCustomErrorMessage() {
        return customErrorMessage;
    }
}