package dev.knoblauch.piilog.services;

import dev.knoblauch.piilog.data.LogEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RegexPiiFilterTest {

    private RegexPiiFilter regexPiiFilter;

    @BeforeEach
    void before() {
        regexPiiFilter = new RegexPiiFilter();
    }

    @Test
    void obscurePiiData_PhoneNumber_Match0() {
        LogEvent event = LogEvent.builder().logMessage("7804571234").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("REDACTED", event.getLogMessage());
    }

    @Test
    void obscurePiiData_PhoneNumber_Match1() {
        LogEvent event = LogEvent.builder().logMessage("Phone 7804571234").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Phone REDACTED", event.getLogMessage());
    }

    @Test
    void obscurePiiData_PhoneNumber_Match2() {
        LogEvent event = LogEvent.builder().logMessage("Phone 7804571234 Test").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Phone REDACTED Test", event.getLogMessage());
    }

    @Test
    void obscurePiiData_PhoneNumber_NoMatch() {
        LogEvent event = LogEvent.builder().logMessage("Phone Test").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Phone Test", event.getLogMessage());
    }

    @Test
    void obscurePiiData_Email_Match0() {
        LogEvent event = LogEvent.builder().logMessage("hello@google.com").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("REDACTED", event.getLogMessage());
    }

    @Test
    void obscurePiiData_Email_Match1() {
        LogEvent event = LogEvent.builder().logMessage("Email hello@google.com").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Email REDACTED", event.getLogMessage());
    }

    @Test
    void obscurePiiData_Email_Match2() {
        LogEvent event = LogEvent.builder().logMessage("Email hello@google.com Test").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Email REDACTED Test", event.getLogMessage());
    }

    @Test
    void obscurePiiData_Email_NoMatch() {
        LogEvent event = LogEvent.builder().logMessage("Email Test").build();
        regexPiiFilter.obscurePiiData(event);
        assertEquals("Email Test", event.getLogMessage());
    }
}