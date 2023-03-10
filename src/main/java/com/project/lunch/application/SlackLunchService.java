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
        super.postSlackMessage("π μ€λμ μ μ¬ μΆμ² λ©λ΄λ " + Menu.recommendMenu(weatherStatus) + "μλλ€. λ§μκ² λμΈμ©!");
    }
}