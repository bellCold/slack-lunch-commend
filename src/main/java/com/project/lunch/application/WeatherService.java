package com.project.lunch.application;

import com.project.lunch.domain.Weather;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class WeatherService {

    @Value(value = "${weather.service.key}")
    String serviceKey;

    private final String URL = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";
    private final String CURRENT_DATE = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
    private final String BASE_TIME = LocalTime.now().minusHours(1).format(DateTimeFormatter.ofPattern("HHmm"));
    private final String NX = "57";
    private final String NY = "124";

    private Weather checkWeather() throws IOException {
        StringBuilder urlBuilder = new StringBuilder(URL);
        urlBuilder.append("?" + URLEncoder.encode("serviceKey", UTF_8) + "=" + serviceKey);
        urlBuilder.append("&" + URLEncoder.encode("dataType", UTF_8) + "=" + URLEncoder.encode("JSON", UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("base_date", UTF_8) + "=" + URLEncoder.encode(CURRENT_DATE, UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("base_time", UTF_8) + "=" + URLEncoder.encode(BASE_TIME, UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("nx", UTF_8) + "=" + URLEncoder.encode(NX, UTF_8));
        urlBuilder.append("&" + URLEncoder.encode("ny", UTF_8) + "=" + URLEncoder.encode(NY, UTF_8));

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod(HttpMethod.GET.toString());
        conn.setRequestProperty("Content-type", MediaType.APPLICATION_JSON_VALUE);

        BufferedReader br;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            br = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            sb.append(line);
        }
        br.close();
        conn.disconnect();
        String data = sb.toString();

        Double currentTemp = null;
        Double currentRainFallStatus = null;
        JSONObject jObject = new JSONObject(data);
        JSONObject response = jObject.getJSONObject("response");
        JSONObject body = response.getJSONObject("body");
        JSONObject items = body.getJSONObject("items");
        JSONArray jArray = items.getJSONArray("item");

        for (int i = 0; i < jArray.length(); i++) {
            JSONObject obj = jArray.getJSONObject(i);
            String category = obj.getString("category");
            double obsrValue = obj.getDouble("obsrValue");

            switch (category) {
                case "T1H":
                    currentTemp = obsrValue;
                    break;
                case "PTY":
                    currentRainFallStatus = obsrValue;
            }
        }

        return new Weather(currentTemp, currentRainFallStatus);
    }

    public String getCurrentWeatherStatus() throws IOException {
        Weather weather = checkWeather();
        return weather.currentStatus();
    }

}
