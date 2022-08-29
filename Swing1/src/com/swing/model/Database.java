package com.swing.model;

import java.io.*;
import java.sql.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import static com.swing.commons.Constants.*;

public class Database {

    private List<Person> people;

    private Connection connection;

    private int port;

    private String user;

    private String password;

    public Database() {
        people = new LinkedList<>();
    }

    public void configure(int port, String user, String password) throws Exception {
        this.port = port;
        this.user = user;
        this.password = password;

        if (connection != null) {
            disconnect();
            connect();
        }

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
        PreparedStatement updatePersonStmt = connection.prepareStatement(UPDATE_PEOPLE_SQL_STMT);
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
                updatePersonStmt.setString(1, person.getName());
                updatePersonStmt.setString(2, person.getOccupation());
                updatePersonStmt.setString(3, AgeCategory.ADULT.name());
                updatePersonStmt.setString(4, EmploymentCategory.UNEMPLOYED.name());
                updatePersonStmt.setString(5, person.getTaxId());
                updatePersonStmt.setBoolean(6, person.isUsCitizen());
                updatePersonStmt.setString(7, person.getGender().name());
                updatePersonStmt.setInt(8, person.getId());
                updatePersonStmt.executeUpdate();
            }
        }
        insertPersonStmt.close();
        updatePersonStmt.close();
        countPersonsByIdStmt.close();
    }

    public void load() throws SQLException {
        people.clear();
        Statement selectStatement = connection.createStatement();
        ResultSet resultSet = selectStatement.executeQuery(SELECT_PEOPLE_STMT);
        while (resultSet.next()) {
            people.add(new Person(resultSet.getInt(COLUMN_ID),
                    resultSet.getString(COLUMN_NAME),
                    resultSet.getString(COLUMN_OCCUPATION),
                    AgeCategory.valueOf(resultSet.getString(COLUMN_AGE).toUpperCase()),
                    EmploymentCategory.valueOf(resultSet.getString(COLUMN_EMPLOYMENT_STATUS)),
                    resultSet.getString(COLUMN_TAX_ID),
                    resultSet.getBoolean(COLUMN_US_CITIZEN),
                    Gender.valueOf(resultSet.getString(COLUMN_GENDER).toUpperCase())
            ));
        }
        selectStatement.close();
        System.out.println("Displaying list of people in database");
        System.out.println(people);
    }

}
