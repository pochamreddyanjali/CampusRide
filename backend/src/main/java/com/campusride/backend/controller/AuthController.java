package com.campusride.backend.controller;

import com.campusride.backend.entity.User;
import com.campusride.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.campusride.backend.entity.LoginRequest;


@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")  // allows frontend to call backend
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }
    
    @PostMapping("/login")
    public User login(@RequestBody LoginRequest request) {
        return userService.loginUser(request.getEmail(), request.getPassword());
    }

}
