package com.project.lunch.service;


import com.project.lunch.domain.SlackMessage;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackMessageService extends SlackService {
    public SlackMessageService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public void sendMessage(SlackMessage slackMessage) {
        String message = slackMessage.getMessage() + " " + slackMessage.getEmoji();
        super.sendSlackMessage(message);
    }
}
