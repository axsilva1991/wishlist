package br.com.axsilva.marketplace.wishlist.web;

import br.com.axsilva.marketplace.wishlist.input_boundary.WishListInputBoundary;
import br.com.axsilva.marketplace.wishlist.web.dto.InsertProductReqWeb;
import br.com.axsilva.marketplace.wishlist.web.dto.response.ListProductsResWebDto;
import br.com.axsilva.marketplace.wishlist.web.mapper.InsertProductReqInputBoundaryMapper;
import br.com.axsilva.marketplace.wishlist.web.mapper.ListProductsResWebMapper;
import br.com.axsilva.marketplace.wishlist.web.openapi.WishListOpenApi;
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
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/v1/wishlist/products")
@RestController
public class ProductController implements WishListOpenApi {
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);
    private static final String PATH = "/v1/wishlist/products";
    private final WishListInputBoundary wishListInputBoundary;

    public ProductController(WishListInputBoundary wishListInputBoundary) {
        this.wishListInputBoundary = wishListInputBoundary;
    }


    @PostMapping("/{clientId}")
    public ResponseEntity<HttpStatus> insertProduct(
            @PathVariable("clientId") String clientId,
            @RequestBody InsertProductReqWeb insertProductReqWeb) {
        log.info("POST - {}/{clientId}", PATH);
        wishListInputBoundary.insertProduct(
                clientId,
                InsertProductReqInputBoundaryMapper.INSTANCE.webDtoToInputBoundary(insertProductReqWeb));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @GetMapping("/{clientId}")
    public ResponseEntity<ListProductsResWebDto> getProductsBy(@PathVariable("clientId") String clientId) {
        log.info("GET - {}/{clientId}", PATH);
        return ResponseEntity.ok()
                .body(ListProductsResWebMapper.INSTANCE.inputBoundaryToWebDto(wishListInputBoundary.getProductsBy(clientId)));
    }

    @GetMapping("/selected")
    public ResponseEntity<HttpStatus> checkIfIsOnBy(
            @RequestParam("clientId") String clientId,
            @RequestParam("productId") String productReferenceCode) {
        log.info("GET - {}/exists/{clientId}?productReferenceCode={}", PATH, productReferenceCode);
        wishListInputBoundary.checkIfIsOnBy(clientId, productReferenceCode);
        return new ResponseEntity(HttpStatus.OK);

    }

    @DeleteMapping("/{clientId}")
    public ResponseEntity<HttpStatus> deleteProductBy(
            @PathVariable("clientId") String clientId,
            @RequestParam("productId") String productReferenceCode) {
        log.info("DELETE - {}/{clientId}?productReferenceCode={}", PATH, productReferenceCode);
        wishListInputBoundary.deleteProductBy(clientId, productReferenceCode);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
