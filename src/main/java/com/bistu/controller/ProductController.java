package com.bistu.controller;

import com.bistu.entity.PageBean;
import com.bistu.entity.Result;
import com.bistu.servise.ProductService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

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
                         @RequestParam(defaultValue = "20")Integer pageSize,
                        String name,String category,Double price, Integer grade,Integer salesVolume,
                         String storeName , boolean isDESC) {
        Map<String,Object> paraMap = new HashMap<>();
        paraMap.put("start",start);
        paraMap.put("pageSize",pageSize);
        paraMap.put("name",name);
        paraMap.put("category",category);
        paraMap.put("price",price);
        paraMap.put("grade",grade);
        paraMap.put("salesVolume",salesVolume);
        paraMap.put("storeName",storeName);
        paraMap.put("isDESC",isDESC);
        PageBean products = productService.getAll(paraMap);
        return Result.success(products);
    }

}
