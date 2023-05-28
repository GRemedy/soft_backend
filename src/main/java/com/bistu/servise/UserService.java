package com.bistu.servise;

import com.bistu.dis.DisUser;
import com.bistu.entity.User;

import java.util.List;

public interface UserService {
    DisUser login(User user);

    void signup(User user);

    void audit(List<Integer> ids);

    List<DisUser> selectStateEqual0();

}
