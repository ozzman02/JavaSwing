package com.swing.gui;

import com.swing.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class TablePanel extends JPanel {

    private JTable table;

    private PersonTableModel personTableModel;

    private JPopupMenu popupMenu;

    public TablePanel() {
        personTableModel = new PersonTableModel();
        table = new JTable(personTableModel);
        popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("delete row");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

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
