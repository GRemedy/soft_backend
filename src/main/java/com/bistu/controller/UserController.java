package com.bistu.controller;

import com.bistu.dis.DisUser;
import com.bistu.entity.Account;
import com.bistu.entity.Result;
import com.bistu.entity.User;
import com.bistu.servise.UserService;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("queryMessage/{id}")
    public Result queryMessage(@PathVariable Integer id){
        return Result.success(userService.queryMessage(id));
    }
    @PutMapping("updateMessage")
    public Result updateMessage(@RequestBody User user){
        userService.updateMessage(user);
        return Result.success();
    }

    @GetMapping("historyData")
    public Result historyData(Integer id){
        return Result.success(userService.historyData(id));
    }

    @GetMapping("/getAccount")
    public Result getAccount(Integer id){
        Account account = userService.getAccount(id);
        return Result.success(account);
    }
    @GetMapping("/getComment")
    public Result getComment(Integer id){
        return Result.success(userService.getComment(id));
    }

    @GetMapping("/getChargeRecord")
    public Result getChargeRecord(Integer id){
        return Result.success(userService.getChargeRecord(id));
    }

    @GetMapping("/getPaymentRecord")
    public Result getPaymentRecord(Integer id){
        return Result.success(userService.getPaymentRecord(id));
    }
}
