package com.project.lunch.api.request;

import lombok.Getter;

@Getter
public class SlackMessageDto {
    private String message;
    private String emoji;

    @Override
    public String toString() {
        return this.message + " " + this.emoji;
    }
}
