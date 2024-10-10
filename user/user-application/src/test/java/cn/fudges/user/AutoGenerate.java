package cn.fudges.user;

import cn.fudges.core.GeneratorCore;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/20
 */

public class AutoGenerate {

    static final String mysqlUrl = "jdbc:mysql://sit.fudges.cn:13307/cb_user?useUnicode=true&characterEncoding=utf-8";
    static final String username = "root";
    static final String password = "601202";
    static final List<String> tableNameList = Arrays.asList("user_password");

    public static void main(String[] args) {
        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpingyuan", "user", "cn.fudges.user");
        generatorCore.initMysql(mysqlUrl, username, password, tableNameList);
        generatorCore.generate(true, true);
    }
}
