import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.HashMap;
import java.util.List;

public class Test01 {

    static JedisPool jedisPool = new JedisPool("192.168.1.213", 6379);


    static Jedis jedis = jedisPool.getResource();


    public static void main(String[] args) {

        // 默认连接host是本机，post端口是6379
        // 如果不是需要自定参数
//        JedisPool jedisPool = new JedisPool();


    }


    public static void kvList() {
        jedis.lpush("test3", "l1", "l1", "l1", "l2", "l2");

        List<String> list = jedis.lrange("test3", 0, 10);

        System.out.println(list);
    }

    public static void kvHash() {
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("k1", "v1");
        map.put("k2", "v2");
        map.put("k3", "v3");

        jedis.hmset("test2", map);

        String val = jedis.hget("test2", "k3");
        System.out.println(val);
    }


    public static void kvSet() {
        jedis.sadd("test1", "a", "b", "c");

        List<String> list = jedis.srandmember("test1", 10);

        System.out.println(list);
    }

    public static void kvString() {
        // key - value 字符串
        jedis.set("duyi", "hello");

        String value = jedis.get("duyi");
        System.out.println(value);

    }

}
