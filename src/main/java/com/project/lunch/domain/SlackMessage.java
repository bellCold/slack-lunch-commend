package com.project.lunch.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SlackMessage {

    private String message;
    private String emoji;

}
