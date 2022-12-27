package com.project.lunch.service;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Slf4j
@Service
@RequiredArgsConstructor
public class SlackService {

    public void postSlackMessage(String message) {
        try {
            MethodsClient methods = Slack.getInstance().methods("xoxb-4572177822961-4553022238470-wGK42cmntMM4ZfE2IsIs6GEw");
            ChatPostMessageRequest request = ChatPostMessageRequest.builder()
                    .channel("#lunch")
                    .text(message)
                    .build();

            methods.chatPostMessage(request);

            log.info("보냄");
        } catch (SlackApiException | IOException e) {
            log.error(e.getMessage());
        }
    }

}
