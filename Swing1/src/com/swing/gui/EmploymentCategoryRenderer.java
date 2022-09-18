package com.swing.gui;

import com.swing.model.EmploymentCategory;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class EmploymentCategoryRenderer implements TableCellRenderer {

    private JComboBox<EmploymentCategory> comboBox;

    public EmploymentCategoryRenderer() {
        comboBox = new JComboBox<>(EmploymentCategory.values());
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
            boolean hasFocus, int row, int column) {
        comboBox.setSelectedItem(value);
        return comboBox;
    }

}
