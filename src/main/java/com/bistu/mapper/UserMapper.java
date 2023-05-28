package com.bistu.mapper;

import com.bistu.entity.SubMerchant;
import com.bistu.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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
}
