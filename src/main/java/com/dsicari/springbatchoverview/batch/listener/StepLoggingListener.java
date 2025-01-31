package com.dsicari.springbatchoverview.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.AfterStep;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.ZoneOffset;

@Component
@Slf4j
public class StepLoggingListener {

    @BeforeStep
    public void beforeStep(StepExecution stepExecution) {
        log.debug("Step initiated: {}", stepExecution.getStepName());
    }

    @AfterStep
    public void afterStep(StepExecution stepExecution) {
        log.debug("Step finished: {}, readCount: {}, processSkipCount: {}, writeCount: {}, ",
                stepExecution.getStepName(),
                stepExecution.getReadCount(),
                stepExecution.getProcessSkipCount(),
                stepExecution.getWriteCount());

        if(stepExecution.getEndTime() != null && stepExecution.getStartTime() != null){
            Duration stepDuration = Duration.between(stepExecution.getStartTime().toInstant(ZoneOffset.ofHours(0)),
                stepExecution.getEndTime().toInstant(ZoneOffset.ofHours(0)));
            log.debug("Time to process step: {} ms", stepDuration.toMillis());
        }
    }
}
