package org.raindrop.entity;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.*;
import java.nio.file.Files;
import java.time.*;
import java.math.*;
import java.util.*;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.raindrop.constants.MarketEnum;


/**
 * 股票信息
 *
 * @author raindrop
 * @date 2021-12-23 14:18:30
 */
@Data
@Builder
@AllArgsConstructor
@ApiModel(value = "股票信息")
@TableName(value = "stock_info")
public class StockInfo extends Model<StockInfo> {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "")
    private Long id;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private LocalDateTime createTime;
    /**
     *
     */
    @ApiModelProperty(value = "")
    private LocalDateTime updateTime;
    /**
     * 删除标识位 0-未删除 1-已删除
     */
    @ApiModelProperty(value = "删除标识位 0-未删除 1-已删除")
    private Integer deleteFlag;
    /**
     * 代码
     */
    @ApiModelProperty(value = "代码")
    private String code;
    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;
    /**
     * 名称拼音
     */
    @ApiModelProperty(value = "名称拼音")
    private String namePy;
    /**
     * 状态 0-正常
     */
    @ApiModelProperty(value = "状态 0-正常")
    private Integer status;
    /**
     * 市场
     * {@link org.raindrop.constants.MarketEnum}
     */
    @ApiModelProperty(value = "市场")
    private String market;
    /**
     * 查询结果字符串
     */
    @ApiModelProperty(value = "查询结果字符串")
    private String queryJson;

    public StockInfo() {

    }

    public static void main(String[] args) throws Exception {
        Integer page = 0;
        JSONObject jsonObject = getPage(page);
        Integer totalPages = jsonObject.getInteger("totalPages");
        List<StockInfo> stockInfos = new ArrayList<>();
        stockInfos.addAll(getBjStockInfo(jsonObject.getJSONArray("content")));
        while(totalPages-1 > page){
            page++;
            jsonObject = getPage(page);
            stockInfos.addAll(getBjStockInfo(jsonObject.getJSONArray("content")));
        }
    }

    public static JSONObject getPage(Integer page){
        HttpRequest request = HttpUtil.createPost("http://www.bse.cn/nqxxController/nqxxCnzq.do?callback=jQuery331_1640509705253");
        Map<String, Object> requestBody = new HashMap<String, Object>(){{
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

    public static List<StockInfo> getBjStockInfo(JSONArray result){
        List<StockInfo> stockInfos = new ArrayList<>();

        for (int i = 0; i < result.size(); i++){
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
