package com.swing.database;

import com.swing.model.Database;

public class TestDatabase {
    public static void main(String[] args) {
        System.out.println("Running Database Test");
        Database db = new Database();
        try {
            db.connect();
            db.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
