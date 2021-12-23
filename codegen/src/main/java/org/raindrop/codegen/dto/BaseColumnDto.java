package org.raindrop.codegen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseColumnDto {
    private String comments;
    private boolean priKey=false;
    private String attrType;
    private String lowerAttrName;
    private String columnName;
    private boolean hidden = false;
}
