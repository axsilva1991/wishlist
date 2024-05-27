package br.com.axsilva.marketplace.wishlist.repository.impl;

import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.ProductEntity;
import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.exception.WishListEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.mongo.IWishListRepository;
import com.mongodb.MongoException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
class WishListRepositoryImplTest {

    @Mock
    private IWishListRepository iWishListRepository;

    @InjectMocks
    private WishListRepositoryImpl wishListRepository;

    private final String clientId = "testClientId";

    private final InsertProductReqOutDto insertProductReqOutDto = new InsertProductReqOutDto("", "", Double.MAX_VALUE);

    private final WishListEntity wishListEntity = new WishListEntity(
            UUID.randomUUID(),
            clientId,
            List.of(new ProductEntity(
                    UUID.randomUUID(),
                    "referenceCode",
                    "",
                    Double.MAX_VALUE)));

    @Test
    void insertProduct_when_insert_product_by_client_and_product_id_is_Ok() {
        when(iWishListRepository.insert(any(WishListEntity.class))).thenReturn(any());

        wishListRepository.insertProduct(clientId, insertProductReqOutDto);

        verify(iWishListRepository, times(1)).insert((any(WishListEntity.class)));
    }

    @Test
    void getProductsBy_when_get_product_by_client_is_Ok() {
        when(iWishListRepository.findByClientId(clientId)).thenReturn(wishListEntity);

        assertDoesNotThrow(() -> wishListRepository.getProductsBy(clientId));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void getProductsBy_when_get_product_by_client_when_the_client_is_not_found() {
        when(iWishListRepository.findByClientId(clientId)).thenReturn(null);

        assertThrows(WishListEntityNotFoundException.class, () -> wishListRepository.getProductsBy(clientId));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }
    @Test
    void checkIfIsOnBy_when_get_product_by_client_is_not_found() {
        when(iWishListRepository.findByClientId(clientId)).thenReturn(null);

        assertThrows(ProductEntityNotFoundException.class, () -> wishListRepository.checkIfIsOnBy(clientId, "referenceCode"));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void getProductsBy_when_get_product_by_client_when_there_is_a_mongo_exception() {
        when(iWishListRepository.findByClientId(clientId)).thenThrow(MongoException.class);

        assertThrows(GenericRepositoryException.class, () -> wishListRepository.getProductsBy(clientId));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void checkIfIsOnBy_when_get_product_by_client_when_there_is_a_mongo_exception() {
        when(iWishListRepository.findByClientId(clientId)).thenThrow(MongoException.class);

        assertThrows(GenericRepositoryException.class, () -> wishListRepository.checkIfIsOnBy(clientId, "referenceCode"));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void checkIfIsOnBy_when_get_product_by_client_is_selected() {
        when(iWishListRepository.findByClientId(clientId)).thenReturn(wishListEntity);

        wishListRepository.checkIfIsOnBy(clientId, "referenceCode");

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void checkIfIsOnBy_when_get_product_by_client_is_not_selected() {
        when(iWishListRepository.findByClientId(clientId)).thenReturn(wishListEntity);

        assertThrows(ProductEntityNotFoundException.class, () -> wishListRepository.checkIfIsOnBy(clientId, "referenceCodeIsNotSelected"));

        verify(iWishListRepository, times(1)).findByClientId(clientId);
    }

    @Test
    void insertProduct_when_get_product_by_client_when_there_is_a_mongo_exception() {

        var wishListEntityMutable = new WishListEntity(
                UUID.randomUUID(),
                clientId,
                mutableProducts());

        when(iWishListRepository.findByClientId(clientId)).thenReturn(wishListEntityMutable);

        when(iWishListRepository.save(any(WishListEntity.class))).thenThrow(MongoException.class);

        assertThrows(GenericRepositoryException.class, () -> wishListRepository.insertProduct(clientId, insertProductReqOutDto));

        verify(iWishListRepository, times(1)).save((any(WishListEntity.class)));
    }

    private static ArrayList<ProductEntity> mutableProducts() {
        var products = new ArrayList<ProductEntity>();
        products.add(new ProductEntity(
                UUID.randomUUID(),
                "referenceCode",
                "",
                Double.MAX_VALUE));
        return products;
    }


}