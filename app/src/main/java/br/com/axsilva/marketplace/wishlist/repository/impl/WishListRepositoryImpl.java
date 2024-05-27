package br.com.axsilva.marketplace.wishlist.repository.impl;

import br.com.axsilva.marketplace.wishlist.output_boundary.WishListOutPutBoundary;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.request.InsertProductReqOutDto;
import br.com.axsilva.marketplace.wishlist.output_boundary.dto.response.ListProductsResOutDto;
import br.com.axsilva.marketplace.wishlist.repository.entity.ProductEntity;
import br.com.axsilva.marketplace.wishlist.repository.entity.WishListEntity;
import br.com.axsilva.marketplace.wishlist.repository.exception.GenericRepositoryException;
import br.com.axsilva.marketplace.wishlist.repository.exception.ProductAlreadySelectedException;
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

import java.util.List;

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
            if(filterProductsWith(insertProductReqOut.referenceCode(), wishlist).size() > 0)
                throw new ProductAlreadySelectedException();
            wishlist.products().add(new InsertProductResEntityMapper().inputDtoToEntity(insertProductReqOut));
            iWishListRepository.save(wishlist);
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
            if (filterProductsWith(referenceCode, getByClientId(clientId)).isEmpty()) {
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
            log.info("WishListUseCase.deleteProductBy({clientId}, referenceCode: {}, is sucess)", referenceCode);
            var wishlist = getByClientId(clientId);
            if (filterProductsWith(referenceCode, wishlist).size() > 0) {
                wishlist.products().removeAll(filterProductsWith(referenceCode, wishlist));
                iWishListRepository.save(wishlist);
            } else {
                log.error("WishListUseCase.deleteProductBy({clientId}, referenceCode: {}), products isn't finded for this reference", referenceCode);
                throw new ProductEntityNotFoundException();
            }
        } catch (MongoException e) {
            log.error("WishListRepositoryImpl.deleteProductBy({clientId}) An error occurred when deleteProductBy on database.)");
            throw new GenericRepositoryException();
        }
    }

    private static List<ProductEntity> filterProductsWith(String referenceCode, WishListEntity wishlist) {
        return wishlist.products().stream().filter(productEntity -> productEntity.referenceCode().equals(referenceCode)).toList();
    }
}
