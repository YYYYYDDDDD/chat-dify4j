package com.chat.dify4j.controller;

import com.chat.dify4j.handler.DifyApplicationChatHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.nio.charset.StandardCharsets;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
@Slf4j
public class SseController {

    private DifyApplicationChatHandler difyApplicationChatHandler;

    @GetMapping("/chat")
    public ResponseEntity<SseEmitter> chat(@RequestParam("type") String type,
                                             @RequestParam("apiKey") String apiKey,
                                             @RequestParam(value = "query", required = false) String query,
                                             @RequestParam(value = "inputs", required = false) String inputs,
                                             @RequestParam(value = "conversationId", required = false) String conversationId,
                                             @RequestParam("userId") String userId) throws JsonProcessingException {

        SseEmitter emitter = difyApplicationChatHandler.handle(type, apiKey, query, inputs, conversationId, userId);

        // 设置响应头
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(new org.springframework.http.MediaType("text", "event-stream", StandardCharsets.UTF_8));

        return new ResponseEntity<>(emitter, headers, HttpStatus.OK);
    }
}