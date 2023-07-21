package com.asraf.springshellopenai.configs;

import com.asraf.springshellopenai.clients.DadJokeClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.support.WebClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import java.time.Duration;

@Configuration
public class DadJokeConfig {
    @Bean
    DadJokeClient dadJokeClient() {
        WebClient client = WebClient.builder()
                .baseUrl("https://icanhazdadjoke.com")
                .defaultHeader("Accept", "application/json")
                .build();

        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builder(WebClientAdapter.forClient(client))
                .blockTimeout(Duration.ofSeconds(30))
                .build();
        return factory.createClient(DadJokeClient.class);
    }
}
