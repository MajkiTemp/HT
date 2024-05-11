package tcom.group.shopping.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import tcom.group.shopping.model.CartItemAction;
import tcom.group.shopping.model.ShoppingCart;

import java.time.LocalDateTime;
import java.util.List;

public interface ShoppingCartRepository extends MongoRepository<ShoppingCart, String> {

    List<ShoppingCart> findByItemsOfferIdAndItemsActionAndItemsAddedAtBetween(
            String offerId, CartItemAction action, LocalDateTime start, LocalDateTime end);
}


