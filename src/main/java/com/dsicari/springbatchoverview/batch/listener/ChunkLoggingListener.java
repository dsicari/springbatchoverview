package com.dsicari.springbatchoverview.batch.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ChunkListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.context.annotation.Configuration;
@Slf4j
@Configuration
public class ChunkLoggingListener implements ChunkListener {

    @Override
    public void beforeChunk(ChunkContext context) {
        log.debug("Chunk initiated");
    }

    @Override
    public void afterChunk(ChunkContext context) {
        log.debug("Chunk processed readCount: {}, processSkipCount: {}, writeCount: {}, commitCount: {}",
                context.getStepContext().getStepExecution().getReadCount(),
                context.getStepContext().getStepExecution().getProcessSkipCount(),
                context.getStepContext().getStepExecution().getWriteCount(),
                context.getStepContext().getStepExecution().getCommitCount());
    }

    @Override
    public void afterChunkError(ChunkContext context) {
        log.debug("Chunk process error");
    }
}
