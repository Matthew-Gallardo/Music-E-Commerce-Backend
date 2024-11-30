package org.music.app.codes.account.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Role;
import org.music.app.codes.account.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleRestController {
	public static final Logger LOGGER = LogManager.getLogger(RoleRestController.class);

    @Autowired
    private RoleRepository repository;

    @PostMapping("/add")
    public boolean addRole(@RequestBody Role role) {
        try {
            LOGGER.info("Adding role: {}", role.getRoleName());
           return repository.addRole(role);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Adding role failed: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during addition of role", e);
            return false;
        }
    }

}
