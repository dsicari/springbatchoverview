package com.dsicari.springbatchoverview.batch;

import com.dsicari.springbatchoverview.batch.listener.StepLoggingListener;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ClientOnboardingJobConfig {

    private final JobRepository jobRepository;

    @Bean
    public Job clientOnboardingJob(StepLoggingListener stepLoggingListener, Step registerClientStep) {
        return new JobBuilder("clientOnboardingJob", jobRepository)
                .listener(stepLoggingListener)
                .start(registerClientStep)
                .build();
    }


}
