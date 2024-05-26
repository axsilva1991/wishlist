package br.com.axsilva.marketplace.wishlist.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
import java.util.UUID;

@Document(collection = "wishList")
public record WishListEntity(
        @Id
        @JsonProperty("id")
        UUID id,
        @JsonProperty("clientId")
        UUID client,
        @JsonProperty("products")
        List<ProductsEntity> products) {
}
