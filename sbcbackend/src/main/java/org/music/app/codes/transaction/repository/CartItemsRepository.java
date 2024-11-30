package org.music.app.codes.transaction.repository;

import java.util.List;

import org.music.app.codes.transaction.model.data.CartItems;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class CartItemsRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public CartItems save(CartItems cartItem) {
        if (cartItem.getCartItemId() == null) {
            entityManager.persist(cartItem);
            return cartItem;
        } else {
            return entityManager.merge(cartItem);
        }
    }

    public CartItems findById(Integer id) {
        return entityManager.find(CartItems.class, id);
    }

    public List<CartItems> findAll() {
        return entityManager.createQuery("SELECT c FROM CartItems c", CartItems.class).getResultList();
    }

    @Transactional
    public CartItems update(Integer id, CartItems cartItem) {
        CartItems existingCartItem = findById(id);
        if (existingCartItem != null) {
            if (cartItem.getCartQuantity() != null) {
                existingCartItem.setCartQuantity(cartItem.getCartQuantity());
            }
            if (cartItem.getCart() != null) {
                existingCartItem.setCart(cartItem.getCart());
            }
            if (cartItem.getAlbum() != null) {
                existingCartItem.setAlbum(cartItem.getAlbum());
            }
            return entityManager.merge(existingCartItem);
        }
        return null;
    }

    @Transactional
    public void delete(Integer id) {
        int rowsAffected = 
        		entityManager.createQuery("DELETE FROM CartItems c WHERE c.cartItemId = :id")
                                        .setParameter("id", id)
                                        .executeUpdate();
        if (rowsAffected == 0) {
            throw new EntityNotFoundException("CartItem with id " + id + " does not exist.");
        }
    }


    @Transactional
    public void deleteByUserId(Integer userId) {
        entityManager.createQuery("DELETE FROM CartItems c WHERE c.cart.users.userId = :userId")
                     .setParameter("userId", userId)
                     .executeUpdate();
    }
}