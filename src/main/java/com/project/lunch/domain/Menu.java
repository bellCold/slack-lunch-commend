package com.project.lunch.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public enum Menu {
    // todo 지저분하고 이상함 정리하기
    KOREAN(List.of("라면", "부대찌개", "비빔밥", "김밥"), "clear"),
    JAPANESE(List.of("초밥", "우동", "돈까스"), "clear"),
    WESTERN(List.of("햄버거", "샌드위치", "스파게티"), "clear"),
    CHINESE(List.of("짜장면", "짬뽕", "볶음밥", "마라탕"), "clear"),

    RAIN(List.of("전", "오뎅탕"), "rain"),
    SNOW(List.of("붕어빵", "오뎅탕"), "snow");

    private final List<String> foods;
    private final String weatherStatus;

    Menu(List<String> foods, String weatherStatus) {
        this.foods = foods;
        this.weatherStatus = weatherStatus;
    }

    private static final Random RANDOM = new Random();

    public static String recommendMenu(String weatherStatus) {
        List<Menu> foods = Arrays.stream(Menu.values()).filter(value -> value.weatherStatus.equals(weatherStatus)).collect(Collectors.toList());
        Menu menu = foods.get(RANDOM.nextInt(foods.size()));
        return menu.foods.get(RANDOM.nextInt(menu.foods.size()));
    }
}