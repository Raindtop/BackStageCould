package org.raindrop;


import com.alibaba.fastjson.JSONObject;
import com.hailiang.daoism.search.api.feign.SearchService;
import com.hailiang.daoism.search.query.EsScoreQueryBuilder;
import com.hailiang.daoism.search.query.EsWrappers;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.raindrop.dto.Bank;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDemo {
//    @Resource
//    private ElasticsearchRestTemplate elasticsearchRestTemplate;
//    @Resource
//    private ShopStoreMapper shopStoreMapper;
//
//    @Test
//    public void demo2(){
//        try{
//            JSONObject shop = new JSONObject(){{
//                put("id", 1);
//                put("shop_code", "123123");
//                put("shop_name", "asadasd");
//            }};
//            elasticsearchRestTemplate.save(shop, IndexCoordinates.of("shop"));
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
    @Resource
    private SearchService searchService;


    @Test
    public void testQuery(){
        // eq查询 查询bank索引下 balance=18612的数据
        {
            System.out.println(searchService);
            EsScoreQueryBuilder<Bank> queryBuilder = (EsScoreQueryBuilder<Bank>) EsWrappers.lambdaScoreQueryBuilder("bank", Bank.class);
            System.out.println("ES eq:" + JSONObject.toJSONString(queryBuilder.eq(Bank::getBalance, 18612).query()));
        }
//
//        // ne查询  查询bank索引下 age!=39
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.ne("age", 39);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // in查询 查询bank索引下 age in (39,25,30)
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.in("age", 39, 25, 30);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // notIn查询 查询bank索引下 age not in (39,25,30)
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.notIn("age", 39, 25, 30);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // between查询 查询bank索引下 age between (30, 35)
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.between("age", 30, 35);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // gt sort查询 查询bank索引下 age > 39，并且根据age从小到大排序
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.gt("age", 39);
//            wrapper.sort("age", SortOrder.ASC);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // gte sort查询 查询bank索引下 age >= 39，并且根据age从小到大排序
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.gte("age", 39);
//            wrapper.sort("age", SortOrder.ASC);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // lt sort查询 查询bank索引下 age < 25，并且根据age从大到小排序
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.lt("age", 25);
//            wrapper.sort("age", SortOrder.DESC);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // lte sort查询 查询bank索引下 age <= 25，并且根据age从大到小排序
//        {
//            SearchWrapper wrapper = new SearchWrapper("bank");
//            wrapper.lte("age", 25);
//            wrapper.sort("age", SortOrder.DESC);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // distance查询 查询shop索引下，location_geo字段在经纬度（120.1328, 30.32627）2公里内的门店数据
//        {
//            SearchWrapper wrapper = new SearchWrapper("shop");
//            wrapper.distance("location_geo", 120.1328, 30.32627, 2, DistanceUnit.KILOMETERS);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // distance查询 查询shop索引下，location_geo字段在经纬度（120.1328, 30.32627）5公里内的 200家门店数据
//        {
//            SearchWrapper wrapper = new SearchWrapper("shop");
//            wrapper.distanceWithDistance("location_geo", 120.1328, 30.32627, 5, DistanceUnit.KILOMETERS);
//            wrapper.setCurrent(1);
//            wrapper.setSize(200);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // distance 查询 查询shop索引下，location_geo字段在经纬度（120.1328, 30.32627）2米内的 200家门店数据
//        {
//            SearchWrapper wrapper = new SearchWrapper("shop");
//            wrapper.distance("location_geo", 120.1328, 30.32627, 2, DistanceUnit.METERS);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
//
//        // distance 查询 查询shop索引下，location_geo字段在经纬度（120.1328, 30.32627）5公里内的 200家门店数据，并且根据离（120.1328, 30.32627）的从近到远进行排序
//        {
//            SearchWrapper wrapper = new SearchWrapper("shop");
//            wrapper.distanceWithDistance("location_geo", 120.1328, 30.32627, 5, DistanceUnit.KILOMETERS);
//            wrapper.setCurrent(1);
//            wrapper.setSize(200);
//            R<SearchPage> r = searchService.page(wrapper);
//        }
    }
}
