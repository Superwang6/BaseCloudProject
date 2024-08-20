package cn.fudges.core.enums;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 王平远
 * @since 2024/8/19
 */
public enum CustomOutputFile {
    po("po", "Po.java", "infrastructure"),
    bo("bo", ".java", "infrastructure"),
    request("request", "Request.java","api"),
    response("response", "Response.java","api"),
    service("service", "Service.java","application"),
    serviceImpl("serviceImpl", "ServiceImpl.java","application"),
    poDao("poDao", "PoMapper.java", "infrastructure"),
    boDao("boDao", "BoMapper.java", "infrastructure"),
    dao("dao", "Dao.java", "infrastructure"),
    poMapper("poMapper", "PoMapper.xml", "infrastructure"),
    boMapper("boMapper", "BoMapper.xml", "infrastructure"),
    api("api", "Api.java", "api"),
    apiImpl("apiImpl", "ApiImpl.java","application"),
    parent("", "","");

    private String name;

    private String fileName;

    private String subModule;

    CustomOutputFile(String name, String fileName, String subModule) {
        this.name = name;
        this.fileName = fileName;
        this.subModule = subModule;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getSubModule() {
        return subModule;
    }

    public void setSubModule(String subModule) {
        this.subModule = subModule;
    }

    public static String getSubModuleByFileName(String fileName) {
        Map<String,String> map = new HashMap<>();
        for (CustomOutputFile value : CustomOutputFile.values()) {
            map.put(value.getFileName(),value.getSubModule());
        }
        return map.get(fileName);
    }
}
