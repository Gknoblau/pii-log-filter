package dev.knoblauch.piilog.services;

import dev.knoblauch.piilog.data.LogEvent;

public interface PiiFilter {
    void obscurePiiData(LogEvent logEvent);
}
