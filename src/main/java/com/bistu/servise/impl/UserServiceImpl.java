package com.bistu.servise.impl;

import com.bistu.Enum.Identity;
import com.bistu.dis.DisUser;
import com.bistu.entity.SubMerchant;
import com.bistu.entity.User;
import com.bistu.mapper.UserMapper;
import com.bistu.servise.UserService;
import com.bistu.utils.UserToDisUserMap;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.bistu.utils.EncodePassword.encodePassword;

@Service
public class UserServiceImpl implements UserService {

    private final UserMapper userMapper;
    private final UserToDisUserMap userToDisUserMap;


    public UserServiceImpl(UserMapper userMapper, UserToDisUserMap userToDisUserMap) {
        this.userMapper = userMapper;
        this.userToDisUserMap = userToDisUserMap;
    }

    @Override
    public DisUser login(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        return userToDisUserMap.userToDisUserMap(userMapper.login(user));
    }

    @Override
    public void signup(User user) {
        user.setPassword(encodePassword(user.getPassword()));
        user.setUpdateTime(LocalDateTime.now());
        user.setCreateTime(LocalDateTime.now());
        if(user.getIdentity()==null){
            user.setIdentity(Identity.User);
        }
        if(user.getState() == null){
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
    public void registerMerchant(Integer id, String storeName ,String license) {
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
}
