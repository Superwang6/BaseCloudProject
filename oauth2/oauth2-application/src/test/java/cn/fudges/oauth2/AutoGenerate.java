package cn.fudges.oauth2;

import cn.fudges.core.GeneratorCore;
import cn.fudges.core.enums.CustomOutputFile;
import cn.fudges.core.enums.SubModule;

import java.util.Arrays;
import java.util.List;

/**
 * @author 王平远
 * @since 2024/8/20
 */

public class AutoGenerate {

    static final String mysqlUrl = "jdbc:mysql://localhost:3306/cb_auth?useUnicode=true&characterEncoding=utf-8";
    static final String username = "root";
    static final String password = "root";
    static final List<String> tableNameList = Arrays.asList("auth_oauth_client");

    public static void main(String[] args) {
        GeneratorCore generatorCore = new GeneratorCore();
        generatorCore.initBase("wangpingyuan", "oauth2", "cn.fudges.oauth2")
                .initMysql(mysqlUrl, username, password, tableNameList)
                .rangeExcludeSubModule(SubModule.api)
                .rangeExInclude(CustomOutputFile.apiImpl)
                .generate(true, false);
    }
}
