package com.project.lunch.application;

import com.project.lunch.domain.Menu;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class SlackLunchService extends SlackService {
    private final WeatherService weatherService;

    public void sendLunchMessage() throws IOException {
        String weatherStatus = weatherService.getCurrentWeatherStatus();
        super.postSlackMessage("😀 오늘의 점심 추천 메뉴는 " + Menu.recommendMenu(weatherStatus) + "입니다. 맛있게 드세용!");
    }
}