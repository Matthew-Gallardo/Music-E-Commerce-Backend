package org.music.app.codes.account.repository;

import org.music.app.codes.account.model.data.Login;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;

@Repository
public class LoginRepository {

    @Autowired
    private EntityManager entityManager;

    public Login findByLoginUsername(String username) {
        try {
            String jpql = "SELECT l FROM Login l WHERE l.loginUsername = :username";
            TypedQuery<Login> query = entityManager.createQuery(jpql, Login.class);
            query.setParameter("username", username);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Transactional
    public void save(Login login) {
        entityManager.persist(login);
    }
}