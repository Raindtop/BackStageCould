package org.raindrop.codegen.entity;

import lombok.Data;

@Data
public class TableColumn {
    private String tableSchema;
    private String tableName;
    private String columnName;
    private String dataType;
    private String columnKey;
    private String columnType;
    private String columnComment;
}
