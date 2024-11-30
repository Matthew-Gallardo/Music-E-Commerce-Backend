package org.music.app.codes.transaction.service;

import org.music.app.codes.transaction.model.data.Cart;
import org.music.app.codes.transaction.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    public Cart getCartById(Integer id) {
        return cartRepository.findById(id);
    }

    public Cart getCartByUserId(Integer userId) {
        return cartRepository.findByUserId(userId);
    }

    public List<Cart> getAllCarts() {
        return cartRepository.findAll();
    }

    public Cart updateCart(Integer id, Cart cart) {
        return cartRepository.update(id, cart);
    }

    public void deleteCart(Integer id) {
        cartRepository.delete(id);
    }
}