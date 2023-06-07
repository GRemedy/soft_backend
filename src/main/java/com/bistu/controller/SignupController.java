package com.bistu.controller;


import com.bistu.entity.Result;
import com.bistu.entity.User;
import com.bistu.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignupController {

    private final UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        log.info(user.toString());
        userService.signup(user);
        return Result.success();
    }
}
