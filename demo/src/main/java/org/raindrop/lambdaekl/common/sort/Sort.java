package org.raindrop.lambdaekl.common.sort;

import lombok.Data;

@Data
public class Sort {
    /**
     * 列名
     */
    private String field;

    /**
     * 排序方式
     * {@link org.raindrop.lambdaekl.common.sort.SortOrder}
     */
    private SortOrder order=SortOrder.DESC;
}
