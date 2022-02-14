package org.raindrop.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;


/**
 * 连续涨停股票
 *
 * @author raindrop
 * @date 2021-12-23 14:18:30
 */
@Data
@Builder
@AllArgsConstructor
@TableName(value = "stock_date_top")
public class StockDateTop extends Model<StockDateTop> {
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
    private Integer delete;
    /**
     * 日期
     */
    private LocalDateTime date;
    /**
     * 股票代码
     */
    private String stockCode;
    /**
     * 价格
     */
    private BigDecimal price;
    /**
     * 持股人数
     */
    private Integer holdPerson;
    /**
     * 连续涨停次数
     */
    private Integer topTimes;
    /**
     * 最近一次分红金额
     */
    private BigDecimal lastBonus;

    public StockDateTop() {

    }
}
