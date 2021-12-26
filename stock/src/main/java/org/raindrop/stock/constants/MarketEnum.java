package org.raindrop.stock.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MarketEnum {
    SH("sh", "上海"),
    SZ("sz", "深圳"),
    KC("kc", "科创"),
    BJ("bj", "北京");

    private String dbName;
    private String desc;
}
