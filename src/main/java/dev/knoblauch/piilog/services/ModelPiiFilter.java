package dev.knoblauch.piilog.services;

import dev.knoblauch.piilog.data.LogEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;

@Service
public class ModelPiiFilter implements PiiFilter {
    private final HttpClient httpClient;

    @Autowired
    public ModelPiiFilter(HttpClient httpClient) {
        this.httpClient = httpClient;
    }


    @Override
    public void obscurePiiData(LogEvent logEvent) {

    }
}
