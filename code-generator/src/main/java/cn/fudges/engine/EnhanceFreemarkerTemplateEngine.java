package cn.fudges.engine;

import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.builder.CustomFile;
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

public class EnhanceFreemarkerTemplateEngine extends FreemarkerTemplateEngine {
    @Override
    protected void outputCustomFile(@NotNull List<CustomFile> customFiles, @NotNull TableInfo tableInfo, @NotNull Map<String, Object> objectMap) {
        String entityName = tableInfo.getEntityName();
        for (CustomFile customFile : customFiles) {
            String fileName = String.format(customFile.getFilePath() + File.separator + entityName + "%s", customFile.getFileName());
            this.outputFile(new File(fileName), objectMap, customFile.getTemplatePath(), false);
        }
    }
}
