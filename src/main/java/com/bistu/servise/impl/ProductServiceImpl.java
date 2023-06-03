package com.bistu.servise.impl;

import com.bistu.dis.DisProduct;
import com.bistu.entity.PageBean;
import com.bistu.entity.Product;
import com.bistu.mapper.ProductMapper;
import com.bistu.servise.ProductService;
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


    public ProductServiceImpl(ProductMapper productMapper) {
        this.productMapper = productMapper;

    }



    @Override
    public PageBean getAll(Integer start, Integer pageSize, Product product, Integer grade, String storeName, boolean isDESC) {

        PageHelper.startPage(start, pageSize);

        List<DisProduct> disProducts = productMapper.getAll(product,grade,storeName,isDESC);
        Page<DisProduct> disProductPage = (Page<DisProduct>) disProducts;

        return new PageBean(disProductPage.getTotal(),disProductPage.getResult());
    }
}
