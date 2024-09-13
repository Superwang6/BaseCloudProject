package cn.fudges.core;

import cn.fudges.core.enums.CustomOutputFile;
import cn.fudges.engine.CustomFreemarkerTemplateEngine;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import org.apache.ibatis.type.JdbcType;

import java.util.*;

/**
 * 生成MYSQL 仓储层代码
 * 包含： 实体类、仓储接口、仓储实现
 *
 * @author 王平远
 * @since 2024/8/16
 */

public class GeneratorCore {
    private String author = "author";
    private String moduleName = "";
    private String groupId = "cn.fudges";
    private String mysqlUrl = "jdbc:mysql://localhost:3306/nacos_devtest?useUnicode=true&characterEncoding=utf-8";
    private String username = "nacos";
    private String password = "nacos";
    private List<String> tableNameList = Collections.singletonList("users");

    public void initBase(String author, String moduleName, String groupId) {
        this.author = author;
        this.moduleName = moduleName;
        this.groupId = groupId;
    }

    public void initMysql(String mysqlUrl, String username, String password, List<String> tableNameList) {
        this.mysqlUrl = mysqlUrl;
        this.username = username;
        this.password = password;
        this.tableNameList = tableNameList;
    }

    private List<CustomFile> createCustomFileList(Map<String, String> customPackageInfo) {
        // bo
        CustomFile customBoFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.bo.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.bo.name()))
                .templatePath("templates/bo.java.ftl")
                .build();

        // dao bo
        CustomFile customDaoBoFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.boDao.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.boDao.name()))
                .templatePath("templates/bo_dao.java.ftl")
                .build();

        // dao
        CustomFile customDaoFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.dao.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.dao.name()))
                .templatePath("templates/dao.java.ftl")
                .build();

        // mapper bo
        CustomFile customMapperBoFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.boMapper.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.boMapper.name()))
                .templatePath("templates/bo_mapper.xml.ftl")
                .build();

        // controller
        CustomFile customApiFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.api.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.api.name()))
                .templatePath("templates/api.java.ftl")
                .build();

        // controller impl
        CustomFile customApiImplFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.apiImpl.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.apiImpl.name()))
                .templatePath("templates/apiImpl.java.ftl")
                .build();

        // controller
        CustomFile customControllerFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.controller.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.controller.name()))
                .templatePath("templates/controller.java.ftl")
                .build();

        // request
        CustomFile customRequestFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.request.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.request.name()))
                .templatePath("templates/request.java.ftl")
                .build();

        // response
        CustomFile customResponseFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.response.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.response.name()))
                .templatePath("templates/response.java.ftl")
                .build();

        // service
        CustomFile customServiceFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.service.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.service.name()))
                .templatePath("templates/service.java.ftl")
                .build();

        // service impl
        CustomFile customServiceImplFile = new CustomFile.Builder()
                .fileName(CustomOutputFile.serviceImpl.getFileName())
                .filePath(customPackageInfo.get(CustomOutputFile.serviceImpl.name()))
                .templatePath("templates/serviceImpl.java.ftl")
                .build();


        return Arrays.asList(customBoFile,customMapperBoFile,customDaoBoFile,customDaoFile,customApiFile
                ,customApiImplFile, customControllerFile,customServiceFile,customServiceImplFile, customRequestFile, customResponseFile);
    }

    private Map<String,String> createCustomPackageInfo() {
        Map<String, String> customPackageInfo = new HashMap<>();
        // po
        customPackageInfo.put(CustomOutputFile.po.name(), this.groupId + ".entity.po");
        // bo
        customPackageInfo.put(CustomOutputFile.bo.name(), this.groupId + ".entity");
        // dao po
        customPackageInfo.put(CustomOutputFile.poDao.name(), this.groupId + ".dao.po");
        // dao bo
        customPackageInfo.put(CustomOutputFile.boDao.name(), this.groupId + ".dao.bo");
        // dao
        customPackageInfo.put(CustomOutputFile.dao.name(), this.groupId + ".dao");

        // mapper po
        customPackageInfo.put(CustomOutputFile.poMapper.name(), this.groupId + ".mapper.po");
        // mapper bo
        customPackageInfo.put(CustomOutputFile.boMapper.name(), this.groupId + ".mapper");


        // controller
        customPackageInfo.put(CustomOutputFile.api.name(), this.groupId + ".controller");
        // controller impl
        customPackageInfo.put(CustomOutputFile.apiImpl.name(), this.groupId + ".controller.apiImpl");
        // controller
        customPackageInfo.put(CustomOutputFile.controller.name(), this.groupId + ".controller");

        // request
        customPackageInfo.put(CustomOutputFile.request.name(), this.groupId + ".request");
        // response
        customPackageInfo.put(CustomOutputFile.response.name(), this.groupId + ".response");

        // service
        customPackageInfo.put(CustomOutputFile.service.name(), this.groupId + ".service");
        // service impl
        customPackageInfo.put(CustomOutputFile.serviceImpl.name(), this.groupId + ".service.impl");

        return customPackageInfo;
    }

    public void generate(Boolean isOverwritePo, Boolean isOverwriteOther) {
        Map<String, String> customPackageInfo = createCustomPackageInfo();
        List<CustomFile> customFileList = createCustomFileList(customPackageInfo);

        FastAutoGenerator.create(this.mysqlUrl, this.username, this.password)
                .globalConfig(builder -> builder.author(this.author))
                .dataSourceConfig(builder ->
                    builder.typeConvertHandler((globalConfig, typeRegistry, metaInfo) -> {
                        // 兼容旧版本转换成Integer
                        if (JdbcType.TINYINT == metaInfo.getJdbcType()) {
                            return DbColumnType.INTEGER;
                        }
                        return typeRegistry.getColumnType(metaInfo);
                    })
                )
                .packageConfig(builder -> builder.parent(groupId))
                .strategyConfig( builder -> {
                    builder.addInclude(tableNameList)
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解

                            .mapperBuilder()
                            .enableBaseColumnList()
                            .enableBaseResultMap()
                            .formatMapperFileName("%sPoMapper")
                            .formatXmlFileName("%sPoMapper")
                            .controllerBuilder()
                            .disable()
                            .serviceBuilder()
                            .disable();
                    if(isOverwritePo) {
                        //开启覆盖po
                        builder.entityBuilder().enableFileOverride();
                        builder.mapperBuilder().enableFileOverride();
                    }
                })
                .injectionConfig(builder -> builder
                        .beforeOutputFile((tableInfo, objectMap) -> {
                            TableInfo table = (TableInfo) objectMap.get("table");
                            Set<String> packages = table.getImportPackages();
                            List<String> voImportPackages = new ArrayList<>();
                            for (String importPackage : packages) {
                                if(!importPackage.contains("baomidou")) {
                                    voImportPackages.add(importPackage);
                                }
                            }
                            objectMap.put("moduleName", this.moduleName);
                            objectMap.put("voImportPackages", voImportPackages);
                            objectMap.put("parentPackage", this.moduleName + StringPool.DOT + this.moduleName + "-%s.src.main.java");
                            objectMap.put("customPackage", customPackageInfo);
                            objectMap.put("isOverwriteOther", isOverwriteOther);
                            objectMap.put("entityKC", camelToKebab((String) objectMap.get("entity")));
                        })
                        .customFile(customFileList))
                .templateEngine(new CustomFreemarkerTemplateEngine())
                .execute();
    }

    /**
     * 驼峰 转 kebab-case
     * @param input
     * @return
     */
    public String camelToKebab(String input) {
        // 使用正则表达式在大写字母前加上 "-"
        return input
                .replaceAll("([a-z])([A-Z]+)", "$1-$2") // 在小写字母和大写字母之间添加 "-"
                .toLowerCase(); // 转为小写
    }
}
