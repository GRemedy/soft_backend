package com.bistu.controller;

import com.bistu.dis.DisProduct;
import com.bistu.entity.*;
import com.bistu.servise.ProductService;
import com.bistu.utils.ExceptionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: Gremedy
 * @Description: 物品控制器
 * @Date : 2023/5/26
 */

@RestController
@RequestMapping("/product")
@Slf4j
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
    * @date 2023/6/12 11:13
    * @param start , pageSize , name , category , price , salesVolume , storeName , rating , isDESC   
    * @return Result  
    **/
    

    @PostMapping("/getAll")
    public Result getAll(@RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "20") Integer pageSize,
                         String name,String category,String sortKey,
                         String storeName, Boolean isDESC ) {
        GetAllParam getAllParam = new GetAllParam(start,pageSize,name,category,storeName,sortKey,isDESC);
        PageBean products = productService.getAll(getAllParam);
        return Result.success(products);
    }

    @GetMapping("/getMessage")
    public Result getMessage(Integer id){
        DisProduct message = productService.getMessage(id);
        return Result.success(message);

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
    public Result shoppingCart(@RequestBody Integer id ,List<Integer> ids ,List<Integer> quantity){
        productService.shoppingCart(id,ids,quantity);
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
        return Result.success("上架商品成功，请等待管理员审核");
    }

    @PutMapping("/offShelves")
    public Result offShelves(List<Integer> ids){
        productService.offShelves(ids);
        return Result.success("下架商品成功");
    }

    @PutMapping("/delivery")
    public Result delivery(@RequestBody Transaction transaction){
        productService.delivery(transaction);
        return Result.success();
    }
}
