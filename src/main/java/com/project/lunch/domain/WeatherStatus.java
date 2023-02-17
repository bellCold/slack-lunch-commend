package com.project.lunch.domain;

import java.util.Arrays;
import java.util.Locale;

public enum WeatherStatus {
    CLEAR(0),
    RAIN(1),
    SNOW(2);

    private final int weatherCode;

    WeatherStatus(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public static String of(Double rainFallStatus) {
        return Arrays.stream(WeatherStatus.values()).filter(value -> value.weatherCode == rainFallStatus).findFirst().get().toString().toLowerCase(Locale.ROOT);
    }
}
