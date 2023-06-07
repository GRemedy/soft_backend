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

    PageBean getAll(GetAllParam getAllParam);


    Map<String, List<Object>> prePerchase(Transaction transaction);

    void perchase(Transaction transaction, Integer couponId);

    void shoppingCart(ShoppingCart shoppingCart);

    void comment(Comment comment);

    void shelves(Product product);
}
