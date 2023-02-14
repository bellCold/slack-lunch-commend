package com.project.lunch.application;

import com.slack.api.Slack;
import com.slack.api.methods.MethodsClient;
import com.slack.api.methods.SlackApiException;
import com.slack.api.methods.request.chat.ChatPostMessageRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;

@Slf4j
abstract class SlackService {
    @Value(value = "${slack.token}")
    String token;

    @Value(value = "${slack.channel.monitor}")
    String channel;

    protected void postSlackMessage(String message) {
        try {
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
