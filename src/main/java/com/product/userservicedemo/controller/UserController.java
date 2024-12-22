package com.product.userservicedemo.controller;

import com.product.userservicedemo.dtos.LoginRequestDto;
import com.product.userservicedemo.dtos.SignUpRequestDto;
import com.product.userservicedemo.models.User;
import com.product.userservicedemo.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public String signup(@RequestBody SignUpRequestDto signUpRequestDto) {
        User user = userService.signUp(signUpRequestDto.getEmail(), signUpRequestDto.getPassword(), signUpRequestDto.getName());
        return user.toString();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") Long id) {
        User user = userService.findUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto)
    {
        User user = userService.login(loginRequestDto.getEmail(), loginRequestDto.getPassword());
        if(user != null)
            return new ResponseEntity<>("Login Successfull", HttpStatus.OK);
        else
            return new ResponseEntity<>("Login Failed", HttpStatus.UNAUTHORIZED);
    }
}
