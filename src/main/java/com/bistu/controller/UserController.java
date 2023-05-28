package com.bistu.controller;

import com.bistu.dis.DisUser;
import com.bistu.entity.Result;
import com.bistu.servise.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/select")
    public Result selectStateEqual0(){
        List<DisUser> disUserList = userService.selectStateEqual0();
        return Result.success(disUserList);
    }

    @PutMapping("/audit")
    public Result audit(List<Integer> ids){
        userService.audit(ids);
        return Result.success();
    }
    @PutMapping("/registerMerchant")
    public Result registerMerchant(Integer id, String storeName, String license){
        userService.registerMerchant(id,storeName,license);
        return Result.success();
    }
}
