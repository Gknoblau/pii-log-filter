package dev.knoblauch.piilog.services;

import dev.knoblauch.piilog.data.LogEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LogIndexer {

    public void indexLog(LogEvent logEvent) {
        log.info("Log Message to Index: {}", logEvent.getLogMessage());
        // Add log to index
    }
}
