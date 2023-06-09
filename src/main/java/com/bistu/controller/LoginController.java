package com.bistu.controller;

import com.bistu.dis.DisUser;
import com.bistu.entity.Result;
import com.bistu.entity.Storage;
import com.bistu.entity.User;
import com.bistu.exception.DefaultException;
import com.bistu.servise.UserService;
import com.bistu.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@Slf4j
public class LoginController {
    private final UserService userService;
    private final Storage storage;

    public LoginController(UserService userService, Storage storage) {
        this.userService = userService;
        this.storage = storage;
    }
    @PostMapping("/login")
    public Result login(String username,String password ,String captcha){
        if (Objects.equals(captcha,storage.getCaptcha())){
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            DisUser u = userService.login(user);
            if(!ObjectUtils.isEmpty(u)){
                String token = JWTUtils.JWTGen(u);
                return Result.success(token);
            }
            else {
                return Result.error();
            }
        }
        else throw new DefaultException("0","验证码不正确，请重新输入");
    }
}
