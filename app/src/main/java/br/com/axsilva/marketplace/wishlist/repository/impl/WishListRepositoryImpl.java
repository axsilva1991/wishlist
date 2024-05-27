package br.com.axsilva.marketplace.wishlist.repository.impl;

import br.com.axsilva.marketplace.wishlist.output_boundary.WishListOutPutBoundary;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductsResOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.exception.WishListEntityNotFoundException;
import br.com.axsilva.marketplace.wishlist.repository.mapper.InsertProductResEntityMapper;
import br.com.axsilva.marketplace.wishlist.repository.mapper.InsertProductResOutputBoundaryMapper;
import br.com.axsilva.marketplace.wishlist.repository.mapper.InsertWishListReqEntityMapper;
import br.com.axsilva.marketplace.wishlist.repository.mongo.IWishListRepository;
import com.mongodb.MongoException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WishListRepositoryImpl implements WishListOutPutBoundary {

    private static final Logger log = LoggerFactory.getLogger(WishListRepositoryImpl.class);

    private final IWishListRepository iWishListRepository;

    public WishListRepositoryImpl(
            IWishListRepository iWishListRepository
    ) {
        this.iWishListRepository = iWishListRepository;
    }

    @Override
    public void insertProduct(String clientId, InsertProductReqOutDto insertProductReqOut) {
        try {
            log.info("WishListRepositoryImpl.insertProducts(clientId, insertProductReqIn: {})", insertProductReqOut);
            var wishlist = getByClientId(clientId);
            var wishlistProducts = getByClientId(clientId).products();
            wishlist.products().add(new InsertProductResEntityMapper().inputDtoToEntity(insertProductReqOut));
            iWishListRepository.save(wishlist);
            //TODO Include ReferenceCodeDuplicated
        } catch (WishListEntityNotFoundException e) {
            iWishListRepository.insert(new InsertWishListReqEntityMapper().inputDtoToEntity(clientId, insertProductReqOut));
        } catch (MongoException e) {
            log.error("WishListRepositoryImpl.insertProduct({clientId}, insertProductReqIn: {}), An error occurred when querying the database.)", insertProductReqOut);
            throw new GenericRepositoryException();
        }
    }

    @Override
    public ListProductsResOutDto getProductsBy(String clientId) {
        log.info("WishListRepositoryImpl.getProductBy(clientId)");
        return new InsertProductResOutputBoundaryMapper().entityToOutputBoundaryResponse(getByClientId(clientId));
    }

    @Override
    public void checkIfIsOnBy(String clientId, String referenceCode) throws GenericRepositoryException {
        try {
            log.info("WishListRepositoryImpl.checkIfIsOnBy({clientId}, referenceCode: {})", referenceCode);
            boolean hasProduct = !getByClientId(clientId).products()
                    .stream()
                    .filter(productEntity -> productEntity.referenceCode().equals(referenceCode)).toList().isEmpty();
            if (!hasProduct) {
                log.error("WishListRepositoryImpl.checkIfIsOnBy({clientId}, referenceCode: {}) product isn't selected.", referenceCode);
                throw new ProductEntityNotFoundException();
            }
        } catch (WishListEntityNotFoundException e) {
            log.error("WishListRepositoryImpl.checkIfIsOnBy({clientId}, referenceCode: {}) no wishlist found for this customer.)", referenceCode);
            throw new ProductEntityNotFoundException();
        }
    }

    private WishListEntity getByClientId(String clientId) throws GenericRepositoryException {
        try {
            var wishlist = iWishListRepository.findByClientId(clientId);
            if (wishlist == null) {
                log.error("WishListRepositoryImpl.getByClientId({clientId}) no wishlist found for this customer.)");
                throw new WishListEntityNotFoundException();
            }
            return wishlist;
        } catch (MongoException e) {
            log.error("WishListRepositoryImpl.getByClientId({clientId}) An error occurred when querying the database.)");
            throw new GenericRepositoryException();
        }
    }

    @Override
    public void deleteProductBy(String clientId, String referenceCode) {
        try {
            var wishlist = getByClientId(clientId);
            var productsToRemove = wishlist.products().stream().filter(productEntity -> productEntity.referenceCode().equals(referenceCode)).toList();
            if (productsToRemove.size() > 0) {
                wishlist.products().removeAll(wishlist.products().stream().filter(productEntity -> productEntity.referenceCode().equals(referenceCode)).toList());
                iWishListRepository.save(wishlist);
                log.info("WishListUseCase.deleteProductBy({clientId}, referenceCode: {})", referenceCode);
            } else {
                log.error("WishListUseCase.deleteProductBy({clientId}, referenceCode: {}), products isn't finded for this reference", referenceCode);
                throw new ProductEntityNotFoundException();
            }
        } catch (MongoException e) {
            log.error("WishListRepositoryImpl.deleteProductBy({clientId}) An error occurred when deleteProductBy on database.)");
            throw new GenericRepositoryException();
        }
    }
}
