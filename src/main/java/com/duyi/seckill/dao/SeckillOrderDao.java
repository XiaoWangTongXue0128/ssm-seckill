package com.duyi.seckill.dao;

import com.duyi.seckill.entity.SeckillOrder;

import java.util.List;

public interface SeckillOrderDao {

    void insert(SeckillOrder seckillOrder);

    List<SeckillOrder> getAllNotPaid();

    void updateTimeout(SeckillOrder seckillOrder);

    void pay(String orderCode);

    SeckillOrder getSeckillOrder(String orderCode);
}
