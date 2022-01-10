package org.raindrop.controller;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
//@RequestMapping("/filter")
//@RestController
public class DemoController {

//    @PostMapping("test/{id}")
    public void test(@PathVariable Long id, @RequestBody Object object){
        log.info("test");
        log.info("Obj={}", JSONObject.toJSONString(object));
    }
}
