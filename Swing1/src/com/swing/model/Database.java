package com.swing.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
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
}
