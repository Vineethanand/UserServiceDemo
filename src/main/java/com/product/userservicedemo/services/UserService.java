package com.product.userservicedemo.services;

import com.product.userservicedemo.models.User;

public interface UserService {
    User signUp(String email, String password, String name);

    User findUserById(Long id);

    User login(String email, String password);
}
