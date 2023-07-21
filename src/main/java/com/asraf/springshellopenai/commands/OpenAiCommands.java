package com.asraf.springshellopenai.commands;

import com.asraf.springshellopenai.clients.OpenAiApiClient;
import com.asraf.springshellopenai.models.CompletionRequest;
import com.asraf.springshellopenai.models.CompletionResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public class OpenAiCommands {

    private final ObjectMapper jsonMapper;
    private final OpenAiApiClient client;

    public OpenAiCommands(ObjectMapper jsonMapper, OpenAiApiClient client) {
        this.jsonMapper = jsonMapper;
        this.client = client;
    }


    @ShellMethod(key = "chat-gpt", value = "I will answer like Chat GPT")
    public String getChatGptResponse(@ShellOption(defaultValue = "Hello") String searchText) throws Exception {
        return chatWithGpt3(searchText).trim();
    }

    private String chatWithGpt3(String message) throws Exception {
        var completion = CompletionRequest.defaultWith(message);
        var postBodyJson = jsonMapper.writeValueAsString(completion);
        var responseBody = client.postToOpenAiApi(postBodyJson);
        var completionResponse = jsonMapper.readValue(responseBody, CompletionResponse.class);
        return completionResponse.firstAnswer().orElseThrow();
    }
}
