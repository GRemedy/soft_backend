package com.bistu.servise;

import com.bistu.dis.DisProduct;
import com.bistu.dis.DisUser;
import com.bistu.entity.*;

import java.util.List;

public interface UserService {
    /**
     * @param user 用户信息
     * @return DisUser
     * @author Gremedy
     * @description 登录方法
     * @date 2023/6/5 18:02
     **/

    DisUser login(User user);

    /**
     * @param user 用户信息
     * @author Gremedy
     * @description 注册方法
     * @date 2023/6/5 18:02
     **/

    void signup(User user);

    /**
     * @param ids 账户ID
     * @author Gremedy
     * @description 审核用户状态为0的
     * @date 2023/6/5 18:03
     **/

    void audit(List<Integer> ids);

    /**
     * @return List<DisUser>
     * @author Gremedy
     * @description 查询状态为0的用户
     * @date 2023/6/5 18:03
     **/

    List<DisUser> selectStateEqual0();

    /**
     * @param id , 用户Id
     * @param storeName , 店铺名字
     * @param license  营业执照
     * @author Gremedy
     * @description 用户注册成为商家
     * @date 2023/6/5 18:04
     **/

    void registerMerchant(Integer id, String storeName, String license);

    /**
     * @param id 账户ID
     * @return User
     * @author Gremedy
     * @description 查询自己的信息
     * @date 2023/6/5 18:05
     **/

    User queryMessage(Integer id);

    /**
     * @param user 修改后的用户信息
     * @author Gremedy
     * @description 修改自己的信息
     * @date 2023/6/5 18:05
     **/

    void updateMessage(User user);

    /**
     * @param id 账户ID
     * @return List<DisProduct>
     * @author Gremedy
     * @description 查询自己的历史交易的商品
     * @date 2023/6/5 18:06
     **/

    List<DisProduct> historyData(Integer id);

    /**
     * @param id 账户ID
     * @return Account 账户信息
     * @author Gremedy
     * @description 查询自己的账户信息
     * @date 2023/6/5 18:06
     **/
    Account getAccount(Integer id);
    /**
    * @author Gremedy
    * @description  获取自己历史评论
    * @date 2023/6/5 18:11
    * @param id   账户ID
    * @return List<Comment>
    **/
    List<Comment> getComment(Integer id);

    List<ChargeRecord> getChargeRecord(Integer id);

    List<PaymentRecord> getPaymentRecord(Integer id);

    void charge(ChargeRecord chargeRecord);

    List<DisProduct> getReviewProduct();

    void review(List<Integer> ids);


    void pay(Transaction transaction);

    void updateRank(Integer id ,Integer rank);
}
