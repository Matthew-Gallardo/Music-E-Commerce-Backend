package org.music.app.codes.test.account.repository;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.music.app.codes.account.model.data.Login;
import org.music.app.codes.account.model.data.Role;
import org.music.app.codes.account.model.data.Users;
import org.music.app.codes.account.repository.RegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@SpringBootTest
public class RegistrationRepositoryTest {

    @Autowired
    private RegistrationRepository registrationRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @Transactional
    @Rollback(true)
    public void testRegisterUser() {
        // Create a new Users instance
        Users user = new Users();
        user.setUserFirstname("John");
        user.setUserLastname("Doe");
        user.setUserEmail("john.doe@example.com");
        user.setUserMobile("1234567890");
        user.setUserStreet("123 Main St");
        user.setUserCity("Anytown");
        user.setUserState("Anystate");
        user.setUserZipcode("12345");
        user.setUserCountry("USA");
        user.setUserBillingAddress("123 Main St");
        user.setUserShippingAddress("123 Main St");
        entityManager.persist(user);

        // Create a new Role instance
        Role role = new Role();
        role.setRoleName("USER");
        role.setRoleDescription("Standard user role"); // Set the roleDescription
        entityManager.persist(role);

        // Create a new Login instance
        Login login = new Login();
        login.setLoginUsername("johndoe");
        login.setLoginPassword("password123");
        login.setUsers(user);
        login.setRole(role);

        // Register the user
        registrationRepository.registerUser(login);

        // Verify the user was registered
        Login registeredLogin = entityManager.find(Login.class, login.getLoginId());
        assertNotNull(registeredLogin, "Registered login should not be null");
        assertEquals("johndoe", registeredLogin.getLoginUsername(), "Username should match");
        assertEquals("john.doe@example.com", registeredLogin.getUsers().getUserEmail(), "User email should match");
    }
}