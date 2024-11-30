package org.music.app.codes.test.account.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;
import org.music.app.codes.account.model.data.Login;
import org.music.app.codes.account.repository.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import jakarta.transaction.Transactional;

@SpringBootTest
public class LoginRepositoryTest {

    @Autowired
    private LoginRepository loginRepository;

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByLoginUsername() {
        // Create an account 
        Login login = new Login();
        login.setLoginUsername("testuser");
        login.setLoginPassword("password123");
        loginRepository.save(login);

        // Find the login by username
        Login foundLogin = loginRepository.findByLoginUsername("testuser");
        assertNotNull(foundLogin, "Login should be found");
        assertEquals("testuser", foundLogin.getLoginUsername(), "Username should match");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testFindByLoginUsernameNotFound() {
        // Try to find a login by a non-existing username
        Login foundLogin = loginRepository.findByLoginUsername("nonexistentuser");
        assertNull(foundLogin, "Login should not be found");
    }

    @Test
    @Transactional
    @Rollback(true)
    public void testSaveLogin() {
        // Create a new Login instance
        Login login = new Login();
        login.setLoginUsername("newuser");
        login.setLoginPassword("newpassword123");

        // Save the login
        loginRepository.save(login);

        // Verify the login was saved
        Login savedLogin = loginRepository.findByLoginUsername("newuser");
        assertNotNull(savedLogin, "Login should be saved");
        assertEquals("newuser", savedLogin.getLoginUsername(), "Username should match");
    }
}