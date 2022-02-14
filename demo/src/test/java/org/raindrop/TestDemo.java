package org.raindrop;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.raindrop.aspect.MyAspect;
import org.raindrop.entity.Person;
import org.raindrop.service.DemoService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
    @Resource
    private DemoService demoService;

    @Test
    public void demo(){
        demoService.test(new Person(){{
            setAge(10);
            setName("Demo");
        }});
    }

    @Test
    public void demo2(){
        try{
            demoService.demo();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
