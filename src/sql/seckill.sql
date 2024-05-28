
-- 秒杀商品表
CREATE TABLE `seckill_item` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `end_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `price` decimal(10,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 秒杀订单表
CREATE TABLE `seckill_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `seckill_item_id` int(11) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `state` tinyint(4) NOT NULL DEFAULT '-1',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- 用户表
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
