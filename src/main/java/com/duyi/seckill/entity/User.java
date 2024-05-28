package com.duyi.seckill.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 用户表
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {


    private static final long serialVersionUID = 8344236036865981692L;

    public User(String phone, String password) {
        this.phone = phone;
        this.password = password;
    }

    private int id;
    private String phone;
    private String password;


}
