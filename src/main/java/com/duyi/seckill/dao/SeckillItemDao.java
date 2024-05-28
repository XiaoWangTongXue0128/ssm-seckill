package com.duyi.seckill.dao;

import com.duyi.seckill.entity.SeckillItem;

import java.util.List;

public interface SeckillItemDao {

    List<SeckillItem> getAll();

    SeckillItem get(int id);

    void updateStock(int seckillId);

    void stockAdd(int seckillId);
}
