package br.com.axsilva.marketplace.wishlist.web.helper;

import br.com.axsilva.marketplace.wishlist.web.WishListController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionIdentification {

    private static Logger log = LoggerFactory.getLogger(WishListController.class);

    public HttpHeaders generateCorrelationId() {
        var correlationId = String.valueOf(UUID.randomUUID());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("correlationId",correlationId);
        return responseHeaders;
    }
}
