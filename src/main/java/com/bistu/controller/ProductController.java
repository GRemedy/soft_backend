package com.bistu.controller;

import com.bistu.entity.*;
import com.bistu.servise.ProductService;
import com.bistu.utils.ExceptionUtils;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: Gremedy
 * @Description: 物品控制器
 * @Date : 2023/5/26
 */

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;
    private final ExceptionUtils exceptionUtils;

    public ProductController(ProductService productService, ExceptionUtils exceptionUtils) {
        this.productService = productService;
        this.exceptionUtils = exceptionUtils;
    }

    /**
    * @author Gremedy
    * @description
    * @date 2023/6/6 14:58
    * @param getAllParam 分页参数对象
    * @return Result
    **/

    @PostMapping("/getAll")
    public Result getAll(@RequestBody GetAllParam getAllParam) {
        if (getAllParam == null){
            GetAllParam getAllParam1 = new GetAllParam();
            getAllParam1.setStart(1);
            getAllParam1.setPageSize(20);
            PageBean products = productService.getAll(getAllParam1);
            return Result.success(products);
        }
        PageBean products = productService.getAll(getAllParam);
        return Result.success(products);
    }

    @PostMapping("/prePerchase")
    public Result prePerchase(@RequestBody Transaction transaction){
       return Result.success(productService.prePerchase(transaction));
    }

    @PutMapping("/perchase")
    public Result perchase(@RequestBody Transaction transaction,Integer couponId){
        productService.perchase(transaction,couponId);
        return Result.success("购买成功");
    }
    @PutMapping("/shoppingCart")
    public Result shoppingCart(@RequestBody ShoppingCart shoppingCart){
        productService.shoppingCart(shoppingCart);
        return Result.success("成功添加到购物车");
    }
    @PutMapping("/comment")
    public Result comment(@RequestBody Comment comment){
        productService.comment(comment);
        return Result.success("评论成功");
    }

    @PutMapping("/shelves")
    public Result shelves(Product product){
        exceptionUtils.throwException(product);
        productService.shelves(product);
        return Result.success();
    }
}
