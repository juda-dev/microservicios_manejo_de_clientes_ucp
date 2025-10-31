package agendia.manejo_clientes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

@Configuration
@EnableAsync
public class AsyncConfig {

    private final Integer numberThreads = Runtime.getRuntime().availableProcessors();

    @Bean(name = "taskExecutor")
    public Executor asyncExecutor(){
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
            taskExecutor.setCorePoolSize(numberThreads);
            taskExecutor.setMaxPoolSize(numberThreads*2);
            taskExecutor.setQueueCapacity(100);
            taskExecutor.setThreadNamePrefix("email-async-");

        taskExecutor.initialize();

        return taskExecutor;
    }
}
