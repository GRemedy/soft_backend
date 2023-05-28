package com.bistu.controller;

import com.bistu.entity.PageBean;
import com.bistu.entity.Result;
import com.bistu.servise.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Gremedy
 * @Description: 物品控制器
 * @Date : 2023/5/26
 */

@RestController
@RequestMapping("/product")
public class ProductController {
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /***
    * @author Gremedy
    * @date 2023/5/26 22:00
    * @param start , pageSize
    * @return Result
    **/
    @GetMapping
    public Result getAll(@RequestParam(defaultValue = "1") Integer start,
                         @RequestParam(defaultValue = "10")Integer pageSize) {

        PageBean products = productService.getAll(start, pageSize);
        return Result.success(products);
    }
}
