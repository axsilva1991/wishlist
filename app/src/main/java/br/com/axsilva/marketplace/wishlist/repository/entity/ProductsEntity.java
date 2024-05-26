package br.com.axsilva.marketplace.wishlist.repository.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product")
public record ProductsEntity(@Id
                             @JsonProperty("id")
                             Long id,
                             @JsonProperty("referenceCode")
                             Long referenceCode,
                             @JsonProperty("referenceStore")
                             String referenceStore,
                             @JsonProperty("selectedPrice")
                             Double selectedPrice) {
}