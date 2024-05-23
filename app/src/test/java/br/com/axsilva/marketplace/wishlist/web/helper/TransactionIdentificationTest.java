package br.com.axsilva.marketplace.wishlist.web.helper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;

import java.util.UUID;


import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class TransactionIdentificationTest {

    @InjectMocks
    private TransactionIdentification transactionIdentification;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void generateCorrelationId_when_return_correlationCode() {
        HttpHeaders headers = transactionIdentification.generateCorrelationId();

        assertNotNull(headers);
        assertTrue(headers.containsKey("correlationId"));

        String correlationId = headers.getFirst("correlationId");
        assertNotNull(correlationId);
        assertTrue(isValidUUID(correlationId));
    }

    private boolean isValidUUID(String uuidStr) {
        try {
            UUID.fromString(uuidStr);
            return true;
        } catch (IllegalArgumentException ex) {
            return false;
        }
    }
}