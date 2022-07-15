package com.swing.model;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Database {

    private static final String MYSQL_CLASS_NAME = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER_NOT_FOUND_ERROR_MSG = "Driver not found";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/swingtest";
    private static final String DATABASE_USER = "root";
    private static final String DATABASE_PASSWORD = "admin";
    private static final String CLOSE_CONNECTION_ERROR_MSG = "Can't close connection to db";
    private static final String CONNECTION_SUCCESS = "Successfully connected to database";
    private static final String DISCONNECTION_SUCCESS = "Successfully disconnected from database";

    private List<Person> people;

    private Connection connection;

    public Database() {
        people = new LinkedList<>();
    }

    public List<Person> getPeople() {
        return Collections.unmodifiableList(people);
    }

    public void addPerson(Person person) {
        people.add(person);
    }

    public void saveToFile(File file) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        Person[] persons = people.toArray(Person[]::new);
        objectOutputStream.writeObject(persons);
        objectOutputStream.close();
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Person[] persons = (Person[]) objectInputStream.readObject();
        people.clear();
        people.addAll(Arrays.asList(persons));
        objectInputStream.close();
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Database{");
        sb.append("people=").append(people);
        sb.append('}');
        return sb.toString();
    }

    public void removePerson(int index) {
        people.remove(index);
    }

    public void connect() throws Exception {
        if (connection != null) return;
        try {
            Class.forName(MYSQL_CLASS_NAME);
        } catch (ClassNotFoundException e) {
            throw new Exception(DRIVER_NOT_FOUND_ERROR_MSG);
        }
        connection = DriverManager.getConnection(CONNECTION_URL,DATABASE_USER, DATABASE_PASSWORD);
        System.out.println(CONNECTION_SUCCESS);
    }

    public void disconnect() throws Exception {
        if (connection != null) {
            try {
                connection.close();
                System.out.println(DISCONNECTION_SUCCESS);
            } catch (SQLException e) {
                throw new Exception(CLOSE_CONNECTION_ERROR_MSG);
            }
        }
    }
}
