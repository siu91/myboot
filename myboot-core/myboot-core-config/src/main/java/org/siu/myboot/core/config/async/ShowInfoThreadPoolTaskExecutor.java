package org.siu.myboot.core.config.async;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.annotations.NonNull;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.util.concurrent.ListenableFuture;

import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 打印线程信息
 *
 * @Author Siu
 * @Date 2020/3/6 20:55
 * @Version 0.0.1
 */
@Slf4j
public class ShowInfoThreadPoolTaskExecutor extends ThreadPoolTaskExecutor {

    private void showThreadPoolInfo(String prefix) {
        ThreadPoolExecutor threadPoolExecutor = getThreadPoolExecutor();

        log.info("{}, {},taskCount [{}], completedTaskCount [{}], activeCount [{}], queueSize [{}]",
                this.getThreadNamePrefix(),
                prefix,
                threadPoolExecutor.getTaskCount(),
                threadPoolExecutor.getCompletedTaskCount(),
                threadPoolExecutor.getActiveCount(),
                threadPoolExecutor.getQueue().size());
    }

    @Override
    public void execute(@NonNull Runnable task) {
        showThreadPoolInfo("execute");
        super.execute(task);
    }

    @Override
    public void execute(@NonNull Runnable task, long startTimeout) {
        showThreadPoolInfo("execute with startTime");
        super.execute(task, startTimeout);
    }

    @Override
    @NonNull
    public Future<?> submit(@NonNull Runnable task) {
        showThreadPoolInfo("submit runnable");
        return super.submit(task);
    }

    @Override
    @NonNull
    public <T> Future<T> submit(@NonNull Callable<T> task) {
        showThreadPoolInfo("submit callable");
        return super.submit(task);
    }

    @Override
    @NonNull
    public ListenableFuture<?> submitListenable(Runnable task) {
        showThreadPoolInfo("submitListenable runnable");
        return super.submitListenable(task);
    }

    @Override
    @NonNull
    public <T> ListenableFuture<T> submitListenable(Callable<T> task) {
        showThreadPoolInfo("submitListenable callable");
        return super.submitListenable(task);
    }
}