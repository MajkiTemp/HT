package tcom.group.shopping.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import tcom.group.shopping.exception.ProductNotFoundException;
import tcom.group.shopping.model.CartItem;
import tcom.group.shopping.model.CartItemAction;
import tcom.group.shopping.model.ShoppingCart;
import tcom.group.shopping.repository.ProductRepository;
import tcom.group.shopping.repository.ShoppingCartRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ProductRepository productRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartItemRepository, ProductRepository productRepository) {
        this.shoppingCartRepository = shoppingCartItemRepository;
        this.productRepository = productRepository;
    }


    public ShoppingCart getCartByCustomerId(String customerId) {
        return shoppingCartRepository.findById(customerId).orElse(new ShoppingCart(customerId, new ArrayList<>()));
    }

    public ShoppingCart addItem(String customerId, CartItem item) {
            validateShoppingCart(item);
            ShoppingCart cart = getCartByCustomerId(customerId);
            cart.getItems().add(item);
            return shoppingCartRepository.save(cart);


    }


    public ShoppingCart removeItem(String customerId, String itemId) {
        ShoppingCart cart = getCartByCustomerId(customerId);
        cart.setItems(cart.getItems().stream().filter(i -> !i.getId().equals(itemId)).collect(Collectors.toList()));
        return shoppingCartRepository.save(cart);
    }

    public void evictCart(String customerId) {
        shoppingCartRepository.deleteById(customerId);
    }

    public List<ShoppingCart> getOfferStatistics(String offerId, CartItemAction action, LocalDateTime start, LocalDateTime end) {
        return shoppingCartRepository.findByItemsOfferIdAndItemsActionAndItemsAddedAtBetween(offerId, action, start, end);

    }


    public void validateShoppingCart(CartItem item){

        if (item.getPrice()== null) {
            throw new IllegalArgumentException("Price cannot be null for a shopping cart item");
        }
        if (item.getPrice().getRecurrences() <= 0 && item.getPrice().getValue() <= 0) {
            throw new IllegalArgumentException("item should have at least one price bigger then 0");
        }

        if (!productRepository.existsById(item.getId())) {
            throw new ProductNotFoundException("Product ID does not exist in the product database: " + item.getId());
        }

        if (item.getOfferId() == null || item.getOfferId().isEmpty()) {
            throw new IllegalArgumentException("OfferId cannot be null or empty");
        }

    }
}

