package com.duyi.seckill.dao;

import com.duyi.seckill.entity.User;

public interface UserDao {


    User getUser(int id);

    void insert(User user);

    User getUserByPhone(String phone);

}
