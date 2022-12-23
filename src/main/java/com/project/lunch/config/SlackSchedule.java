package com.project.lunch.config;

import com.project.lunch.service.SlackLunchService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@EnableScheduling
@Component
public class SlackSchedule {

    private final SlackLunchService slackLunchService;
    private static final String LUNCH_ALERT_TIMER = "0 * * * * *";

    @Scheduled(cron = LUNCH_ALERT_TIMER)
    public void todayLunchRecommendMenu() {
        slackLunchService.sendLunchMessage();
    }
}