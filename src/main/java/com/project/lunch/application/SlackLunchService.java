package com.project.lunch.application;

import com.project.lunch.domain.Menu;
import org.springframework.stereotype.Service;

@Service
public class SlackLunchService extends SlackService {
    public void sendLunchMessage() {
        super.postSlackMessage("😀 오늘의 추천 메뉴는 " + Menu.recommendMenu() + "입니다. 맛있게 드세용!");
    }
}
