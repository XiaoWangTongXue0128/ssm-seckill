package com.duyi.seckill.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 秒杀商品
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SeckillItem implements Serializable {


      private static final long serialVersionUID = -2365891879745839834L;
      private int id;
      private String name;
      private int number;
      //      private BigDecimal price;
      private float price;
      private Date startTime;
      private Date endTime;
      private Date createTime;


}
