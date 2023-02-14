package com.project.lunch.application;

import org.springframework.stereotype.Service;

@Service
public class SlackMessageService extends SlackService {

    @Override
    public void postSlackMessage(String message) {
        super.postSlackMessage(message);
    }
}
