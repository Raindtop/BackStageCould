package org.raindrop.entity;

import cn.hutool.extra.pinyin.PinyinUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 股票信息
 *
 * @author raindrop
 * @date 2021-12-23 14:18:30
 */
@Data
@Builder
@AllArgsConstructor
@TableName(value = "stock_info")
public class StockInfo extends Model<StockInfo> {
    private static final long serialVersionUID = 1L;
    /**
     *
     */
    @TableId
    private Long id;
    /**
     *
     */
    private LocalDateTime createTime;
    /**
     *
     */
    private LocalDateTime updateTime;
    /**
     * 删除标识位 0-未删除 1-已删除
     */
    private Integer deleteFlag;
    /**
     * 代码
     */
    private String code;
    /**
     * 名称
     */
    private String name;
    /**
     * 名称拼音
     */
    private String namePy;
    /**
     * 状态 0-正常
     */
    private Integer status;
    /**
     * 市场
     */
    private String market;
    /**
     * 查询结果字符串
     */
    private String queryJson;

    public StockInfo() {

    }
}
