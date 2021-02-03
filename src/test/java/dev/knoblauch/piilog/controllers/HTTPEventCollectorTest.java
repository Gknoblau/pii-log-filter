package dev.knoblauch.piilog.controllers;

import com.google.gson.Gson;
import dev.knoblauch.piilog.data.LogEvent;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest()
@AutoConfigureMockMvc
class HTTPEventCollectorTest {

    @Autowired
    private MockMvc mvc;

    private Gson gson = new Gson();

    @Test
    void eventCollector_200() throws Exception {
        LogEvent logEvent = LogEvent.builder().level("info").timestamp(123542345).logMessage("Hello World").build();
        mvc.perform(post("/collector/event")
                .content(gson.toJson(logEvent))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    void eventCollector_400() throws Exception {
        mvc.perform(post("/collector/event")
                .content("{Hello World}")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is4xxClientError());
    }
}