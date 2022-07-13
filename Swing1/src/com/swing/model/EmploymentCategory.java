package com.swing.model;

public enum EmploymentCategory {
    EMPLOYED,
    SELF_EMPLOYED,
    UNEMPLOYED,
    OTHER;

    public static EmploymentCategory getValueOf(String value) {
        EmploymentCategory employmentCategory;
        switch (value) {
            case "employed":
                employmentCategory = EmploymentCategory.EMPLOYED;
                break;
            case "self-employed":
                employmentCategory = EmploymentCategory.SELF_EMPLOYED;
                break;
            case "unemployed":
                employmentCategory = EmploymentCategory.UNEMPLOYED;
                break;
            default:
                employmentCategory = EmploymentCategory.OTHER;
        }
        return employmentCategory;
    }

}

