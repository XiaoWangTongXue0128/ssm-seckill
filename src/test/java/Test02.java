import cn.hutool.crypto.digest.DigestUtil;
import com.duyi.seckill.dao.RedisDao;
import com.duyi.seckill.dao.SeckillItemDao;
import com.duyi.seckill.dao.SeckillOrderDao;
import com.duyi.seckill.dao.UserDao;
import com.duyi.seckill.entity.User;
import com.duyi.seckill.service.SeckillItemService;
import com.duyi.seckill.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


// 让单元测试在运行的时候，基于spring容器运行，先加载spring容器，基于spring环境进行单元测试
@RunWith(SpringJUnit4ClassRunner.class) //使用junit4进行测试
@ContextConfiguration(locations = {"classpath:spring/spring-*.xml"}) //加载配置文件
public class Test02 {


    // spring容器内注入这个属性对象
    @Autowired
    RedisTemplate<String, Object> redisTemplate;

    @Autowired
    UserService userService;

    @Autowired
    UserDao userDao;

    @Autowired
    SeckillItemDao seckillItemDao;


    @Autowired
    SeckillItemService seckillItemService;


    @Autowired
    SeckillOrderDao seckillOrderDao;

    @Autowired
    RedisDao redisDao;



    @Test
    public void t35() {

//        System.out.println(seckillItemDao.getAll());
//        System.out.println(seckillItemDao.get(1));
//        System.out.println(seckillItemService.getSeckillUrl(1));

//        SeckillOrder seckillOrder = new SeckillOrder();
//        seckillOrder.setUserId(99);
//        seckillOrder.setState(99);
//        seckillOrder.setSeckillItemId(99);
//        seckillOrder.setCreateTime(new Date());
//        seckillOrderDao.insert(seckillOrder);

//        seckillItemDao.updateStock(1);


//        redisDao.stockDecr("stock_1");


//        redisDao.setex("13812344321_1", 1, 60 * 5);

//        System.out.println(seckillOrderDao.getAllNotPaid());


        redisDao.stockAdd("stock_1");

    }

    @Test
    public void t34() {
        // 4297f44b13955235245b2497399d7a93
        // 4297f44b13955235245b2497399d7a93
        // 8b99ab2587550f471d22fcdf26f86581
        // 73f3233551032e4847f2a80bc338eccb
        // 9d2d56638ad24c7d522edd94e134198a
        // 8c6aa1aa434596ce9142daf4182ba23d
        // 1G mp4 -->  8c6aa1aa434596ce9142daf4182ba23d 摘要

        // a 97
        // a 65
        // 中 20013
        // 0-255 0000 00000 00000 00000
        // ASCII(数字，字母，常用符号) GBK UTF8
        // 1base编码
        // 2MD5 摘要
        // 3对称
        // 4非对称 HTTPS http

        System.out.println(DigestUtil.md5Hex("  123123"));
    }

    @Test
    public void t33() {
        User user = new User();
        user.setPhone("123aaa123");
        // 4297f44b13955235245b2497399d7a93
        user.setPassword("123123");
        // MD5
        userService.regist(user);

    }

    @Test
    public void t2() {

//        System.out.println(redisTemplate);
        System.out.println("userService = " + userService.getUser(1));
//        System.out.println("userDao = " + userDao.getUser(1));


    }


    @Test
    public void t3() {

        redisTemplate.opsForValue().set("template", "haha");

    }


    @Test
    public void t1() {
        // 容器初始化
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("spring/spring-redis.xml", "spring/spring-dao.xml", "spring/spring-web.xml");

//        RedisTemplate<String, Object> redisTemplate = (RedisTemplate<String, Object>) context.getBean("redisTemplate");

        System.out.println(redisTemplate);
    }


}
