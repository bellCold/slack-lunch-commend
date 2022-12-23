package com.project.lunch.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SlackService {

    private final RestTemplate restTemplate;

    public void postSlackMessage(String message) {
        String url = "https://hooks.slack.com/services/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";
        Map<String, String> messageBuilder = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        messageBuilder.put("text", message);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(messageBuilder, headers);
        restTemplate.postForEntity(url, request, String.class);
    }
}