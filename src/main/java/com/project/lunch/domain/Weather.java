package com.project.lunch.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weather {
    private Double temp;
    private Double rainFallStatus;

    // Todo 조금더 깔끔하게 다듬기
    public String currentStatus() {
        if (this.rainFallStatus == 1 || this.rainFallStatus == 2) {
            // 비
            return "rain";
        } else if (this.rainFallStatus >= 3) {
            // 눈
            return "snow";
        } else {
            // 맑음
            return "clear";
        }
    }
}
