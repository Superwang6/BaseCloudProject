package cn.fudges.core.enums;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 王平远
 * @since 2024/8/19
 */
public enum CustomOutputFile {
    po("po", "Po.java", SubModule.infrastructure.name(), ".entity.po", null),
    bo("bo", ".java",  SubModule.infrastructure.name(), ".entity", "templates/bo.java.ftl"),
    request("request", "Request.java", SubModule.api.name(), ".request", "templates/request.java.ftl"),
    response("response", "Response.java", SubModule.api.name(), ".response", "templates/response.java.ftl"),
    service("service", "Service.java", SubModule.application.name(), ".service", "templates/service.java.ftl"),
    serviceImpl("serviceImpl", "ServiceImpl.java", SubModule.application.name(), ".service.impl", "templates/serviceImpl.java.ftl"),
    poDao("poDao", "PoMapper.java",  SubModule.infrastructure.name(), ".dao.po", null),
    boDao("boDao", "BoMapper.java",  SubModule.infrastructure.name(), ".dao.bo", "templates/bo_dao.java.ftl"),
    dao("dao", "Dao.java",  SubModule.infrastructure.name(), ".dao", "templates/dao.java.ftl"),
    poMapper("poMapper", "PoMapper.xml",  SubModule.infrastructure.name(), ".mapper.po", null),
    boMapper("boMapper", "BoMapper.xml",  SubModule.infrastructure.name(), ".mapper", "templates/bo_mapper.xml.ftl"),
    api("controller", "Api.java", SubModule.api.name(), ".api", "templates/api.java.ftl"),
    apiImpl("apiImpl", "ApiImpl.java", SubModule.application.name(), ".controller.apiImpl", "templates/apiImpl.java.ftl"),
    controller("controller", "Controller.java", SubModule.application.name(), ".controller", "templates/controller.java.ftl");

    private String name;

    private String fileName;

    private String subModule;

    private String subPackage;

    private String templateUrl;

    CustomOutputFile(String name, String fileName, String subModule, String subPackage, String templateUrl) {
        this.name = name;
        this.fileName = fileName;
        this.subModule = subModule;
        this.subPackage = subPackage;
        this.templateUrl = templateUrl;
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

    public String getSubPackage() {
        return subPackage;
    }

    public void setSubPackage(String subPackage) {
        this.subPackage = subPackage;
    }

    public String getTemplateUrl() {
        return templateUrl;
    }

    public void setTemplateUrl(String templateUrl) {
        this.templateUrl = templateUrl;
    }

    public static String getSubModuleByFileName(String fileName) {
        Map<String, String> map = new HashMap<>();
        for (CustomOutputFile value : CustomOutputFile.values()) {
            map.put(value.getFileName(), value.getSubModule());
        }
        return map.get(fileName);
    }
}
