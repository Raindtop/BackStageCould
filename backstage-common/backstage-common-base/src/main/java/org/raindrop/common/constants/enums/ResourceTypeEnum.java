package org.raindrop.common.constants.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 资源类型
 */
@Getter
@AllArgsConstructor
public enum ResourceTypeEnum {

    /**
     * 左侧菜单
     */
    LEFT_MENU("0", "left"),

    /**
     * 按钮
     */
    BUTTON("1", "button");

    /**
     * 类型
     */
    private String type;

    /**
     * 描述
     */
    private String description;
}
