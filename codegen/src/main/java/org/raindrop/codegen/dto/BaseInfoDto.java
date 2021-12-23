package org.raindrop.codegen.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BaseInfoDto {
    private String pkg;
    private String module;
    private String author;
    private String dateTime;
    private String comments;
    private String tableName;
    private String className;
    private String pathName;

    public String fullPkg(String finalPkg){
        return pkg + "." + module + "." + finalPkg;
    }

    public String fullPkg(){
        return pkg + "." + module;
    }
}
