package org.raindrop.lambdaekl.common.page;

import cn.hutool.json.JSONObject;
import lombok.Data;

import java.util.List;

/**
 * 分页信息
 *
 * @author raindrop
 * @date 2022/2/14
 */
@Data
public class EsPage {
    private long current;
    private long total;
    private long pages;

    private List<JSONObject> results;
}
