package com.dsicari.springbatchoverview.batch.reader;

import com.dsicari.springbatchoverview.domain.Client;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;

public class ClientLineMapper {

    public LineMapper<Client> clientLineMapper() {
        DefaultLineMapper<Client> clientLineMapper = new DefaultLineMapper<>();
        LineTokenizer clientLineTokenizer = createClientLineTokenizer();

        clientLineMapper.setLineTokenizer(clientLineTokenizer);
        clientLineMapper.setFieldSetMapper(createClientFieldSetMapper());

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
