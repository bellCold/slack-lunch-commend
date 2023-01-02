package com.project.lunch.api;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class SlackWeatherController {

    @Value(value = "secretKey")
    String serviceKey;

    @GetMapping("/weather")
    public void getVillageWeather() throws IOException {
        String apiUrl = "http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst";

        String pageNo = "1";
        String numOfRows = "1000";
        String dataType = "XML";
        String baseDate = LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE);
        String baseTime = LocalTime.now().format(DateTimeFormatter.ofPattern("HHmm"));
        String nx = "55";
        String ny = "127";

        String url = apiUrl
                + "?serviceKey=" + serviceKey
                + "&pageNo=" + pageNo
                + "&numOfRows=" + numOfRows
                + "&dataType=" + dataType
                + "&base_date=" + baseDate
                + "&base_time=" + baseTime
                + "&nx=" + nx
                + "&ny=" + ny;
        // https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getVilageFcst?serviceKey=xfErn0w%2BdVGtzl0gQHl43yFAak81NU9PRKrLlDuNtEs%2BsBttvK3ke%2BKSZ0W8JAFlfym0nsllylWfxvrmxcTz4w%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date=20230102&base_time=1953&nx=55&ny=127
        // https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=xfErn0w%2BdVGtzl0gQHl43yFAak81NU9PRKrLlDuNtEs%2BsBttvK3ke%2BKSZ0W8JAFlfym0nsllylWfxvrmxcTz4w%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date=20230102&base_time=0600&nx=55&ny=127
        // https://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst?serviceKey=xfErn0w%2BdVGtzl0gQHl43yFAak81NU9PRKrLlDuNtEs%2BsBttvK3ke%2BKSZ0W8JAFlfym0nsllylWfxvrmxcTz4w%3D%3D&pageNo=1&numOfRows=1000&dataType=XML&base_date=20230102&base_time=0600&nx=55&ny=127

        System.out.println(url);


        String result = getStringFromURL(url);
        System.out.println("result = " + result);
    }

    public String getStringFromURL(String url) throws IOException {
        URL apiURL = new URL(url);

        HttpURLConnection conn = (HttpURLConnection) apiURL.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
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
