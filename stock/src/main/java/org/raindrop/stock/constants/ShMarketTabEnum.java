package org.raindrop.constants;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ShMarketTabEnum {
    TAB1("1", "主板A股", MarketEnum.SH),
    TAB2("2", "主板B股", MarketEnum.SH),
    TAB3("8", "科创板", MarketEnum.KC);

    private String stockType;
    private String desc;
    private MarketEnum marketEnum;
}
