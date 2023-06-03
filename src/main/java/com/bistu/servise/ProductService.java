package com.bistu.servise;

import com.bistu.entity.PageBean;
import com.bistu.entity.Product;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
public interface ProductService {

    PageBean getAll(Integer start, Integer pageSize, Product product, Integer grade, String storeName, boolean isDESC);
}
