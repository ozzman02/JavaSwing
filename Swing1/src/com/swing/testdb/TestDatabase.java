package com.swing.testdb;

import com.swing.model.*;

public class TestDatabase {

    public static void main(String[] args) {
        System.out.println("Running Database Test");
        Database db = new Database();
        db.addPerson(new Person("Joe", "builder", AgeCategory.ADULT,
                EmploymentCategory.EMPLOYED, "TAX-7777", true, Gender.MALE));
        db.addPerson(new Person("Sue", "artist", AgeCategory.SENIOR,
                EmploymentCategory.SELF_EMPLOYED, null, false, Gender.FEMALE));
        try {
            db.connect();
            db.save();
            db.load();
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
