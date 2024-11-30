package org.music.app.codes.transaction.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.transaction.model.data.CartItems;
import org.music.app.codes.transaction.service.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cart-items")
public class CartItemsController {
	private static final Logger LOGGER = LogManager.getLogger(CartItemsController.class);

    @Autowired
    private CartItemsService cartItemsService;

    @PostMapping
    public ResponseEntity<CartItems> createCartItem(@RequestBody CartItems cartItem) {
        // Log the incoming cartItem to debug
    	LOGGER.info("Received cartItem {}", cartItem);
    	LOGGER.info("cartQuantity: {} ", cartItem.getCartQuantity());

        CartItems createdCartItem = cartItemsService.createCartItem(cartItem);
        return ResponseEntity.ok(createdCartItem);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItems> getCartItemById(@PathVariable Integer id) {
        CartItems cartItem = cartItemsService.getCartItemById(id);
        return ResponseEntity.ok(cartItem);
    }

    @GetMapping
    public ResponseEntity<List<CartItems>> getAllCartItems() {
        List<CartItems> cartItems = cartItemsService.getAllCartItems();
        return ResponseEntity.ok(cartItems);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CartItems> updateCartItem(@PathVariable Integer id, @RequestBody CartItems cartItem) {
        CartItems updatedCartItem = cartItemsService.updateCartItem(id, cartItem);
        return ResponseEntity.ok(updatedCartItem);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCartItem(@PathVariable Integer id) {

    	LOGGER.info("Attempting to delete from controller , ID {}", id);
        cartItemsService.deleteCartItem(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/user/{userId}")
    public ResponseEntity<Void> deleteCartItemsByUserId(@PathVariable Integer userId) {
        cartItemsService.deleteCartItemsByUserId(userId);
        return ResponseEntity.noContent().build();
    }
}
