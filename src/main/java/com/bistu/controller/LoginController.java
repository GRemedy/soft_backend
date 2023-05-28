package com.bistu.controller;

import com.bistu.dis.DisUser;
import com.bistu.entity.Result;
import com.bistu.entity.User;
import com.bistu.servise.UserService;
import com.bistu.utils.JWTUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        DisUser u = userService.login(user);
        if(!ObjectUtils.isEmpty(u)){
            String token = JWTUtils.JWTGen(u);
            return Result.success(token);
        }
        else {
            return Result.error();
        }
    }
}
