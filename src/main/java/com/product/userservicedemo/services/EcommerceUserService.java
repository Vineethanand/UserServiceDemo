package com.product.userservicedemo.services;

import com.product.userservicedemo.models.User;
import com.product.userservicedemo.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCrypt;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EcommerceUserService implements UserService {

    private UserRepo userRepo;
    private PasswordEncoder passwordEncoder;

    public EcommerceUserService(UserRepo userRepo,
                                PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User signUp(String email, String password, String name) {
        User user = new User();
        user.setEmail(email);
        user.setName(name);
        user.setHashedPassword(passwordEncoder.encode(password));
        return userRepo.save(user);
    }

    @Override
    public User findUserById(Long id) {
        return userRepo.findById(id).get();
    }

    @Override
    public User login(String email, String password) {
        Optional<User> opt_user = userRepo.findUserByEmail(email);
        if (opt_user.isPresent()) {
            if(passwordEncoder.matches(password, opt_user.get().getHashedPassword())) {
                return opt_user.get();
            }
        }
        return null;
    }
}
