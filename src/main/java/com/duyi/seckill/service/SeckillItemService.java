package com.duyi.seckill.service;

import com.duyi.seckill.dto.SeckillUrl;
import com.duyi.seckill.entity.SeckillItem;
import com.duyi.seckill.entity.SeckillOrder;
import com.duyi.seckill.entity.User;

import java.util.List;

public interface SeckillItemService {

    List<SeckillItem> getSeckillList();


    SeckillItem getSeckillItem(Integer id);


    SeckillUrl getSeckillUrl(Integer id);

    boolean verifySeckillMD5(int seckillId, String md5);

    boolean executeSeckill(User user, int seckillId);

    SeckillOrder createOrder(User user, int seckillId);

    void pay(String orderCode);

    SeckillOrder getSeckillOrder(String orderCode);
}
