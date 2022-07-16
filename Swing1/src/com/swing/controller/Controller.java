package com.swing.controller;

import com.swing.gui.FormEvent;
import com.swing.model.*;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Controller {

    private final Database database = new Database();

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
}
