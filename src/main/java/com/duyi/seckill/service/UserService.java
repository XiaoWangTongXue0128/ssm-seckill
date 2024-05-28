package com.duyi.seckill.service;

import com.duyi.seckill.entity.User;

public interface UserService {

    User getUser(int id);

    boolean login(String phone,String password);

    void regist(User user);
}
