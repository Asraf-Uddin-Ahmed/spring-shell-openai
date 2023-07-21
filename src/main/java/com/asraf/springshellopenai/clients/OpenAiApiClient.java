package com.asraf.springshellopenai.clients;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

@Component
public class OpenAiApiClient {

    @Value("${openai.apiKey}")
    private String openaiApiKey;

    private final String OPEN_AI_URL_COMPLETION = "https://api.openai.com/v1/completions";
    private final HttpClient client = HttpClient.newHttpClient();

    public String postToOpenAiApi(String requestBodyAsJson)
            throws IOException, InterruptedException {
        var request = HttpRequest.newBuilder()
                .uri(URI.create(OPEN_AI_URL_COMPLETION))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + openaiApiKey)
                .POST(BodyPublishers.ofString(requestBodyAsJson)).build();
        return client.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

}