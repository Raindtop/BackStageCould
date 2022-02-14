package org.raindrop.lambdaekl.common.query;

import lombok.Data;

import java.util.List;

/**
 * 多字段匹配查询
 * see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.16/query-dsl-multi-match-query.html">Document</a>
 */
@Data
public class MultiMatchQuery implements Query{
    /**
     * 查询内容
     */
    private Object query;

    /**
     * 匹配字段
     */
    private List<String> fields;
}
