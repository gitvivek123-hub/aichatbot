package com.spring.ollama.controller;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api")
public class ChatController {

    private final ChatClient chatClient;

    public ChatController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @GetMapping(value = "/chat-stream", produces = "text/event-stream")
    public Flux<String> stream(@RequestParam("q") String query) {

        return chatClient
                .prompt()
                .system("You are a helpful C programming tutor. Keep answers short.")
                .user(query)
                .stream()
                .content();
    }
}
