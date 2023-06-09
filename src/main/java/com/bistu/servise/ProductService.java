package com.bistu.servise;

import com.bistu.dis.DisProduct;
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

    void shoppingCart(Integer id,List<Integer> ids,List<Integer> quantity);


    void comment(Comment comment);

    void shelves(Product product);

    void offShelves(List<Integer> ids);

    void delivery(Transaction transaction);

    DisProduct getMessage(Integer id);
}
