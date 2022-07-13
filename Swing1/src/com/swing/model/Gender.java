package com.swing.model;

public enum Gender {
    MALE, FEMALE;

    public static Gender getValueOf(String value) {
        Gender gender = null;
        switch (value) {
            case "male":
                gender = Gender.MALE;
                break;
            case "female":
                gender = Gender.FEMALE;
                break;
        }
        return gender;
    }
}
