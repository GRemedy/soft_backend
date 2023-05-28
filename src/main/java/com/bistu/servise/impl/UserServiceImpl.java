package com.bistu.servise.impl;

import com.bistu.Enum.Identity;
import com.bistu.dis.DisUser;
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
        user.setIdentity(Identity.User);
        user.setState(-1);
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
}
