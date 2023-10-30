package com.example.musicrequestapp.global.config;

import org.apache.tomcat.util.threads.VirtualThreadExecutor;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@EnableAsync
@Configuration
public class AsyncConfig implements AsyncConfigurer, DisposableBean {

    private ExecutorService taskExecutor;

    @Override
    public Executor getAsyncExecutor() {
        taskExecutor = Executors.newFixedThreadPool(20);
        Executor virtualThreadExecutor =
                new VirtualThreadExecutor("VT : ");

        return task -> taskExecutor.execute(
                () -> virtualThreadExecutor.execute(task));
    }

    @Override
    public void destroy() {
        if (taskExecutor != null) {
            taskExecutor.shutdown();
        }
    }

}
