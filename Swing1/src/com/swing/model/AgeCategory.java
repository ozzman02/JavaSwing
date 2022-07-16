package com.swing.model;

import java.util.HashMap;
import java.util.Map;

public enum AgeCategory {

    CHILD(0),
    ADULT(1),
    SENIOR(2);

    private final int value;

    private static final Map<Integer, AgeCategory> ageCategoryMap = new HashMap<>();

    static {
        for (AgeCategory ageCategory : AgeCategory.values()) {
            ageCategoryMap.put(ageCategory.value, ageCategory);
        }
    }

    AgeCategory(int value) {
        this.value = value;
    }

    public static AgeCategory getValueOf(int value) {
        return ageCategoryMap.get(value);
    }

}
