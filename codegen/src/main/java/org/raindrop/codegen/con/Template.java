package org.raindrop.codegen.con;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Template{
    CONTROLLER("templates/Service.java.vm", "Service.java", false, "/service"),
    SERIVCE("templates/Controller.java.vm", "Controller.java", false, "/controller"),
    SERVICE_IMPL("templates/ServiceImpl.java.vm", "ServiceImpl.java", false, "/service/impl"),
    MAPPER("templates/Mapper.java.vm", "Mapper.java", false, "/mapper"),
    MAPPER_XML("templates/Mapper.xml.vm", "Mapper.xml", true, "/resources/mapper/"),
    ENTITY("templates/Entity.java.vm", ".java", false, "/entity");

    private String vmFile;
    private String outputFileName;
    private Boolean isResource;
    private String packageName;

    public String getPackage(String packageName, String moduleName){
        // 资源文件获取
        if (this.getIsResource()){
            return getRootPath() + this.getPackageName();
        }else{
            return (getRootPath() + "/main/java/" + packageName + "/" + moduleName + "/" + this.getPackageName()).replaceAll("\\.", "/") + "/";
        }
    }

    public String getFile(String className){
        // 资源文件获取
        return className + this.getOutputFileName();
    }

    public static String getRootPath(){
        return System.getProperty("user.dir") + "/src/CodeGenResult";
    }
}