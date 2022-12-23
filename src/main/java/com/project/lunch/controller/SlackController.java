package com.project.lunch.controller;

import com.project.lunch.domain.SlackMessage;
import com.project.lunch.service.SlackMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SlackController {

    private final SlackMessageService slackMessageService;

    @PostMapping("/slack")
    public ResponseEntity<Void> sendMessage(@RequestBody SlackMessage slackMessage) {
        slackMessageService.sendMessage(slackMessage);
        return ResponseEntity.ok().build();
    }
}
