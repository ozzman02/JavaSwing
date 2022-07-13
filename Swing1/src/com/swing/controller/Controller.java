package com.swing.controller;

import com.swing.gui.FormEvent;
import com.swing.model.*;

import java.util.List;

public class Controller {

    private final Database database = new Database();

    public void addPerson(FormEvent formEvent) {

        Person person = new Person(
                formEvent.getName(),
                formEvent.getOccupation(),
                AgeCategory.valueOf(formEvent.getAgeCategoryId()),
                EmploymentCategory.getValueOf(formEvent.getEmploymentCategory()),
                formEvent.getTaxId(),
                formEvent.isUsCitizen(),
                Gender.getValueOf(formEvent.getGender())
        );
        database.addPerson(person);
    }

    public List<Person> getPeople() {
        return database.getPeople();
    }

}
