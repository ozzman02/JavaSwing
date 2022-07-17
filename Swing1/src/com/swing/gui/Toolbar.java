package com.swing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class Toolbar extends JPanel implements ActionListener {

    private static final String SAVE_BUTTON_PATH = "/Save16.gif";
    private static final String REFRESH_BUTTON_PATH = "/Refresh16.gif";

    private final JButton saveBtn;
    private final JButton refreshBtn;
    private ToolbarListener toolbarListener;

    public Toolbar() {
        setBorder(BorderFactory.createEtchedBorder());
        saveBtn = new JButton("Save");
        saveBtn.setIcon(createIcon(SAVE_BUTTON_PATH));
        refreshBtn = new JButton("Refresh");
        refreshBtn.setIcon(createIcon(REFRESH_BUTTON_PATH));
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

    private ImageIcon createIcon(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            System.err.println("Unable to load image: " + path);
        }
        return new ImageIcon(url);
    }

}
