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
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class WithParamJobConfig {
    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job withParamJob() {
        return jobBuilderFactory.get("withParamJob")
                .start(withParamStep())
                .build();
    }

    @Bean
    @JobScope
    public Step withParamStep() {
        return stepBuilderFactory.get("withParamTasklet")
                .tasklet(withParamTasklet())
                .build();
    }


    @Bean
    @StepScope
    public Tasklet withParamTasklet() {
        return (contribution, chunkContext) -> {
            System.out.println("with Parameter Job");

            return RepeatStatus.FINISHED;
        };
    }
}