package TestController.config;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;

/**
 * @author zhg
 * @date 2023/8/28
 */
// @Slf4j 通过自动装配注入bean时， slf4j还未初始化
//@AutoConfiguration
public class AutoConfigurationTest {

    private static final Logger log = LoggerFactory.getLogger(AutoConfigurationTest.class);

    static{
        log.info("AutoConfigurationTest 类初始化");
    }

    @PostConstruct
    public void init(){
      // 并没有执行
      log.info("AutoConfigurationTest 自动装配测试");
    }
}
