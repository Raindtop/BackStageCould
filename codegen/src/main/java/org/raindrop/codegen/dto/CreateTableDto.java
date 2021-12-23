package org.raindrop.codegen.dto;

import lombok.Data;

@Data
public class CreateTableDto {
    /**
     * 用户
     */
    private String author = "raindrop";

    /**
     * 包名
     */
    private String pkg = "org.raindrop";

    /**
     * 模块名
     */
    private String module = "demo";

    /**
     * createTable
     */
    private String database;

    /**
     * createTable
     */
    private String[] tables;
}
