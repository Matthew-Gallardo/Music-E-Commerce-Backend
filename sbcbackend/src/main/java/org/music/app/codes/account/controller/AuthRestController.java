package org.music.app.codes.account.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Login;
import org.music.app.codes.account.model.data.Users;
import org.music.app.codes.account.model.forms.ForgotPasswordForm;
import org.music.app.codes.account.model.forms.LoginForm;
import org.music.app.codes.account.model.forms.RegisterForm;
import org.music.app.codes.account.repository.LoginRepository;
import org.music.app.codes.account.services.AccountService;
import org.music.app.codes.transaction.model.data.Cart;
import org.music.app.codes.transaction.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class AuthRestController {
    public static final Logger LOGGER = LogManager.getLogger(AuthRestController.class);

    @Autowired
    private AccountService accountService;

    @Autowired
    private LoginRepository loginRepository;

    @Autowired
    private CartRepository cartRepository;

    @PostMapping("/register")
    public boolean registerAccount(@RequestBody RegisterForm form) {
        try {
            LOGGER.info("Registration request for user: {}", form.getUsername());
            return accountService.registerAccount(form);
        } catch (IllegalArgumentException e) {
            LOGGER.error("Registration failed: {}", e.getMessage());
            return false;
        } catch (Exception e) {
            LOGGER.error("Unexpected error during registration", e);
            return false;
        }
    }
    
    @PostMapping("/forgot-password")
    public ResponseEntity<String> validateSecurityQuestion(@RequestBody ForgotPasswordForm form) {
        boolean isValid = accountService.validateSecurityQuestion(form);
        
        if (isValid) {
            return ResponseEntity.ok("Validation successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid security question or answer");
        }
    }
    
    @PostMapping("/login")
    public ResponseEntity<String> loginAccount(@RequestBody LoginForm form, HttpSession session) {
        boolean isAuthenticated = accountService.verifyCredentials(form);

        if (isAuthenticated) {
            Login login = loginRepository.findByLoginUsername(form.getUsername());
            session.setAttribute("loginId", login.getLoginId());
            session.setAttribute("userId", login.getUsers().getUserId());


            Users user = login.getUsers();
            Cart cart = cartRepository.findByUserId(user.getUserId());
            if (cart == null) {
                cart = new Cart();
                cart.setUsers(user);
                cartRepository.save(cart);
                LOGGER.info("Created new cart with ID: {} for user ID: {}", cart.getCartId(), user.getUserId());
            } else {
                LOGGER.info("Fetched existing cart with ID: {} for user ID: {}", cart.getCartId(), user.getUserId());
            }
            session.setAttribute("cartId", cart.getCartId());

            LOGGER.info("Login success for user {}", form.getUsername());
            return ResponseEntity.ok("Login successful");
        } else {
            LOGGER.error("Login failed for user {}", form.getUsername());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid username or password");
        }
    }
    
    @GetMapping("/session")
    public ResponseEntity<?> getProfile(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        Integer cartId = (Integer) session.getAttribute("cartId");

        if (userId != null) {
            return ResponseEntity.ok(Map.of("userId", userId, "cartId", cartId));
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("User is not logged in");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logged out successfully");
    }
}