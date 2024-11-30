package org.music.app.codes.account.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.music.app.codes.account.model.data.Users;
import org.music.app.codes.account.model.forms.UserForm;
import org.music.app.codes.account.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/profile")
public class UserProfileRestController {

    private static final Logger LOGGER = LogManager.getLogger(UserProfileRestController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public ResponseEntity<Users> getUserProfile(HttpSession session) {
        Integer loginId = (Integer) session.getAttribute("loginId");
        if (loginId == null) {
            return ResponseEntity.status(401).body(null);
        }

        Users user = userRepository.findByLoginId(loginId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(404).body(null);
        }
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUserProfile(@RequestBody UserForm userForm, HttpSession session) {
        Integer loginId = (Integer) session.getAttribute("loginId");
        if (loginId == null) {
            return ResponseEntity.status(401).body("User not logged in");
        }

        Users user = userRepository.findByLoginId(loginId);
        if (user == null) {
            return ResponseEntity.status(404).body("User not found");
        }

        user.setUserFirstname(userForm.getUserFirstname());
        user.setUserLastname(userForm.getUserLastname());
        user.setUserEmail(userForm.getUserEmail());
        user.setUserMobile(userForm.getUserMobile());
        user.setUserStreet(userForm.getUserStreet());
        user.setUserCity(userForm.getUserCity());
        user.setUserState(userForm.getUserState());
        user.setUserZipcode(userForm.getUserZipcode());
        user.setUserCountry(userForm.getUserCountry());
        user.setUserBillingAddress(userForm.getUserBillingAddress());
        user.setUserShippingAddress(userForm.getUserShippingAddress());

        boolean isUpdated = userRepository.updateUserProfile(user);
        if (isUpdated) {
            LOGGER.info("User updated successfully: {}", user);
            return ResponseEntity.ok("User profile updated successfully.");
        } else {
            LOGGER.error("Failed to update user profile: {}", user);
            return ResponseEntity.status(500).body("Error updating user profile.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllProfiles() {
        List<Users> users = userRepository.getAllProfiles();
        if (users.isEmpty()) {
            LOGGER.warn("No user profiles found.");
            return ResponseEntity.noContent().build();
        } else {
            LOGGER.info("Retrieved {} user profiles.", users.size());
            return ResponseEntity.ok(users);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUserProfile(@PathVariable("id") Integer userId) {
        boolean isDeleted = userRepository.deleteUserProfile(userId);
        if (isDeleted) {
            LOGGER.info("User profile deleted successfully, ID: {}", userId);
            return ResponseEntity.ok("User profile deleted successfully.");
        } else {
            LOGGER.error("Failed to delete user profile, ID: {}", userId);
            return ResponseEntity.status(500).body("Error deleting user profile.");
        }
    }
}