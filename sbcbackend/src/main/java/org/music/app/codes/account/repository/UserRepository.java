package org.music.app.codes.account.repository;

import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Users;
import org.springframework.stereotype.Repository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class UserRepository {
    public static final Logger LOGGER = LogManager.getLogger(UserRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean addUserProfile(Users user) {
        try {
            entityManager.persist(user);
            LOGGER.info("User profile added successfully: {} {}", user.getUserFirstname(), user.getUserLastname());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error adding user profile", e);
            return false;
        }
    }

    @Transactional
    public boolean updateUserProfile(Users updatedUser) {
        try {
            entityManager.merge(updatedUser);
            LOGGER.info("User profile updated for user ID: {}", updatedUser.getUserId());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error updating user profile", e);
            return false;
        }
    }

    @Transactional
    public List<Users> getAllProfiles() {
        try {
            List<Users> userList = entityManager.createQuery("SELECT u FROM Users u", Users.class).getResultList();
            LOGGER.info("Retrieved all user profiles, count: {}", userList.size());
            return userList;
        } catch (Exception e) {
            LOGGER.error("Error retrieving user profiles", e);
            return Collections.emptyList();
        }
    }

    @Transactional
    public boolean deleteUserProfile(Integer userId) {
        try {
            Users user = entityManager.find(Users.class, userId);
            if (user != null) {
                entityManager.remove(user);
                LOGGER.info("User profile deleted for user ID: {}", userId);
                return true;
            } else {
                LOGGER.warn("User profile not found for user ID: {}", userId);
                return false;
            }
        } catch (Exception e) {
            LOGGER.error("Error deleting user profile", e);
            return false;
        }
    }
    public Users findByLoginId(Integer loginId) {
        try {
            String jpql = "SELECT u FROM Users u JOIN u.login l WHERE l.loginId = :loginId";
            return entityManager.createQuery(jpql, Users.class)
                                .setParameter("loginId", loginId)
                                .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}