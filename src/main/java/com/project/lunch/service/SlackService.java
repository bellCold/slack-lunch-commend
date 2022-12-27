package com.project.lunch.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackService {

    private final RestTemplate restTemplate;

    public void sendSlackMessage(String message) {
        Map<String, String> messageBuilder = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();

        headers.setContentType(MediaType.APPLICATION_JSON);
        messageBuilder.put("text", message);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(messageBuilder, headers);

        String url = "https://w1671775896-0o7589189.slack.com/archives/C04GD0BKGPP";
        restTemplate.postForEntity(url, request, String.class);
    }

    @Value(value = "${slack.token}")
    String token;
    @Value(value = "${slack.channel.monitor}")
    String channel;

    public void postSlackMessage(String message){
        try{
            MethodsClient methods = Slack.getInstance().methods(token);
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel(channel)
                    .text(message)
                    .build();

            methods.chatPostMessage(request);

            log.info("보냄");
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

}
