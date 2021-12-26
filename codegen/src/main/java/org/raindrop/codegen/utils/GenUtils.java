package org.raindrop.codegen.utils;

import cn.hutool.core.io.FileUtil;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.raindrop.codegen.dto.BaseColumnDto;
import org.raindrop.codegen.dto.BaseInfoDto;
import org.raindrop.codegen.dto.CreateTableDto;
import org.raindrop.codegen.entity.TableColumn;
import org.raindrop.codegen.entity.TableInfo;
import org.raindrop.codegen.mapper.CodeGenMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.raindrop.codegen.con.Template;

@Component
public class GenUtils {

    @Resource
    private CodeGenMapper codeGenMapper;

    /**
     * 创建文件
     *
     * @param tableDto
     * @throws IOException
     */
    public void createCode(CreateTableDto tableDto) throws IOException {
        // 删除原文件
        delFile();

        // 创建文件夹
        for (Template template : Template.values()) {
            FileUtil.mkdir(template.getPackage(tableDto.getPkg(), tableDto.getModule()));
        }
        String dataBaseName = tableDto.getDatabase();

        // 创建文件
        for (String tableName : tableDto.getTables()) {
            TableInfo tableInfo = codeGenMapper.queryTable(dataBaseName, tableName);
            Properties properties = new Properties();
            properties.load(new ClassPathResource("generator.properties").getInputStream());
            BaseInfoDto baseInfoDto = BaseInfoDto.builder()
                    .author(tableDto.getAuthor())
                    .pkg(tableDto.getPkg())
                    .module(tableDto.getModule())
                    .className(StringUtil.tableToClassName(tableInfo.getTableName()))
                    .classNameValue(StringUtil.lowerFirstCase(tableInfo.getTableName()))
                    .pathName(tableName)
                    .comments(tableInfo.getTableComment())
                    .tableName(tableInfo.getTableName())
                    .dateTime(DateUtil.format(LocalDateTime.now())).build();
            List<TableColumn> tableColumns = codeGenMapper.queryColumn(dataBaseName, tableName);
            List<BaseColumnDto> columnDtos = tableColumns.stream().map(obj -> {
                return BaseColumnDto.builder()
                        .priKey(obj.getColumnKey().equals("PRI"))
                        .comments(obj.getColumnComment())
                        .lowerAttrName(StringUtil.underscoreToCamelCase(obj.getColumnName()))
                        .columnName(obj.getColumnName())
                        .attrType(String.valueOf(properties.get(obj.getDataType())))
                        .build();
            }).collect(Collectors.toList());

            /* 首先，初始化运行时引擎，使用默认的配置 */
            Velocity.addProperty("class.resource.loader.class",
                    "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            Velocity.addProperty("resource.loader", "class");
            Velocity.init();

            /* 创建Context对象，然后把数据放进去 */

            VelocityContext context = new VelocityContext();

            context.put("base", baseInfoDto);
            context.put("columns", columnDtos);

            for (Template template : Template.values()) {
                createTemplate(template, context, baseInfoDto);
            }
        }
    }

    private void createTemplate(Template template,
                                VelocityContext context,
                                BaseInfoDto baseInfoDto) throws IOException {
        /* 渲染模板 */
        StringWriter sw = new StringWriter();
        Velocity.mergeTemplate(template.getVmFile(), "UTF-8", context, sw);

        // 创建文件
        File entity = FileUtil.newFile(template.getPackage(baseInfoDto.getPkg(), baseInfoDto.getModule()) + template.getFile(baseInfoDto.getClassName()));
        FileWriter fw = new FileWriter(entity);
        fw.write(sw.toString());
        fw.close();
    }

    public void delFile(){
        // 删除原文件
        FileUtil.del(System.getProperty("user.dir") + "/src");
    }
}
