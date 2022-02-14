package org.raindrop.service;

import lombok.SneakyThrows;
import org.raindrop.aspect.MyAspect;
import org.raindrop.entity.Person;
import org.raindrop.entity.StockDateTop;
import org.raindrop.glock.annotation.Glock;
import org.raindrop.mapper.StockDateTopMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.math.BigDecimal;

@Service
public class DemoService {
    @Resource
    private StockDateTopMapper stockDateTopMapper;

    @SneakyThrows
    @Glock(name = "DemoService", keys = {"#person.name"}, leaseTime = 1000000L)
    public void test(Person person) {
        Thread.sleep(10000L);
    }

    public void demo() {
        demoTran();
    }

    @Transactional(rollbackFor = Exception.class)
    public void demoTran(){
        StockDateTop stockDateTop = new StockDateTop() {{
            setPrice(BigDecimal.TEN);
        }};
        stockDateTopMapper.insert(stockDateTop);
        BigDecimal.ONE.divide(BigDecimal.ZERO, 4);
    }
}
