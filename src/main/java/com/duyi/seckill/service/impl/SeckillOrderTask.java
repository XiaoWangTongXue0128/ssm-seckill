package com.duyi.seckill.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.duyi.seckill.dao.RedisDao;
import com.duyi.seckill.dao.SeckillItemDao;
import com.duyi.seckill.dao.SeckillOrderDao;
import com.duyi.seckill.entity.SeckillOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@EnableScheduling//开启定时任务
public class SeckillOrderTask {


    @Autowired
    SeckillOrderDao seckillOrderDao;

    @Autowired
    SeckillItemDao seckillItemDao;

    @Autowired
    RedisDao redisDao;

    @Scheduled(fixedRate = 1000 * 1)//声明任务，每秒执行一次
    @Transactional
    public void runsecend() {
        System.out.println("********SeckillOrderTask job is ok******");

        // 获取数据库中所有未支付的订单
        List<SeckillOrder> list = seckillOrderDao.getAllNotPaid();

        if (ObjectUtil.isEmpty(list) || list.size() == 0) {
            // 没有未支付订单，什么都不做
            return;
        }
        for (SeckillOrder sOrder : list) {
            // 根据返回结果，去reids中查询是否订单超时
            Object existOrder = redisDao.get("timeout_" + sOrder.getOrderCode());

            if (ObjectUtil.isEmpty(existOrder)) {
                // 说明订单超时，不可以在继续支付，更改数据库订单状态
                seckillOrderDao.updateTimeout(sOrder);

                // redis库存+1
                redisDao.stockAdd("stock_" + sOrder.getSeckillItemId());
                // mysql库存+1
                seckillItemDao.stockAdd(sOrder.getSeckillItemId());


            }
        }
    }

}
