package com.swing.gui;

import com.swing.model.EmploymentCategory;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;

public class EmploymentCategoryEditor extends AbstractCellEditor implements TableCellEditor {

    private JComboBox<EmploymentCategory> comboBox;

    public EmploymentCategoryEditor() {
        this.comboBox = new JComboBox<>(EmploymentCategory.values());
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        comboBox.setSelectedItem(value);
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fireEditingStopped();
            }
        });
        return comboBox;
    }

    @Override
    public Object getCellEditorValue() {
        return comboBox.getSelectedItem();
    }

    @Override
    public boolean isCellEditable(EventObject e) {
        return true;
    }

}
