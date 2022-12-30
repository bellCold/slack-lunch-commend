package com.project.lunch.api;

import com.project.lunch.application.SlackService;
import com.project.lunch.domain.SlackMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SlackMessageController {
    private final SlackService slackService;

    @PostMapping("/slack")
    public void sendMessage(@RequestBody SlackMessage slackMessage) {
        String message = slackMessage.getMessage() + " " + slackMessage.getEmoji();
        slackService.postSlackMessage(message);
    }

}
