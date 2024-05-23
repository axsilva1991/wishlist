package br.com.axsilva.marketplace.wishlist.web;

import br.com.axsilva.marketplace.wishlist.web.helper.TransactionIdentification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WishListController {
    private static final Logger log = LoggerFactory.getLogger(WishListController.class);

    @Autowired
    private TransactionIdentification transactionIdentification;

    @GetMapping("/v1/products/{clientId}")
    public ResponseEntity<String> productsBy(@PathVariable("clientId") String clientId) {
        var correlationId = transactionIdentification.generateCorrelationId();
        log.info("/v1/products/{clientId} by {}", correlationId);
        return ResponseEntity.ok()
                .headers(correlationId)
                .body("products");
    }

}
