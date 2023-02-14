package com.project.lunch.domain;

import java.util.Arrays;
import java.util.Locale;

public enum WeatherStatus {
    RAIN(1),
    SNOW(2),
    CLEAR(0);

    private final int weatherCode;

    WeatherStatus(int weatherCode) {
        this.weatherCode = weatherCode;
    }

    public static String of(Double rainFallStatus) {
        return Arrays.stream(WeatherStatus.values()).filter(value -> value.weatherCode == rainFallStatus).findFirst().get().toString().toLowerCase(Locale.ROOT);
    }
}
