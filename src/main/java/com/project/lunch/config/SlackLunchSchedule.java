package com.project.lunch.config;

import com.project.lunch.service.SlackLunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@RequiredArgsConstructor
@EnableScheduling
@Configuration
public class SlackLunchSchedule {
    // 0 0/1 * * * * -> 1분마다
    // 0 0 12 * * * -> 12시마
    private final SlackLunchService slackLunchService;
    private static final String LUNCH_ALERT_TIMER = "0 0 12 * * *";

    @Scheduled(cron = LUNCH_ALERT_TIMER)
    public void todayLunchRecommendMenu() {
        slackLunchService.sendLunchMessage();
    }
}