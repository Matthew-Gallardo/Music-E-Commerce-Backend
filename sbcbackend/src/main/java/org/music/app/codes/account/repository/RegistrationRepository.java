package org.music.app.codes.account.repository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Login;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class RegistrationRepository {
    public static final Logger LOGGER = LogManager.getLogger(RegistrationRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void registerUser(Login login) {
        try {
            entityManager.persist(login);
            LOGGER.info("User registered successfully: {}", login.getLoginUsername());
        } catch (Exception e) {
            LOGGER.error("Error registering user", e);
            throw e;
        }
    }
}
