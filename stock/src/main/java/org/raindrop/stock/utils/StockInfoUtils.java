package org.raindrop.utils;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.raindrop.constants.MarketEnum;
import org.raindrop.constants.ShMarketTabEnum;
import org.raindrop.entity.StockInfo;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.*;

@Slf4j
@Component
public class StockInfoUtils {

    /**
     * 获取上海A股所有的股票
     *
     * @param shMarketTabEnum {@link }
     * @return
     */
    public List<StockInfo> getShStockInfo(ShMarketTabEnum shMarketTabEnum) {
        log.info("Sh Sotck Info Get Start, stockType={}", shMarketTabEnum.getDesc());

        HttpRequest request = HttpUtil.createGet("http://query.sse.com.cn/security/stock/getStockListData.do");
        // 设置请求头
        request.header("Host", "query.sse.com.cn");
        request.header("Pragma", "no-cache");
        request.header("Referer", "http://www.sse.com.cn/assortment/stock/list/share/");
        request.header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.138 Safari/537.36");

        request.body("jsonCallBack=jsonpCallback66942&amp;isPagination=true&amp;stockCode&amp;csrcCode&amp;areaName&amp;stockType={stockType}&amp;pageHelp.cacheSize=1&amp;pageHelp.beginPage=1&amp;pageHelp.pageSize=2000&amp;pageHelp.pageNo=1&amp;pageHelp.endPage=11&amp;_=1589881387934".replaceAll("\\{stockType}", shMarketTabEnum.getStockType()));

        String body = request.execute().body();
        body = body.replaceAll("jsonpCallback66942\\(", "").replaceAll("\\)", "");

        JSONObject responseBody = JSONObject.parseObject(body);
        JSONArray result = responseBody.getJSONArray("result");
        List<StockInfo> stockInfos = new ArrayList<>();
        for (int i = 0; i < result.size(); i++) {
            JSONObject stock = result.getJSONObject(i);
            String code = stock.getString("COMPANY_CODE");
            String name = stock.getString("COMPANY_ABBR");
            String namePy = PinyinUtil.getFirstLetter(name, "");
            stockInfos.add(StockInfo.builder()
                    .code(code)
                    .name(name)
                    .namePy(namePy)
                    .queryJson(stock.toJSONString())
                    .market(shMarketTabEnum.getMarketEnum().getDbName()).build());
        }

        log.info("Sh Sotck Info Get End, stockType={}", shMarketTabEnum.getDesc());
        return stockInfos;
    }

    /**
     * 获取上海A股所有的股票
     *
     * @return
     */
    public List<StockInfo> getSzStockInfo() {
        log.info("Sz Sotck Info Get Start");
        HttpRequest request = HttpUtil.createGet("http://www.szse.cn/api/report/ShowReport");

        request.body("SHOWTYPE=xlsx&amp;CATALOGID=1110&amp;TABKEY=tab1&amp;random=0.6935816432433362");
        File file = new File("/Users/raindrop/Desktop/1.xlsx");
        byte[] bytes = request.execute().bodyBytes();
        ExcelReader excelReader = ExcelUtil.getReader(new ByteArrayInputStream(bytes));

        List<Map<String, Object>> rows = excelReader.readAll();
        List<StockInfo> stockInfos = new ArrayList<>();

        for (Map<String, Object> col : rows) {
            String code = (String) col.get("A股代码");
            String name = (String) col.get("A股简称");
            String namePy = PinyinUtil.getFirstLetter(name, "");
            stockInfos.add(StockInfo.builder()
                    .code(code)
                    .name(name)
                    .namePy(namePy)
                    .queryJson(JSONObject.toJSONString(col))
                    .market(MarketEnum.SZ.getDbName()).build());
        }

        log.info("Sz Sotck Info Get End");
        return stockInfos;
    }

    /**
     * 获取北京交易所股票数据
     *
     * @return
     */
    public List<StockInfo> getBjStockInfo() {
        Integer page = 0;
        JSONObject jsonObject = getBjPage(page);
        Integer totalPages = jsonObject.getInteger("totalPages");
        List<StockInfo> stockInfos = new ArrayList<>();
        stockInfos.addAll(getBjStockInfo(jsonObject.getJSONArray("content")));
        while (totalPages - 1 > page) {
            page++;
            jsonObject = getBjPage(page);
            stockInfos.addAll(getBjStockInfo(jsonObject.getJSONArray("content")));
        }

        return stockInfos;
    }

    public JSONObject getBjPage(Integer page) {
        HttpRequest request = HttpUtil.createPost("http://www.bse.cn/nqxxController/nqxxCnzq.do?callback=jQuery331_1640509705253");
        Map<String, Object> requestBody = new HashMap<String, Object>() {{
            put("page", page);
            put("typejb", "T");
            put("xxfcbj[]", "2");
            put("xxzqdm", "");
            put("sortfield", "xxzqdm");
            put("sorttype", "asc");
        }};
        request.form(requestBody);

        String body = request.execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body.substring(body.indexOf("[") + 1, body.lastIndexOf("]")));

        return jsonObject;
    }

    public List<StockInfo> getBjStockInfo(JSONArray result) {
        List<StockInfo> stockInfos = new ArrayList<>();

        for (int i = 0; i < result.size(); i++) {
            JSONObject stock = result.getJSONObject(i);
            String code = stock.getString("xxzqdm");
            String name = stock.getString("xxzqjc");
            String namePy = PinyinUtil.getFirstLetter(name, "");
            stockInfos.add(StockInfo.builder()
                    .code(code)
                    .name(name)
                    .namePy(namePy)
                    .queryJson(stock.toJSONString())
                    .market(MarketEnum.BJ.getDbName()).build());
        }

        return stockInfos;
    }
}
