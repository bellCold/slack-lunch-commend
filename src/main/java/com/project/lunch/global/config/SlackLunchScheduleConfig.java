package com.project.lunch.global.config;

import com.project.lunch.application.SlackLunchService;
import com.project.lunch.global.annotaion.LunchScheduleConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;

@LunchScheduleConfig
@RequiredArgsConstructor
public class SlackLunchScheduleConfig {
    // 0 0/1 * * * * -> 1분마다
    // 0 0 12 * * * -> 12시마
    // 0/10 * * * * ? -> 10초마다
    private static final String LUNCH_ALERT_TIMER = "0/10 * * * * ?";

    private final SlackLunchService slackLunchService;

    @Scheduled(cron = LUNCH_ALERT_TIMER)
    public void todayLunchRecommendMenu() throws IOException {
        slackLunchService.sendLunchMessage();
    }

}