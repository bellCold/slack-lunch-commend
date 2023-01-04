package com.project.lunch.api;

import com.project.lunch.application.SlackWeatherService;
import com.project.lunch.domain.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SlackWeatherController {

    private final SlackWeatherService slackWeatherService;

    @GetMapping("/weather")
    public ResponseEntity<Void> getVillageWeather(@Nullable @RequestBody Weather weather) throws IOException {
        if (weather == null) {
            slackWeatherService.currentWeather();
        }
        return ResponseEntity.ok().build();
    }

}
