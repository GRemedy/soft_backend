package com.bistu.controller;


import com.bistu.entity.Result;
import com.bistu.entity.User;
import com.bistu.servise.UserService;
import com.bistu.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class SignupController {

    private final UserService userService;
    private final ExceptionUtils exceptionUtils;

    public SignupController(UserService userService, ExceptionUtils exceptionUtils) {
        this.userService = userService;
        this.exceptionUtils = exceptionUtils;
    }

    @PostMapping("/signup")
    public Result signup(@RequestBody User user) {
        exceptionUtils.throwException(user);
        userService.signup(user);
        return Result.success();
    }
}
