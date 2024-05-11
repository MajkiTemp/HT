package tcom.group.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tcom.group.shopping.model.CartItem;
import tcom.group.shopping.model.CartItemAction;
import tcom.group.shopping.model.ShoppingCart;
import tcom.group.shopping.service.ShoppingCartService;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/cart")
public class ShoppingCartController {
    @Autowired
    private ShoppingCartService service;

    @GetMapping("/{customerId}/getCart")
    public ShoppingCart getCart(@PathVariable String customerId) {
        return service.getCartByCustomerId(customerId);
    }

    @PostMapping("/{customerId}/add")
    public ShoppingCart addItem(@PathVariable String customerId, @RequestBody CartItem item) {
        return service.addItem(customerId, item);
    }

    @DeleteMapping("/{customerId}/remove/{itemId}")
    public ShoppingCart removeItem(@PathVariable String customerId, @PathVariable String itemId) {
        return service.removeItem(customerId, itemId);
    }

    @PostMapping("/{customerId}/evict")
    public void evictCart(@PathVariable String customerId) {
        service.evictCart(customerId);
    }

    @GetMapping("/statistics")
    public List<ShoppingCart> getStatistics(@RequestParam String offerId,
                                            @RequestParam CartItemAction action,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate start,
                                            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end) {
        return service.getOfferStatistics(offerId, action, start.atStartOfDay(), end.atStartOfDay());
    }
}

