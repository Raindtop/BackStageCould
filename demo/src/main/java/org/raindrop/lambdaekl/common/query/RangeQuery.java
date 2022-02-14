package org.raindrop.lambdaekl.common.query;

import lombok.Data;
import org.raindrop.lambdaekl.common.query.Query;

/**
 * 范围查询
 * {document <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.16/query-dsl-range-query.html">Doument</a>}
 */
@Data
public class RangeQuery implements Query {
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
