package com.swing.model;

import java.io.*;
import java.sql.*;
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
    private static final String COUNT_PERSONS_BY_ID_SQL_STMT = "SELECT COUNT(*) AS COUNT FROM PEOPLE WHERE ID = ?";
    private static final String INSERT_PERSON_SQL_STMT =
            "INSERT INTO PEOPLE (id, name, occupation, age, employment_status, tax_id, us_citizen, gender) " +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

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

    public void save() throws SQLException {
        PreparedStatement countPersonsByIdStmt =  connection.prepareStatement(COUNT_PERSONS_BY_ID_SQL_STMT);
        PreparedStatement insertPersonStmt = connection.prepareStatement(INSERT_PERSON_SQL_STMT);
        for (Person person : people) {
            countPersonsByIdStmt.setInt(1, person.getId());
            ResultSet resultSet = countPersonsByIdStmt.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            if (count == 0) {
                System.out.println("Inserting person with ID " + person.getId());
                insertPersonStmt.setInt(1, person.getId());
                insertPersonStmt.setString(2, person.getName());
                insertPersonStmt.setString(3, person.getOccupation());
                insertPersonStmt.setString(4, person.getAgeCategory().name());
                insertPersonStmt.setString(5, person.getEmploymentCategory().name());
                insertPersonStmt.setString(6, person.getTaxId());
                insertPersonStmt.setBoolean(7, person.isUsCitizen());
                insertPersonStmt.setString(8, person.getGender().name());
                insertPersonStmt.executeUpdate();
            } else {
                System.out.println("Updating person with ID " + person.getId());
            }
        }
        insertPersonStmt.close();
        countPersonsByIdStmt.close();
    }
}
