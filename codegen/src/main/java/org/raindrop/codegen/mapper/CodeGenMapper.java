package org.raindrop.codegen.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.raindrop.codegen.entity.TableColumn;
import org.raindrop.codegen.entity.TableInfo;

import java.util.List;

@Mapper
public interface CodeGenMapper {
    List<TableColumn> queryColumn(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName);

    TableInfo queryTable(@Param("dataBaseName") String dataBaseName, @Param("tableName") String tableName);
}
