package com.file;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.util.Collections;

/**
 * @author andy
 * @Description: 根据数据库表生成代码
 */
@RunWith(JUnit4.class)
@Slf4j
public class CodeGenTest {
    /**
     * 数据库配置 由于云服务器暂时不可用通过本地数据库进行模拟实现
     */
    private static final DataSourceConfig.Builder DATA_SOURCE_CONFIG = new DataSourceConfig
            .Builder("jdbc:mysql://localhost:3306/big_file?useSSL=false&useUnicode=true&characterEncoding=utf" +
            "-8&serverTimezone=GMT%2B8", "root", "123456");

    @Test
    public void CodeGen() {
        log.info("Start automatic code generation");
        FastAutoGenerator.create(DATA_SOURCE_CONFIG)
                .globalConfig(builder -> {
                    builder.author("andy") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .outputDir(System.getProperty("user.dir") + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.file") // 设置父包名
                            // .moduleName("system") // 设置父包模块名
                            .pathInfo(Collections.singletonMap(OutputFile.mapper.xml,
                                    System.getProperty("user.dir") + "/src/main/resources/mapper/")); // 设置mapperXml生成路径
                }).strategyConfig(builder -> {
            builder.addInclude("supplier_good_copy")//需要生成的表名称，多表可逗号隔开
                    .entityBuilder()
                    .enableLombok()
                    .enableChainModel()
                    .mapperBuilder()
                    .superClass(BaseMapper.class)
                    .serviceBuilder()
                    .formatServiceFileName("%sService")
                    .formatServiceImplFileName("%sServiceImp");
        }).execute();
    }
}
