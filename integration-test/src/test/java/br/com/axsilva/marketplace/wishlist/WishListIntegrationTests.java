package br.com.axsilva.marketplace.wishlist;

import com.intuit.karate.junit5.Karate;

public class WishListIntegrationTests {

    @Karate.Test
    Karate testAll() {
        return Karate.run().relativeTo(getClass());
    }

}
