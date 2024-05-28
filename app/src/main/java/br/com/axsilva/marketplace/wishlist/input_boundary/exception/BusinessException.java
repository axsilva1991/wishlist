package br.com.axsilva.marketplace.wishlist.input_boundary.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class BusinessException extends CustomException {
    BusinessException(MessageError message) {
        super(message);
    }
}