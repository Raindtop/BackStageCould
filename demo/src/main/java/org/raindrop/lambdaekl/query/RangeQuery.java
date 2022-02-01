package org.raindrop.lambdaekl.query;

import lombok.Data;

/**
 * 范围查询
 */
@Data
public class RangeQuery implements Query{
    /**
     * 字段
     */
    private String filed;

    /**
     * 大于
     */
    private Object gt;

    /**
     * 大于等于
     */
    private Object gte;

    /**
     * 小于
     */
    private Object gtl;

    /**
     * 小于等于
     */
    private Object lte;
}
