package org.music.app.codes.account.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Role;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RoleRepository {
    public static final Logger LOGGER = LogManager.getLogger(RoleRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public boolean addRole(Role role) {
        try {
            entityManager.persist(role);
            LOGGER.info("Role added successfully: {}", role.getRoleName());
            return true;
        } catch (Exception e) {
            LOGGER.error("Error adding role", e);
            return false;
        }
    }
}
