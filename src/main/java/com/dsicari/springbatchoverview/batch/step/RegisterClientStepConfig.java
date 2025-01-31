package com.dsicari.springbatchoverview.batch.step;

import com.dsicari.springbatchoverview.batch.listener.ChunkLoggingListener;
import com.dsicari.springbatchoverview.batch.listener.StepLoggingListener;
import com.dsicari.springbatchoverview.domain.Client;
import com.dsicari.springbatchoverview.exception.InvalidClientException;
import jakarta.validation.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class RegisterClientStepConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;

    @Bean
    public Step registerClientStep(
            FlatFileItemReader<Client> clientFlatFileReader,
            ItemProcessor<Client, Client> clientValidationProcessor,
            JdbcBatchItemWriter<Client> clientJdbcBatchItemWriter,
            ChunkLoggingListener chunkLoggingListener,
            StepLoggingListener stepLoggingListener) {

        return new StepBuilder("registerClientStep", jobRepository)
                .<Client, Client>chunk(3, transactionManager)
                .reader(clientFlatFileReader)
                .processor(clientValidationProcessor)
                .writer(clientJdbcBatchItemWriter)
                .faultTolerant()
                .skip(InvalidClientException.class)
                .listener(chunkLoggingListener)
                .listener(stepLoggingListener)
                .build();
    }
}
