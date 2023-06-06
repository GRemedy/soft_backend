package com.bistu.servise;

import com.bistu.entity.Comment;
import com.bistu.entity.PageBean;
import com.bistu.entity.ShoppingCart;
import com.bistu.entity.Transaction;

import java.util.List;
import java.util.Map;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
public interface ProductService {

    PageBean getAll(Map<String,Object> paraMap);


    Map<String, List<Object>> prePerchase(Transaction transaction);

    void perchase(Transaction transaction, Integer couponId);

    void shoppingCart(ShoppingCart shoppingCart);

    void comment(Comment comment);
}
