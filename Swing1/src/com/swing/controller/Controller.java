package com.swing.controller;

import com.swing.gui.FormEvent;
import com.swing.model.*;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class Controller {

    private final Database database = new Database();

    public void configure(int port, String user, String password) throws Exception {
        database.configure(port, user, password);
    }

    public void connect() throws Exception {
        database.connect();
    }

    public void disconnect() throws Exception {
        database.disconnect();
    }

    public void addPerson(FormEvent formEvent) {
        Person person = new Person(
                formEvent.getName(),
                formEvent.getOccupation(),
                AgeCategory.getValueOf(formEvent.getAgeCategoryId()),
                EmploymentCategory.getValueOf(formEvent.getEmploymentCategory()),
                formEvent.getTaxId(),
                formEvent.isUsCitizen(),
                Gender.valueOf(formEvent.getGender().toUpperCase())
        );
        database.addPerson(person);
    }

    public List<Person> getPeople() {
        return database.getPeople();
    }

    public void saveToFile(File file) throws IOException {
        database.saveToFile(file);
    }

    public void loadFromFile(File file) throws IOException, ClassNotFoundException {
        database.loadFromFile(file);
    }

    public void removePerson(int index) {
        database.removePerson(index);
    }

    public void saveToDatabase() throws SQLException {
        database.save();
    }
    public void disconnectFromDatabase() throws Exception {
        database.disconnect();
    }

    public void connectToDatabase() throws Exception {
        database.connect();
    }

    public void loadDataFromDatabase() throws SQLException {
        database.load();
    }

}
