package com.swing.gui;

import com.swing.model.Person;

import javax.swing.table.AbstractTableModel;
import java.util.List;

public class PersonTableModel extends AbstractTableModel {

    private List<Person> people;

    private String[] columnNames = { "ID","Name","Occupation", "Age Category",
            "Employment Category","Tax ID", "US Citizen", "Gender" };

    public PersonTableModel() {}

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 1:
            case 6:
                return true;
            default:
                return false;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        if (people == null) return;
        Person person = people.get(rowIndex);
        switch (columnIndex) {
            case 1:
                person.setName((String) aValue);
                break;
            case 6:
                person.setUsCitizen((Boolean) aValue);
                break;
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 7:
                return String.class;
            case 6:
                return Boolean.class;
            default:
                return null;
        }
    }

    public void setPeople(List<Person> people) {
        this.people = people;
    }

    @Override
    public int getRowCount() {
        return people.size();
    }

    @Override
    public int getColumnCount() {
        return Person.class.getDeclaredFields().length - 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Person person = people.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return person.getId();
            case 1:
                return person.getName();
            case 2:
                return person.getOccupation();
            case 3:
                return person.getAgeCategory();
            case 4:
                return person.getEmploymentCategory();
            case 5:
                return person.getTaxId();
            case 6:
                return person.isUsCitizen();
            case 7:
                return person.getGender();
        }
        return null;
    }

}
