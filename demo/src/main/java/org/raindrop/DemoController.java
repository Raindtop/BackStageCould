package org.raindrop;

import com.alibaba.fastjson.JSONObject;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/filter")
@RestController
public class DemoController {

    @SneakyThrows
    @PostMapping("test/{id}")
    public void test(@PathVariable Long id, @RequestBody Object object){
        log.info("test");
        log.info("Obj={}", JSONObject.toJSONString(object));
        // 对Flux的缓存区进行实验，在这里延迟1秒，使1秒的数据缓存至gateway的缓存中
        Thread.sleep(1000l);
    }
}
