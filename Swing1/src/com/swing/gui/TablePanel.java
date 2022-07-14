package com.swing.gui;

import com.swing.model.Person;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;


public class TablePanel extends JPanel {

    private JTable table;

    private PersonTableModel personTableModel;

    private JPopupMenu popupMenu;

    private PersonTableListener personTableListener;

    public TablePanel() {
        personTableModel = new PersonTableModel();
        table = new JTable(personTableModel);
        popupMenu = new JPopupMenu();

        JMenuItem removeItem = new JMenuItem("delete row");
        popupMenu.add(removeItem);

        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int row = table.rowAtPoint(e.getPoint());
                table.getSelectionModel().setSelectionInterval(row, row);
                if (e.getButton() == MouseEvent.BUTTON3) {
                    popupMenu.show(table, e.getX(), e.getY());
                }
            }
        });

        removeItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = table.getSelectedRow();
                if (personTableListener != null) {
                    personTableListener.rowDeleted(row);
                    personTableModel.fireTableRowsDeleted(row, row);
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

    public void setPersonTableListener(PersonTableListener personTableListener) {
        this.personTableListener = personTableListener;
    }

}
