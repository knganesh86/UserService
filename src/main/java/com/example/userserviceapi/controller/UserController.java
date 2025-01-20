package com.example.userserviceapi.controller;

import com.example.userserviceapi.dtos.LoginRequestDto;
import com.example.userserviceapi.dtos.SignUpRequestDto;
import com.example.userserviceapi.dtos.UserDto;
import com.example.userserviceapi.models.Token;
import com.example.userserviceapi.models.User;
import com.example.userserviceapi.services.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {
    private UserService userservice;

    public UserController(UserService userService) {
        this.userservice = userService;
    }

    @PostMapping("/signup")
    public UserDto signup(@RequestBody SignUpRequestDto requestDto) {
        User user =
                userservice.signUp(requestDto.getEmail(), requestDto.getName(), requestDto.getPassword());
        return UserDto.from(user);
    }

    @PostMapping("/login")
    public Token login(@RequestBody LoginRequestDto requestDto) {
        Token token =
                userservice.login(requestDto.getEmail(), requestDto.getPassword());
        return token;
    }

    @PostMapping("/validate/{token}")
    public UserDto validate(@PathVariable String token) {
        try {
            return UserDto.from(userservice.validateToken(token));
        } catch (Exception e) {
            return null;
        }
    }
}
