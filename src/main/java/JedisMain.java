import redis.clients.jedis.Jedis;

import java.util.HashMap;
import java.util.Map;

public class JedisMain {
    public static void main(String[] args) {
        Jedis jedis = new Jedis("192.168.10.133", 6379);
        //jedis.auth("amdin");
        jedis.set("name", "jedis");
        System.out.println("jedis.get(\"name\") = " + jedis.get("name"));
        Map<String, String> hashMap = new HashMap<String, String>();
        hashMap.put("english", String.valueOf(45) );
        hashMap.put("math", String.valueOf(89));
        hashMap.put("computer", String.valueOf(100));
        jedis.hmset("student.scofield", hashMap);
        Map<String, String> map = jedis.hgetAll("student.scofield");
        map.forEach((key, value) ->{
            System.out.println(key + " : "  + value);
        });

        System.out.println("jedis.hget(\"student.scofield\", \"english\") = " + jedis.hget("student.scofield", "english"));

    }
}
