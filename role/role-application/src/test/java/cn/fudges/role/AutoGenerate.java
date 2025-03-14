package cn.fudges.role;

import cn.fudges.core.GeneratorCore;
import freemarker.core.Environment;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/19
 */

public class AutoGenerate {

    static final String mysqlUrl = "jdbc:mysql://local.fudges.cn:13307/cb_role?useUnicode=true&characterEncoding=utf-8";
    static final String username = System.getenv("FUDGES_MYSQL_USERNAME");
    static final String password = System.getenv("FUDGES_MYSQL_PASSWORD");
    static final List<String> tableNameList = Arrays.asList("role");

    public static void main(String[] args) {
        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpingyuan", "role", "cn.fudges.role");
        generatorCore.initMysql(mysqlUrl, username, password, tableNameList);
        generatorCore.generate(true, true);
    }
}
