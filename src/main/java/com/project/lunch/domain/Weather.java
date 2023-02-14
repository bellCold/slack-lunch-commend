package com.project.lunch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weather {
    private Double temp;
    private Double rainFallStatus;

    public String currentStatus() {
        return WeatherStatus.of(this.rainFallStatus);
    }
}
