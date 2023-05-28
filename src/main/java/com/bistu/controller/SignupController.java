package com.bistu.controller;


import com.bistu.entity.Result;
import com.bistu.entity.User;
import com.bistu.servise.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        userService.signup(user);
        return Result.success();
    }
}
