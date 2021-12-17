package org.raindrop;

import com.alibaba.fastjson.JSON;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class Demo2Controller {
    @Resource(name = "redisTemplate")
    private RedisTemplate redisTemplate;

    @GetMapping("set")
    public void set(@RequestParam("key") String key){
        redisTemplate.opsForValue().set(key, new TestObj("jack", 25), 1000, TimeUnit.SECONDS);
        log.info("111");
        return ;
    }

    @GetMapping("get")
    public void get(@RequestParam("key") String key){
        TestObj testObj = (TestObj)redisTemplate.opsForValue().get(key);
        System.out.println(JSON.toJSONString(testObj));
        return ;
    }
}

@Data
@AllArgsConstructor
@NoArgsConstructor
class TestObj{
    private String name;
    private Integer age;
}
