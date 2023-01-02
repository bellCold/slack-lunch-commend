package com.project.lunch.global.config;

import com.project.lunch.application.SlackLunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@RequiredArgsConstructor
@EnableScheduling
public class SlackLunchScheduleConfig {
    // 0 0/1 * * * * -> 1분마다
    // 0 0 12 * * * -> 12시마
    // 0/10 * * * * ? -> 10초마다
    private final SlackLunchService slackLunchService;
    private static final String LUNCH_ALERT_TIMER = "0/10 * * * * ? ";

    @Scheduled(cron = LUNCH_ALERT_TIMER)
    public void todayLunchRecommendMenu() {
        slackLunchService.sendLunchMessage();
    }
}