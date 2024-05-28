package com.duyi.seckill.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀订单
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillOrder implements Serializable {


    private static final long serialVersionUID = -5940100412169957006L;
    private int id;
    private String orderCode;
    private int seckillItemId;
    private int userId;
    // 1 下单成功，未支付
    // TODO 未支付还存在一个逻辑
    // 2 已支付
    // 4 订单超时
    private int state;
    private Date createTime;


}
