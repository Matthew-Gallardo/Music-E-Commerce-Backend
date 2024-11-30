package org.music.app.codes.account.repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Permission;
import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

@Repository
public class PermissionRepository {
	 public static final Logger LOGGER = LogManager.getLogger(PermissionRepository.class);

	    @PersistenceContext
	    private EntityManager entityManager;

	    @Transactional
	    public boolean addPermission(Permission permission ) {
	        try {
	            entityManager.persist(permission);
	            LOGGER.info("Permission added successfully: {}", permission.getPermissionName());
	            return true;
	        } catch (Exception e) {
	            LOGGER.error("Error adding permission", e);
	            return false;
	        }
	    }

}
