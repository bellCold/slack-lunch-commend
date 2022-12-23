package com.project.lunch.service;

import com.project.lunch.domain.Menu;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SlackLunchService extends SlackService {
    public SlackLunchService(RestTemplate restTemplate) {
        super(restTemplate);
    }

    public void sendLunchMessage() {
        super.sendSlackMessage("😀 오늘의 추천 메뉴는 " + Menu.recommendMenu() + "입니다. 맛있게 드세용!");
    }
}
