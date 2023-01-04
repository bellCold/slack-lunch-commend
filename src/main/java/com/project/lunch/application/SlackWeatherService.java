package com.project.lunch.application;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static org.springframework.http.HttpHeaders.CONTENT_TYPE;

@Service
@RequiredArgsConstructor
public class SlackWeatherService {

    private String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    private String baseTime = LocalTime.now().minusMinutes(45).format(DateTimeFormatter.ofPattern("HHmm"));
    private String nx = "57";
    private String ny = "124";

    @Value("${weather.secretKey}")
    private String serviceKey;

    @Value("${weather.apiUrl}")
    private String apiUrl;

    private String getWeatherApiAddress() {
        return apiUrl +
                "?serviceKey=" + serviceKey +
                "&base_date=" + baseDate +
                "&base_time=" + baseTime +
                "&nx=" + nx +
                "&ny=" + ny;
    }

    public void currentWeather() throws IOException {
        Map<String, String> messageBuilder = new HashMap<>();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccessControlRequestMethod(HttpMethod.GET);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map<String, String>> request = new HttpEntity<>(messageBuilder, headers);

        System.out.println(getWeatherApiAddress());

        System.out.println(getStringFromURL(getWeatherApiAddress()));
    }

    private String getStringFromURL(String url) throws IOException {
        URL apiURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
        conn.setRequestMethod(HttpMethod.GET.toString());
        conn.setRequestProperty(CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);
        BufferedReader rd;

        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }

        rd.close();
        conn.disconnect();
        return sb.toString();
    }

}
