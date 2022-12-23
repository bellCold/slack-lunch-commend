package com.project.lunch.domain;

import java.util.List;
import java.util.Random;

public enum Menu {
    KOREAN(List.of("라면", "부대찌개", "비빔밥", "김밥")),
    JAPANESE(List.of("초밥", "우동", "돈까스")),
    WESTERN(List.of("햄버거", "샌드위치", "스파게티")),
    CHINESE(List.of("짜장면", "짬뽕", "볶음밥", "마라탕"));

    private final List<String> foods;

    Menu(List<String> foods) {
        this.foods = foods;
    }

    private static final List<Menu> VALUES = List.of(values());
    private static final int SIZE = VALUES.size();
    private static final Random RANDOM = new Random();

    public static String recommendMenu() {
        List<String> foods = VALUES.get(RANDOM.nextInt(SIZE)).foods;
        return foods.get(RANDOM.nextInt(foods.size()));
    }
}
