package cn.fudges.engine;

import cn.fudges.core.enums.CustomOutputFile;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.generator.config.ConstVal;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
import com.baomidou.mybatisplus.generator.config.builder.Entity;
import com.baomidou.mybatisplus.generator.config.builder.Mapper;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.List;
import java.util.Map;

/**
 * @author 王平远
 * @since 2024/8/16
 */

public class CustomFreemarkerTemplateEngine extends FreemarkerTemplateEngine {

    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        for (CustomFile customFile : customFiles) {
            String parentPath = String.format((String) objectMap.get("parentPackage"), entityName.toLowerCase(), CustomOutputFile.getSubModuleByFileName(customFile.getFileName()));
            String path = (parentPath + StringPool.DOT + customFile.getFilePath()).replaceAll("\\.", StringPool.BACK_SLASH + File.separator) + File.separator ;
            String fileName = String.format(entityName + "%s",customFile.getFileName());
            this.outputFile(new File(path + fileName), objectMap, customFile.getTemplatePath(), (Boolean) objectMap.get("isOverwriteOther"));
        }
    }

    private String getCustomPathInfo(Map<String,Object> objectMap, CustomOutputFile customOutputFile, String entityName, String subModule) {
        Map<String, String> customPackageInfo = (Map<String, String>) objectMap.get("customPackage");
        String path = String.format(customPackageInfo.get(customOutputFile.name()), entityName.toLowerCase());
        String parentPath = String.format((String) objectMap.get("parentPackage"), entityName.toLowerCase(), subModule);
        return (parentPath + StringPool.DOT + path).replaceAll("\\.", StringPool.BACK_SLASH + File.separator);
    }

    @Override
    protected void outputMapper(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        // MpMapper.java
        String entityName = tableInfo.getEntityName();
        String mapperPath = getCustomPathInfo(objectMap, CustomOutputFile.poDao, entityName, CustomOutputFile.poDao.getSubModule());
        Mapper mapper = this.getConfigBuilder().getStrategyConfig().mapper();
        if (mapper.isGenerateMapper()) {
            String mapperFile = String.format((mapperPath + File.separator + tableInfo.getMapperName() + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(mapperFile, OutputFile.mapper), objectMap, "templates/po_dao.java.ftl", getConfigBuilder().getStrategyConfig().mapper().isFileOverride());
        }
        // MpMapper.xml
        String xmlPath = getCustomPathInfo(objectMap, CustomOutputFile.poMapper, entityName, CustomOutputFile.poMapper.getSubModule());
        if (mapper.isGenerateMapperXml()) {
            String xmlFile = String.format((xmlPath + File.separator + tableInfo.getXmlName() + ConstVal.XML_SUFFIX), entityName);
            outputFile(getOutputFile(xmlFile, OutputFile.xml), objectMap, "templates/po_mapper.xml.ftl", getConfigBuilder().getStrategyConfig().mapper().isFileOverride());
        }
    }

    @Override
    protected void outputEntity(@NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        String entityPath = getCustomPathInfo(objectMap, CustomOutputFile.po, entityName, CustomOutputFile.po.getSubModule());
        Entity entity = this.getConfigBuilder().getStrategyConfig().entity();
        if (entity.isGenerate()) {
            String entityFile = String.format((entityPath + File.separator + "%sPo" + suffixJavaOrKt()), entityName);
            outputFile(getOutputFile(entityFile, OutputFile.entity), objectMap, "templates/po.java.ftl", getConfigBuilder().getStrategyConfig().entity().isFileOverride());
        }
    }
}
