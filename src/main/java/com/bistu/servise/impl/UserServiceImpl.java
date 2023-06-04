package com.bistu.servise.impl;

import com.bistu.Enum.Identity;
import com.bistu.dis.DisProduct;
import com.bistu.dis.DisUser;
import com.bistu.entity.*;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.UserService;
import com.bistu.utils.ProToDisProMap;
import com.bistu.utils.TransToProductMap;
import com.bistu.utils.UserToDisUserMap;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.bistu.utils.EncodePassword.encodePassword;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserToDisUserMap userToDisUserMap;

    private final ProToDisProMap proToDisProMap;
    private final TransToProductMap transToProductMap;


    public UserServiceImpl(UserMapper userMapper, UserToDisUserMap userToDisUserMap, ProToDisProMap proToDisProMap, TransToProductMap transToProductMap) {
        this.userMapper = userMapper;
        this.userToDisUserMap = userToDisUserMap;
        this.proToDisProMap = proToDisProMap;
        this.transToProductMap = transToProductMap;
    }

    @Override
    public DisUser login(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return userToDisUserMap.userToDisUserMap(userMapper.login(user));
    }

    @Override
    @Transactional
    public void signup(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        if (user.getIdentity() == null) {
            user.setIdentity(Identity.User);
        }
        if (user.getState() == null) {
            user.setState(0);
        }
        userMapper.signup(user);
        userMapper.addAccount();
    }

    @Override
    public void audit(List<Integer> ids) {
        userMapper.audit(ids);
    }

    @Override
    public List<DisUser> selectStateEqual0() {
        return userToDisUserMap.userToDisUserMap(userMapper.selectStateEqual0());
    }

    @Override
    public void registerMerchant(Integer id, String storeName, String license) {
        userMapper.registerMerchant(id);
        SubMerchant subMerchant = new SubMerchant();
        subMerchant.setUserId(id);
        subMerchant.setLicense(license);
        subMerchant.setStoreName(storeName);
        userMapper.buildSubMerchant(subMerchant);
    }

    @Override
    public User queryMessage(Integer id) {
        return userMapper.queryMessage(id);
    }

    @Override
    public void updateMessage(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        userMapper.updateMessage(user);
    }

    /**
    * @author Gremedy
    * @date 2023/6/4 20:51
    * @param id   用户id
    * @return List<DisProduct>
    **/

    @Override
    public List<DisProduct> historyData(Integer id) {
        List<Transaction> transactions = userMapper.historyData(id);
        List<Product> products = transToProductMap.transToProductMap(transactions);
        List<DisProduct> disProducts = proToDisProMap.proToDisProMap(products);

        Map<Integer, DisProduct> mergedMap = new HashMap<>();

        for (DisProduct disProduct : disProducts) {
            int productId = disProduct.getId();
            if (mergedMap.containsKey(productId)) {
                DisProduct mergedProduct = mergedMap.get(productId);
                // 合并当前DisProduct和已存在的DisProduct的属性
                mergedProduct.setSalesVolume(mergedProduct.getSalesVolume() + disProduct.getSalesVolume());
                // 在此处添加要合并的其他属性
                // ...
            } else {
                // 将当前DisProduct添加到Map中
                mergedMap.put(productId, disProduct);
            }
        }

        Map<Integer, List<LocalDateTime>> paymentTimeMap = transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getProductId,
                        Collectors.mapping(Transaction::getPaymentTime, Collectors.toList())));
        for (DisProduct disProduct : mergedMap.values()) {
            int productId = disProduct.getId();
            if (paymentTimeMap.containsKey(productId)) {
                List<LocalDateTime> paymentTimes = paymentTimeMap.get(productId);
                disProduct.setPaymentTimes(paymentTimes);
            }
        }

        return new ArrayList<>(mergedMap.values());
    }

    @Override
    public Account getAccount(Integer id) {
        return userMapper.getAccount(id);
    }
}
