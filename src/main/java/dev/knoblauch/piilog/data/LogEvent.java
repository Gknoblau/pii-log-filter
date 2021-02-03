package dev.knoblauch.piilog.data;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LogEvent {
    private final long timestamp;
    private final String level;
    private String logMessage;
}
