package br.com.axsilva.marketplace.wishlist.integration;

import com.intuit.karate.junit5.Karate;

public class WishListIntegrationTestRunner {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

}
