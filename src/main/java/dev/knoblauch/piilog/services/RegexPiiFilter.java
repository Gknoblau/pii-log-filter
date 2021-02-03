package dev.knoblauch.piilog.services;

import dev.knoblauch.piilog.data.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.http.HttpClient;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegexPiiFilter implements PiiFilter {

    private final Pattern email = Pattern.compile("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])");
    private final Pattern phoneNumber = Pattern.compile("(\\+\\d{1,2}\\s)?\\(?\\d{3}\\)?[\\s.-]?\\d{3}[\\s.-]?\\d{4}");
    private final Pattern address = Pattern.compile("\\d{1,5}\\s\\w.\\s(\\b\\w*\\b\\s){1,2}\\w*\\.");
    private final Pattern ssn = Pattern.compile("^\\d{3}-?\\d{2}-?\\d{4}");

    @Autowired
    public RegexPiiFilter() {}

    @Override
    public void obscurePiiData(LogEvent logEvent) {
        String logMessage = logEvent.getLogMessage();

        //Filter Out Emails
        Matcher emailMatcher = email.matcher(logMessage);
        logMessage = emailMatcher.replaceAll("REDACTED");

        //Filter Out Phone Numbers
        Matcher phoneNumberMatcher = phoneNumber.matcher(logMessage);
        logMessage = phoneNumberMatcher.replaceAll("REDACTED");

        //Filter Out Addresses
        Matcher addressMatcher = address.matcher(logMessage);
        logMessage = addressMatcher.replaceAll("REDACTED");

        //Filter Out SSN
        Matcher ssnMatcher = ssn.matcher(logMessage);
        logMessage = ssnMatcher.replaceAll("REDACTED");

        logEvent.setLogMessage(logMessage);

    }
}
