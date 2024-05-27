package br.com.axsilva.marketplace.wishlist.repository.mongo;

import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWishListRepository extends MongoRepository<WishListEntity, String> {

    WishListEntity findByClientId(String clientId);
}
