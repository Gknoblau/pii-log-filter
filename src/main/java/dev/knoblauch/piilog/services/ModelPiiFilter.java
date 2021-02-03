package dev.knoblauch.piilog.services;

import com.google.gson.Gson;
import com.squareup.okhttp.*;
import dev.knoblauch.piilog.config.AppProperties;
import dev.knoblauch.piilog.data.LogEvent;
import dev.knoblauch.piilog.data.ModelResponse;
import jdk.jfr.ContentType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
public class ModelPiiFilter implements PiiFilter {
    private final AppProperties appProperties;
    private final OkHttpClient httpClient;
    private final Gson gson;

    @Autowired
    public ModelPiiFilter(AppProperties appProperties, OkHttpClient httpClient, Gson gson) {
        this.appProperties = appProperties;
        this.httpClient = httpClient;
        this.gson = gson;
    }

    @Override
    public void obscurePiiData(LogEvent logEvent)  {
        Request request = new Request.Builder()
                .url(appProperties.getPiiDetectorUrl())
                .post(RequestBody.create(MediaType.parse("application/json"), gson.toJson(logEvent)))
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                ModelResponse modelResponse = gson.fromJson(response.body().string(), ModelResponse.class);
                log.info("Probability={}", modelResponse.getProbability());
                if (modelResponse.getIs_sensitive()) {
                    log.warn("There is some PII in the logs");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
