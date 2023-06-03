package com.bistu.servise.impl;

import com.bistu.dis.DisProduct;
import com.bistu.entity.PageBean;
import com.bistu.entity.Product;
import com.bistu.mapper.ProductMapper;
import com.bistu.servise.ProductService;
import com.bistu.utils.ProToDisProMap;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

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
    public PageBean getAll(Map<String,Object> paraMap) {
        Long count = productMapper.getCount(paraMap);
        List<Product> products = productMapper.getAll(paraMap);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);

        return new PageBean(count,disProducts);
    }
}
