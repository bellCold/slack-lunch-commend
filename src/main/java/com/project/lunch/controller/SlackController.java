package com.project.lunch.controller;

import com.project.lunch.domain.SlackMessage;
import com.project.lunch.service.SlackService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SlackController {

    private final SlackService slackService;

    @PostMapping("/slack")
    public void sendMessage(@RequestBody SlackMessage slackMessage) {
        String message = slackMessage.getMessage() + " " + slackMessage.getEmoji();
        slackService.postSlackMessage(message);
    }
}
