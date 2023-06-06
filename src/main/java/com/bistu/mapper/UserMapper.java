package com.bistu.mapper;

import com.bistu.entity.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.time.LocalDateTime;
import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where username = #{username} and password = #{password}")
    User login(User user);

    void signup(User user);

    void addAccount();

    @Select("select * from user where state = 0")
    List<User> selectStateEqual0();

    @Update("update user set state = 1 where id in #{id}")
    void audit(List<Integer> id);

    @Update("update user set identity = 'Merchant' where id = #{id}")
    void registerMerchant(Integer id);

    void buildSubMerchant(SubMerchant subMerchant);

    @Select("select * from user where id = #{id}")
    User queryMessage(Integer id);

    void updateMessage(User user);

    @Select("select * from account where user_id = #{id}")
    Account getAccount(Integer id);

    @Update("update account set balance = balance - #{paid} ," +
            "outcome_time = #{outcomeTime} ," +
            "update_time = #{outcomeTime} ,point = point + #{paid} where user_id = #{id}")
    void perchase(Double paid, LocalDateTime outcomeTime, Integer id);

    @Select("select * from transaction where user_id = #{id}")
    List<Transaction> historyData(Integer id);

    @Select("select * from comment where user_id = #{id}")
    List<Comment> getComment(Integer id);

    @Select("select user_id from sub_merchant where id in (select store_id from product where id = #{id}) ")
    Integer getUserIdByProductId(Integer id);

    @Insert("insert into payment_record (user_id, merchant_id, amount, payment_time) " +
            "VALUES (#{userId},#{merchantId},#{amount},#{paymentTime})")
    void updatePaymentRecord(PaymentRecord paymentRecord);

    @Select("select point from account where user_id = #{id}")
    Integer getPoint(Integer id);

    @Update("update account set point = point - #{point} where user_id = #{id}" )
    void minusPoint(Double point, Integer id);
}