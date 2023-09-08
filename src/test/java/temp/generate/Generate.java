package temp.generate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhg
 * @Description: 根据数据库表生成代码
 */
@RunWith(JUnit4.class)
@Slf4j
public class Generate {
    /**
     * 数据库配置 由于云服务器暂时不可用通过本地数据库进行模拟实现
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://172.16.8.69:3306/yfkb?useSSL=false&useUnicode=true&characterEncoding=utf" +
            "-8&serverTimezone=GMT%2B8&rewriteBatchedStatements=true", "root", "bonc.123");

    // 表有无创建时间日期字段
    private static boolean existDate = false;
    private static String createDateName = "createDate";
    private static String updateDateName = "updateDate";
    // 全局响应类名
    private static String allResultApiName = "R";

    @Test
    public void CodeGen() {
        log.info("Start automatic code generation");
        Map<String, String> fileMap = new HashMap<>();
        Map<String, Object> fieldMap = new HashMap<>();
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("zhg") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .disableOpenDir() // 生成后不弹出目录
                            .dateType(DateType.TIME_PACK) // 时间策略
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("temp.generate") // 设置父包名
                            .moduleName("tempGenerate") // 设置父包模块名
                            .entity("entity") // 设置普通类所在的包名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
            builder.addInclude("monthly_report_delivery_hours_analysis", "monthly_report_overall_data")//需要生成的表名称，多表可逗号隔开
                    .entityBuilder()
                    .enableLombok()
                    .enableChainModel()
                    .mapperBuilder()
                    .superClass(BaseMapper.class)
                    .serviceBuilder()
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImpl");
        }).injectionConfig(builder -> builder //不管用可能是velocity模板没配置的原因
                        .beforeOutputFile((tableInfo, objectMap) -> {
                            String entityName = tableInfo.getEntityName();
                            Map<String, Object> aPackageMap = (Map) objectMap.get("package");

                            objectMap.put("table_name", entityName.substring(0, 1).toLowerCase() + entityName.substring(1));
                            objectMap.put("model", aPackageMap.get("Parent") + ".model");
                            objectMap.put("bean", entityName.replace("PO", ""));
                            objectMap.put("vo", entityName + "VO");
                            objectMap.put("convert", entityName + "Convert");
                            objectMap.put("dto", entityName + "DTO");
                            objectMap.put("query", entityName + "Query");
                            diyConfig(objectMap);

                            //自定义生成文件配置
                            fileMap.put("/model/vo/" + entityName + "VO.java", "/template/vo.java.vm");
                            fileMap.put("/model/convert/" + entityName + "Convert.java", "/template/convert.java.vm");
                            fileMap.put("/model/dto/" + entityName + "DTO.java", "/template/dto.java.vm");
                            fileMap.put("/model/query/" + entityName + "Query.java", "/template/query.java.vm");
                        })
                // 自定义属性，模板变量
//                .customMap(fieldMap)
//                .customFile(fileMap)
        ).execute();
    }
    /**
     * 自定义模板变量配置
     * 主要用于生成一些特殊需求
     *
     * @param objectMap
     */
    private static void diyConfig(Map<String, Object> objectMap) {
        //设定entityLombokModel为true,使用lombok
        objectMap.put("entityLombokModel", true);
        //表有无创建时间日期字段
        objectMap.put("existDate", existDate);
        //时间字段set方法定义
        objectMap.put("setCreateDate", "set" + createDateName.substring(0, 1).toUpperCase() + createDateName.substring(1));
        objectMap.put("setUpdateDate", "set" + updateDateName.substring(0, 1).toUpperCase() + updateDateName.substring(1));
        objectMap.put("ApiResult", allResultApiName);
        objectMap.put("baseResultMap", true);
        objectMap.put("baseColumnList", true);
    }
}