package br.com.axsilva.marketplace.wishlist.usecase.exception;

import br.com.axsilva.marketplace.wishlist.usecase.utils.MessageError;

public class WishListNotFoundException extends CustomException {
    public WishListNotFoundException(MessageError messageError) {
        super(messageError);
    }
}