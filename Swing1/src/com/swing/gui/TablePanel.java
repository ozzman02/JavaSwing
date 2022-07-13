package com.swing.gui;

import com.swing.model.Person;

import javax.swing.*;
import java.awt.*;
import java.util.List;


public class TablePanel extends JPanel {

    private JTable table;

    private PersonTableModel personTableModel;

    public TablePanel() {
        personTableModel = new PersonTableModel();
        table = new JTable(personTableModel);
        setLayout(new BorderLayout());
        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(List<Person> people) {
        personTableModel.setPeople(people);
    }

    public void refresh() {
        personTableModel.fireTableDataChanged();
    }
}
