package com.dsicari.springbatchoverview.batch.writer;

import com.dsicari.springbatchoverview.domain.Client;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@RequiredArgsConstructor
public class ClientJdbcBatchItemWriterConfig {

    @Bean
    public JdbcBatchItemWriter<Client> clientJdbcBatchItemWriter(DataSource dataSource){
        return new JdbcBatchItemWriterBuilder<Client>()
                .sql("INSERT INTO client (first_name, last_name) VALUES (:firstName, :lastName)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
}
