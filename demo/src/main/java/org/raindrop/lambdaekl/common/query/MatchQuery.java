package org.raindrop.lambdaekl.common.query;

import lombok.Data;
import org.raindrop.lambdaekl.common.query.Query;

/**
 * 匹配查询
 * see <a href="https://www.elastic.co/guide/en/elasticsearch/reference/7.16/query-dsl-match-query.html">Document</a>
 */
@Data
public class MatchQuery implements Query{
    /**
     * 列
     */
    private String filed;

    /**
     * 查询内容
     */
    private Object query;
}
