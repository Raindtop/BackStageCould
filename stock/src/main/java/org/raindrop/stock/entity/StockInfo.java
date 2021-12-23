package org.raindrop.stock.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.*;
import java.math.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


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
    private static final long serialVersionUID=1L;
    /**
    * 
    */
        @TableId
        @ApiModelProperty(value="")
    private Long id;
    /**
    * 
    */
        @ApiModelProperty(value="")
    private LocalDateTime createTime;
    /**
    * 
    */
        @ApiModelProperty(value="")
    private LocalDateTime updateTime;
    /**
    * 删除标识位 0-未删除 1-已删除
    */
        @ApiModelProperty(value="删除标识位 0-未删除 1-已删除")
    private Integer delete;
    /**
    * 代码
    */
        @ApiModelProperty(value="代码")
    private String code;
    /**
    * 名称
    */
        @ApiModelProperty(value="名称")
    private String name;
    /**
    * 名称拼音
    */
        @ApiModelProperty(value="名称拼音")
    private String namePy;

    public StockInfo () {

    }
}
