package com.swing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton saveBtn;
    private final JButton refreshBtn;
    private ToolbarListener toolbarListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        saveBtn = new JButton("Save");
        refreshBtn = new JButton("Refresh");
        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(saveBtn);
        add(refreshBtn);
    }

    public void setToolbarListener(ToolbarListener listener) {
        this.toolbarListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == saveBtn) {
            if (toolbarListener != null) {
                toolbarListener.saveEventOccurred();
            }
        } else if (clicked == refreshBtn) {
            if (toolbarListener != null) {
                toolbarListener.refreshEventOccurred();
            }
        }
    }
}
