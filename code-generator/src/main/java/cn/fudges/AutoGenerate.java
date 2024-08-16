package cn.fudges;

import cn.fudges.core.GeneratorCore;
import cn.fudges.engine.EnhanceFreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;

import java.util.Collections;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/16
 */
public class AutoGenerate {
    static final String mysqlUrl = "jdbc:mysql://localhost:3306/nacos_devtest?useUnicode=true&characterEncoding=utf-8";
    static final String username = "nacos";
    static final String password = "nacos";
    static final List<String> tableNameList = Collections.singletonList("users");

    public static void main(String[] args) {
//        CustomFile customFile = new CustomFile.Builder()
//                .fileName("Controller.java")
//                .filePath("D:\\adminw\\Code\\own_project\\BaseProject\\server")
//                .templatePath("templates/controller.java.ftl")
//                .enableFileOverride()
//                .build();

        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpeiyuan", "code-generator", "cn.fudges.init");
        generatorCore.initMysql(mysqlUrl, username, password, tableNameList);
        generatorCore.generate(true, false);

    }
}
