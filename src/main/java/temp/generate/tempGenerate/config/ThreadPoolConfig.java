package temp.generate.tempGenerate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ThreadPoolExecutor;

@Component
public class ThreadPoolConfig {

    @Bean("wordGenerate")
    public ThreadPoolTaskExecutor getWordGenerateExecutor() {
        // 获取CPU核数
        int coreNum = Runtime.getRuntime().availableProcessors();
        if (coreNum < 4) {
            coreNum = 4;
        }
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(coreNum);
        threadPoolTaskExecutor.setMaxPoolSize(coreNum << 1);
        threadPoolTaskExecutor.setQueueCapacity(coreNum << 4);
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        threadPoolTaskExecutor.setAllowCoreThreadTimeOut(true);
        threadPoolTaskExecutor.setKeepAliveSeconds(180);
        threadPoolTaskExecutor.setThreadNamePrefix("WordGenerate-");
        threadPoolTaskExecutor.initialize();
        return threadPoolTaskExecutor;
    }
}
