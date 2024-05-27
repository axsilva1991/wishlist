package br.com.axsilva.marketplace.wishlist.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Document(collection = "product")
public record ProductEntity(
        @Id
        UUID id,
        @JsonProperty("referenceCode")
        String referenceCode,
        @JsonProperty("referenceStore")
        String referenceStore,
        @JsonProperty("selectedPrice")
        Double selectedPrice) {
}