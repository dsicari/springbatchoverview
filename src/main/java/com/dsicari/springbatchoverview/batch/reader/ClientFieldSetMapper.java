package com.dsicari.springbatchoverview.batch.reader;

import com.dsicari.springbatchoverview.domain.Client;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.lang.NonNull;
import org.springframework.validation.BindException;

public class ClientFieldSetMapper implements FieldSetMapper<Client> {

    @Override
    public @NonNull Client mapFieldSet(@NonNull FieldSet fieldSet) throws BindException {
        return new Client(
                fieldSet.readString("firstName"),
                fieldSet.readString("lastName"),
                fieldSet.readInt("age"),
                fieldSet.readString("address"),
                fieldSet.readString("email"),
                fieldSet.readString("phoneNumber")
        );
    }
}