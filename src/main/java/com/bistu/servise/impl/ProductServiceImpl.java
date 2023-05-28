package com.bistu.servise.impl;

import com.bistu.dis.DisProduct;
import com.bistu.entity.PageBean;
import com.bistu.mapper.ProductMapper;
import com.bistu.servise.ProductService;
import com.bistu.utils.ProToDisProMap;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductMapper productMapper;
    private final ProToDisProMap proToDisProMap;

    public ProductServiceImpl(ProductMapper productMapper, ProToDisProMap proToDisProMap) {
        this.productMapper = productMapper;
        this.proToDisProMap = proToDisProMap;
    }


    @Override
    public PageBean getAll(Integer start , Integer pageSize , String name , String category , String storeName) {

        PageHelper.startPage(start, pageSize);

        List<DisProduct> disProducts = productMapper.getAll(name,category,storeName);
        Page<DisProduct> disProductPage = (Page<DisProduct>) disProducts;

        return new PageBean(disProductPage.getTotal(),disProductPage.getResult());
    }
}
