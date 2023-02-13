package com.file.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * 线程池定义
 */
@Configuration
@EnableAsync
public class ThreadPoolConfig {

    @Bean("myThreadPoolExecutor")
    public Executor threadPoolExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(1);//核心线程数
        taskExecutor.setMaxPoolSize(10);//最大线程数
        taskExecutor.setQueueCapacity(100);//队列大小
        taskExecutor.setKeepAliveSeconds(60);//保持存活时长
        taskExecutor.setThreadNamePrefix("threadPoolExecutor-");//名称前缀
        //下面两个是线程关闭需要注意的问题
        taskExecutor.setWaitForTasksToCompleteOnShutdown(true);//线程池关闭的时候等待所有任务都完成再继续销毁其他的Bean，默认false
        taskExecutor.setAwaitTerminationSeconds(60);//设置线程池中 任务的等待时间，如果超过这个时间还没有销毁就 强制销毁，以确保应用最后能够被关闭，而不是阻塞住。
        return taskExecutor;
    }
}