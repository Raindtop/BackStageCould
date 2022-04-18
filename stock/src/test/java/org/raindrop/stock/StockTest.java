package org.raindrop.stock;

import org.junit.Test;
import org.raindrop.stock.service.StockInfoService;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class StockTest {
    @Resource
    private StockInfoService stockInfoService;

    @Test
    public void sync(){
        stockInfoService.sync();
    }
}
