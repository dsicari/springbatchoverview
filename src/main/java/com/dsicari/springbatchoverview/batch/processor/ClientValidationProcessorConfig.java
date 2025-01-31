package com.dsicari.springbatchoverview.batch.processor;

import com.dsicari.springbatchoverview.domain.Client;

import com.dsicari.springbatchoverview.exception.InvalidClientException;
import jakarta.validation.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Set;

@Slf4j
@Configuration
public class ClientValidationProcessorConfig {

    @Bean
    public ItemProcessor<Client, Client> clientValidationProcessor() {
        return client -> {
            
            if (client.phoneNumber() != null) {
                client = client.toBuilder()
                        .phoneNumber(client.phoneNumber().replaceAll("[^0-9+]", ""))
                        .build();
            }

            try (ValidatorFactory factory = Validation.buildDefaultValidatorFactory()) {
                Validator validator = factory.getValidator();
                Set<ConstraintViolation<Client>> violations = validator.validate(client);

                if(!violations.isEmpty()) {
                    log.error("Client {} has invalid fields: {}", client.firstName(), violations);
                    throw new InvalidClientException(violations.toString());
                }
            } catch (ValidationException e) {
                log.error("Client {} failed to validate", client.firstName(), e);
                return null;
            }

            return client;
        };
    }
}