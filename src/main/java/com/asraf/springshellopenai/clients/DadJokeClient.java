package com.asraf.springshellopenai.clients;

import com.asraf.springshellopenai.models.DadJokeResponse;
import org.springframework.web.service.annotation.GetExchange;

public interface DadJokeClient {

    @GetExchange("/")
    DadJokeResponse random();
}
