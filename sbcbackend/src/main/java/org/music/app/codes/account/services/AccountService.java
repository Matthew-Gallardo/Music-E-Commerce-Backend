package org.music.app.codes.account.services;

import org.music.app.codes.account.model.data.Login;
import org.music.app.codes.account.model.data.Users;
import org.music.app.codes.account.model.forms.ForgotPasswordForm;
import org.music.app.codes.account.model.forms.LoginForm;
import org.music.app.codes.account.model.forms.RegisterForm;
import org.music.app.codes.account.repository.LoginRepository;
import org.music.app.codes.account.repository.UserRepository;
import org.music.app.codes.transaction.model.data.Cart;
import org.music.app.codes.transaction.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private LoginRepository loginRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CartRepository cartRepository;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    public boolean registerAccount(RegisterForm form) {
        if (!form.getPassword().equals(form.getConfirmPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        
        String hashedPassword = passwordEncoder.encode(form.getPassword());

        Users user = new Users();
        user.setUserEmail(form.getEmail());
        // Other fields will use default values set in the database

        Login login = new Login();
        login.setLoginUsername(form.getUsername());
        login.setLoginPassword(hashedPassword);
        login.setLoginSecQuestion(form.getSecurityQuestion());
        login.setLoginSecAnswer(form.getSecurityAnswer());
        login.setUsers(user);

        user.setLogin(login);


        userRepository.addUserProfile(user);

        Cart cart = new Cart();
        cart.setUsers(user);
        cartRepository.save(cart);
        return true;
    }

    public boolean verifyCredentials(LoginForm form) {
        Login login = loginRepository.findByLoginUsername(form.getUsername());
        if (login == null) {
            return false;
        }
        return passwordEncoder.matches(form.getPassword(), login.getLoginPassword());
    }
    
    public boolean validateSecurityQuestion(ForgotPasswordForm form) {
        Login login = loginRepository.findByLoginUsername(form.getUsername());
        if (login == null) {
            return false;
        }
        return login.getLoginSecQuestion().equals(form.getSecurityQuestion()) &&
               login.getLoginSecAnswer().equals(form.getSecurityAnswer());
    }
}