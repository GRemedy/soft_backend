package com.bistu.mapper;

import com.bistu.entity.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: Gremedy
 * @Description:
 * @Date : 2023/5/26
 */
@Mapper
public interface ProductMapper {

    List<Product> getAll(GetAllParam getAllParam);

    @Select("select count(*) from product")
    Long getCount();

    void perchase(Transaction transaction);

    @Select("select * from coupon where user_id = #{id}")
    List<Coupon> getCoupon(Integer id);

    @Select("select * from coupon where Id = #{id}")
    Coupon useCoupon(Integer id);
    @Select("select * from product where id = #{id}")
    Product getProduct(Integer id);

    @Update("update product set quantity = quantity - #{quantity} " +
            ",update_time = #{paymentTime} ,sales_volume = sales_volume + #{quantity}" +
            " where id = #{productId} ")
    void updateProduct(Transaction transaction);

    void shoppingCart(ShoppingCart shoppingCart);

    void comment(Comment comment);
    @Update("update product set rating = #{rating} where id = #{id}")
    void updateRating(Double rating , Integer id);
    @Select("select count(*) from comment where product_id = #{id}")
    Integer getTotalComment(Integer id);

    @Select("select count(*) from comment where grade >= 4 and product_id = #{id}")
    Integer getGoodComment(Integer id);
    @Select("select grade,content from comment where product_id = #{id}")
    List<Comment> getProductComment(Integer id);


    void shelves(Product product);
}
