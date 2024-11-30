package org.music.app.codes.transaction.service;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.product.model.data.Album;
import org.music.app.codes.product.repository.AlbumRepository;
import org.music.app.codes.transaction.model.data.Cart;
import org.music.app.codes.transaction.model.data.CartItems;
import org.music.app.codes.transaction.repository.CartItemsRepository;
import org.music.app.codes.transaction.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartItemsService {
	
	private static final Logger LOGGER = LogManager.getLogger(CartItemsService.class);

    @Autowired
    private CartItemsRepository cartItemsRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private AlbumRepository albumRepository;

    public CartItems createCartItem(CartItems cartItem) {
        // Fetch the album using the album ID
        Album album = albumRepository.findAlbumById(cartItem.getAlbum().getAlbumId());
        cartItem.setAlbum(album);

        Cart cart = cartRepository.findById(cartItem.getCart().getCartId());
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found with ID: " + cartItem.getCart().getCartId());
        }
        cartItem.setCart(cart);
        cartItem.setCartQuantity(cartItem.getCartQuantity());

        CartItems savedCartItem = cartItemsRepository.save(cartItem);
        System.out.println("cartQuantity after save: " + savedCartItem.getCartQuantity());

        return savedCartItem;
    }

    public CartItems getCartItemById(Integer id) {
        return cartItemsRepository.findById(id);
    }

    public List<CartItems> getAllCartItems() {
        return cartItemsRepository.findAll();
    }

    public CartItems updateCartItem(Integer id, CartItems cartItem) {
        return cartItemsRepository.update(id, cartItem);
    }
    public void deleteCartItem(Integer id) {
    	LOGGER.info("Deleting cart item with ID: {}", id);
        cartItemsRepository.delete(id);
        LOGGER.info("Deleted cart item with ID {}",id);
    }


    public void deleteCartItemsByUserId(Integer userId) {
        cartItemsRepository.deleteByUserId(userId);
     
    }
}