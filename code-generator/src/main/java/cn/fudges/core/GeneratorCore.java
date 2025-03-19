package cn.fudges.core;

import cn.fudges.core.enums.CustomOutputFile;
import cn.fudges.core.enums.SubModule;
import cn.fudges.engine.CustomFreemarkerTemplateEngine;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import org.apache.ibatis.type.JdbcType;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

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
    private String mysqlUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf-8";
    private String username = "test";
    private String password = "test";
    private List<String> tableNameList = Collections.singletonList("users");
    private List<CustomOutputFile> outputFileList = new ArrayList<>();

    public GeneratorCore initBase(String author, String moduleName, String groupId) {
        this.author = author;
        this.moduleName = moduleName;
        this.groupId = groupId;
        return rangeIncludeFile(CustomOutputFile.values());
    }

    public GeneratorCore initMysql(String mysqlUrl, String username, String password, List<String> tableNameList) {
        this.mysqlUrl = mysqlUrl;
        this.username = username;
        this.password = password;
        this.tableNameList = tableNameList;
        return this;
    }

    /**
     * 生成范围，默认全部，可以通过填入覆盖部分
     * @return
     */
    private GeneratorCore rangeIncludeFile(CustomOutputFile... files){
        outputFileList.addAll(Arrays.asList(files));
        return this;
    }

    /**
     * 生成范围，默认全部，可以通过填入排除部分
     * @return
     */
    public GeneratorCore rangeExInclude(CustomOutputFile... excludes){
        for (CustomOutputFile exclude : excludes) {
            outputFileList.remove(exclude);
        }
        return this;
    }

    /**
     * 生成范围，默认全部，可以通过填入排除模块
     * @return
     */
    public GeneratorCore rangeExcludeSubModule(SubModule... excludeSubModules) {
        Map<String, List<CustomOutputFile>> map = outputFileList.stream().collect(Collectors.groupingBy(CustomOutputFile::getSubModule));
        for (SubModule exclude : excludeSubModules) {
            map.remove(exclude.name());
        }
        outputFileList = map.values().stream().flatMap(List::stream).collect(Collectors.toList());
        return this;
    }

    private List<CustomFile> createCustomFileList(Map<String, String> customPackageInfo) {
        List<CustomFile> customFileList = new ArrayList<>();
        for (CustomOutputFile customOutputFile : outputFileList) {
            if(customOutputFile.getTemplateUrl() != null) {
                CustomFile file = new CustomFile.Builder()
                        .fileName(customOutputFile.getFileName())
                        .filePath(customPackageInfo.get(customOutputFile.name()))
                        .templatePath(customOutputFile.getTemplateUrl())
                        .build();
                customFileList.add(file);
            }
        }
        return customFileList;
    }

    private Map<String,String> createCustomPackageInfo() {
        Map<String, String> customPackageInfo = new HashMap<>();
        for (CustomOutputFile customOutputFile : CustomOutputFile.values()) {
            customPackageInfo.put(customOutputFile.name(), this.groupId + customOutputFile.getSubPackage());
        }
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
