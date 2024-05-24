package br.com.axsilva.marketplace.wishlist.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductController {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    @PostMapping("/v1/products/{clientId}")
    public ResponseEntity<HttpStatus> insertProducts(@RequestBody String clientId) {
        log.info("POST - /v1/products/{clientId}");
        return new ResponseEntity(HttpStatus.CREATED);
    }
    @GetMapping("/v1/products/{clientId}")
    public ResponseEntity<String> getProductsBy(@PathVariable("clientId") String clientId) {
        log.info("GET - /v1/products/{clientId}");
        return ResponseEntity.ok()
                .body("products");
    }

    @GetMapping("/v1/products")
    public ResponseEntity<String> getProductBy(
            @RequestParam("clientId") String clientId,
            @RequestParam("productId") String productId) {
        log.info("GET - /v1/products/{clientId}?productId={}", productId);
        return ResponseEntity.ok()
                .body("product");
    }

    @DeleteMapping("/v1/products/{clientId}")
    public ResponseEntity<HttpStatus> deleteProductBy(
            @PathVariable("clientId") String clientId,
            @RequestParam("productId") String productId) {
        log.info("DELETE - /v1/products/{clientId}?productId={}", productId);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
