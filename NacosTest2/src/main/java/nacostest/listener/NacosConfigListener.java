package nacostest.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.environment.EnvironmentChangeEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.stereotype.Component;

/**
 * @author zhg
 * @date 2023/8/17
 */
@Slf4j
@Component
public class NacosConfigListener implements ApplicationListener<EnvironmentChangeEvent> {

    @Autowired
    private ConfigurableEnvironment environment;

    @Override
    public void onApplicationEvent(EnvironmentChangeEvent environmentChangeEvent) {
        for (String key : environmentChangeEvent.getKeys()) {
            log.info("[onApplicationEvent][key({}) 最新 value 为 {}]", key, environment.getProperty(key));
        }
    }
}
