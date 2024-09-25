package cn.fudges;

import cn.fudges.core.GeneratorCore;
import cn.fudges.core.enums.SubModule;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/16
 */
public class AutoGenerate {
    static final String mysqlUrl = "jdbc:mysql://localhost:3306/nacos_devtest?useUnicode=true&characterEncoding=utf-8";
    static final String username = "nacos";
    static final String password = "nacos";
    static final List<String> tableNameList = Arrays.asList("roles");

    public static void main(String[] args) {

        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpeiyuan", "code-generator", "cn.fudges.init")
                .initMysql(mysqlUrl, username, password, tableNameList)
                .rangeExcludeSubModule(SubModule.api)
                .generate(true, true);

    }
}
