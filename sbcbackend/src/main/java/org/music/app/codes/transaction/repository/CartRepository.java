package org.music.app.codes.transaction.repository;

import org.music.app.codes.transaction.model.data.Cart;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

@Repository
public class CartRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public Cart save(Cart cart) {
        if (cart.getCartId() == null) {
            entityManager.persist(cart);
            return cart;
        } else {
            return entityManager.merge(cart);
        }
    }

    public Cart findById(Integer id) {
        return entityManager.find(Cart.class, id);
    }

    public Cart findByUserId(Integer userId) {
        try {
            return entityManager.createQuery("SELECT c FROM Cart c WHERE c.users.userId = :userId", Cart.class)
                                .setParameter("userId", userId)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    public List<Cart> findAll() {
        return entityManager.createQuery("SELECT c FROM Cart c", Cart.class).getResultList();
    }

    @Transactional
    public Cart update(Integer id, Cart cart) {
        Cart existingCart = findById(id);
        if (existingCart != null) {
            existingCart.setCreatedAt(cart.getCreatedAt());
            existingCart.setUsers(cart.getUsers());
            return entityManager.merge(existingCart);
        }
        return null;
    }

    @Transactional
    public void delete(Integer id) {
        Cart cart = findById(id);
        if (cart != null) {
            entityManager.remove(cart);
        }
    }
}
