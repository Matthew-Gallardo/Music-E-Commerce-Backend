package org.music.app.codes.account.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Permission;
import org.music.app.codes.account.repository.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/permission")
public class PermissionRestController {
	public static final Logger LOGGER = LogManager.getLogger(PermissionRestController.class);

    @Autowired
    private PermissionRepository repository;

    @PostMapping("/add")
    public boolean addPermission(@RequestBody Permission permission) {
        try {
            LOGGER.info("Adding permission: {}", permission.getPermissionName());
           return repository.addPermission(permission);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Adding permission failed: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during addition of permission", e);
            return false;
        }
    }

}