package cn.fudges.role;

import cn.fudges.core.GeneratorCore;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/19
 */

public class AutoGenerate {

    static final String mysqlUrl = "jdbc:mysql://localhost:3306/nacos_devtest?useUnicode=true&characterEncoding=utf-8";
    static final String username = "nacos";
    static final String password = "nacos";
    static final List<String> tableNameList = Arrays.asList("role");

    public static void main(String[] args) {
        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpingyuan", "role", "cn.fudges.role");
        generatorCore.initMysql(mysqlUrl, username, password, tableNameList);
        generatorCore.generate(true, true);
    }
}
