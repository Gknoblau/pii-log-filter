package dev.knoblauch.piilog.controllers;

import dev.knoblauch.piilog.data.LogEvent;
import dev.knoblauch.piilog.services.LogIndexer;
import dev.knoblauch.piilog.services.ModelPiiFilter;
import dev.knoblauch.piilog.services.RegexPiiFilter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HTTPEventCollector {
    private final RegexPiiFilter regexPiiFilter;
    private final ModelPiiFilter modelPiiFilter;
    private final LogIndexer logIndexer;

    @Autowired
    public HTTPEventCollector(RegexPiiFilter regexPiiFilter, ModelPiiFilter modelPiiFilter, LogIndexer logIndexer) {
        this.regexPiiFilter = regexPiiFilter;
        this.modelPiiFilter = modelPiiFilter;
        this.logIndexer = logIndexer;
    }

    @PostMapping("/collector/event")
    public void eventCollector(@RequestBody LogEvent logEvent) {
        log.info("Received Event");
        regexPiiFilter.obscurePiiData(logEvent);
        modelPiiFilter.obscurePiiData(logEvent);
        logIndexer.indexLog(logEvent);
    }

}
