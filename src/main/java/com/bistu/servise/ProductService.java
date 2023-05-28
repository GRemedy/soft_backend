package com.bistu.servise;

import com.bistu.entity.PageBean;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
public interface ProductService {
    PageBean getAll(Integer start, Integer pageSize,String name ,String category, String storeName);
}
