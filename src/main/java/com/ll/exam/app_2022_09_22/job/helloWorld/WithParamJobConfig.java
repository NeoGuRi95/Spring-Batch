package com.ll.exam.app_2022_09_22.job.helloWorld;

import jdk.jfr.StackTrace;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job withParamJob(Step withParamStep) {
        return jobBuilderFactory.get("withParamJob")
                .start(withParamStep)
                .build();
    }

    @Bean
    @JobScope
    public Step withParamStep(Tasklet withParamTasklet) {
        return stepBuilderFactory.get("withParamStep")
                .tasklet(withParamTasklet)
                .build();
    }


    @Bean
    @StepScope
    public Tasklet withParamTasklet(@Value("#{jobParameters[name]}") String name,
                                    @Value("#{jobParameters[age]}") int age) {
        return (contribution, chunkContext) -> {
            System.out.printf("with Parameter Job\n(name: \"%s\", age: %d)\n".formatted(name, age));

            return RepeatStatus.FINISHED;
        };
    }
}