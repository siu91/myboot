package org.siu.myboot.core.config.async;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 全局异步线程池
 * <p>
 * 其它使用异步时 ：@Async("globalAsyncExecutor")
 *
 * @Author Siu
 * @Date 2020/3/6 20:52
 * @Version 0.0.1
 */
@Slf4j
@Configuration
public class AsyncExecutorConfig {

    @Bean("globalAsyncExecutor")
    public Executor globalAsyncExecutor() {
        log.info("start asyncServiceExecutor");
        //使用 ShowInfoThreadPoolTaskExecutor 打印线程池信息
        ThreadPoolTaskExecutor executor = new ShowInfoThreadPoolTaskExecutor();
        //配置核心线程数
        executor.setCorePoolSize(5);
        //配置最大线程数
        executor.setMaxPoolSize(20);
        //配置队列大小
        executor.setQueueCapacity(99999);
        //配置线程池中的线程的名称前缀
        executor.setThreadNamePrefix("async-executor-");

        // rejection-policy：当pool已经达到max size的时候，如何处理新任务
        // CALLER_RUNS：不在新线程中执行任务，而是有调用者所在的线程来执行
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        //执行初始化
        executor.initialize();
        return executor;
    }
}
