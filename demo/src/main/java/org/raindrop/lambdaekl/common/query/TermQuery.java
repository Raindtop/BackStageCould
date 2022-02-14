package org.raindrop.lambdaekl.common.query;

import lombok.Data;
import org.raindrop.lambdaekl.common.query.Query;

/**
 * 精确匹配查询
 * see <a href="https://www.elastic.co/guide/en/elasticsearch/client/net-api/current/term-query-usage.html">Document</a>
 */
@Data
public class TermQuery implements Query {
    /**
     * 列
     */
    private String filed;

    /**
     * 查询内容
     */
    private Object query;
}
