package com.dsicari.springbatchoverview.batch.reader;

import com.dsicari.springbatchoverview.domain.Client;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class ClientFileReaderConfig {

    @Bean
    public FlatFileItemReader<Client> clientFileReader() {
        return new FlatFileItemReaderBuilder<Client>()
                .name("clientFileReader")
                .resource(new FileSystemResource("files/sample-data-35-records-1-fail.csv"))
                .linesToSkip(1)
//                .delimited()
//                .delimiter(";")
//                .names("firstName", "lastName", "age", "address", "email", "phoneNumber")
//                .targetType(Client.class)
                .lineMapper(createClientLineMapper())
                .build();
    }

    private LineMapper<Client> createClientLineMapper() {
        DefaultLineMapper<Client> clientLineMapper = new DefaultLineMapper<>();
        LineTokenizer clientLineTokenizer = createClientLineTokenizer();
        FieldSetMapper<Client> clientFieldSetMapper = createClientFieldSetMapper();

        clientLineMapper.setLineTokenizer(clientLineTokenizer);
        clientLineMapper.setFieldSetMapper(clientFieldSetMapper);

        return clientLineMapper;
    }

    private LineTokenizer createClientLineTokenizer() {
        DelimitedLineTokenizer clientLineTokenizer = new DelimitedLineTokenizer();
        clientLineTokenizer.setDelimiter(";");
        clientLineTokenizer.setNames("firstName", "lastName", "age", "address", "email", "phoneNumber");

        return clientLineTokenizer;
    }

    private FieldSetMapper<Client> createClientFieldSetMapper() {
        return fieldSet -> new Client(
                fieldSet.readString("firstName"),
                fieldSet.readString("lastName"),
                fieldSet.readInt("age"),
                fieldSet.readString("address"),
                fieldSet.readString("email"),
                fieldSet.readString("phoneNumber")
        );
    }

}
