package com.bistu.servise;

import com.bistu.entity.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
public interface ProductService {

    PageBean getAll(Map<String,Object> paraMap);


    List<Coupon> prePerchase(Integer id);

    void perchase(Transaction transaction, Integer couponId);

    void shoppingCart(ShoppingCart shoppingCart);

    void comment(Comment comment);
}
