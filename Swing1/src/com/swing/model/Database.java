package com.swing.model;

import java.util.ArrayList;
import java.util.List;

public class Database {

    private List<Person> people;

    public Database() {
        people = new ArrayList<>();
    }

    public List<Person> getPeople() {
        return people;
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    public void addPerson(Person person) {
        people.add(person);
    }

}
