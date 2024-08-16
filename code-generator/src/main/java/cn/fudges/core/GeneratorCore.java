package cn.fudges.core;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;

import java.util.Collections;
import java.util.List;

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

    private List<CustomFile> createCustomFileList() {
        // controller
        CustomFile customFile = new CustomFile.Builder()
                .fileName("Controller.java")
                .templatePath("templates/controller.java.ftl")
                .filePath(this.moduleName + "src/main/java/" + groupId + "")
                .build();

        return null;
    }

    public void generate(Boolean isOverwritePo, Boolean isOverwriteOther) {
        String outputDir = moduleName == null || moduleName.equals("") ? "src/main/java" : moduleName + "/src/main/java";

        FastAutoGenerator.create(this.mysqlUrl, this.username, this.password)
                .globalConfig(builder -> builder.author(this.author).outputDir(outputDir))
                .packageConfig(builder -> builder.parent(groupId)
                        .entity("po")
                        .mapper("dao.po")
                        .xml("mapper.po")
                )
                .strategyConfig( builder -> {
                    builder.addInclude(tableNameList)
                            .entityBuilder()
                            .javaTemplate("")
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .controllerBuilder()
                            .disable()
                            .serviceBuilder()
                            .disable();
                })
                .execute();
    }
}
