package br.com.axsilva.marketplace.wishlist.web.helper;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class TransactionIdentification {

    public HttpHeaders generateCorrelationId() {
        var correlationId = String.valueOf(UUID.randomUUID());
        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.set("correlationId",correlationId);
        return responseHeaders;
    }
}
