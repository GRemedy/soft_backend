package com.bistu.servise;

import com.bistu.entity.PageBean;

import java.util.Map;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
public interface ProductService {

    PageBean getAll(Map<String,Object> paraMap);
}
