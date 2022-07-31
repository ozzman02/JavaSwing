package com.swing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static com.swing.commons.Constants.*;
import static com.swing.gui.Utils.createIcon;

public class Toolbar extends JToolBar implements ActionListener {

    private final JButton saveBtn;
    private final JButton refreshBtn;
    private ToolbarListener toolbarListener;

    public Toolbar() {
        // Get rid of the border if you want the draggable toolbar
        setBorder(BorderFactory.createEtchedBorder());

        //setFloatable(false);

        saveBtn = new JButton();
        saveBtn.setIcon(createIcon(SAVE_ICON_BUTTON_PATH));
        saveBtn.setToolTipText(TOOLBAR_SAVE_BTN_TOOLTIP_TEXT);

        refreshBtn = new JButton();
        refreshBtn.setIcon(createIcon(REFRESH_ICON_BUTTON_PATH));
        refreshBtn.setToolTipText(TOOLBAR_REFRESH_BTN_TOOLTIP_TEXT);

        saveBtn.addActionListener(this);
        refreshBtn.addActionListener(this);

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(saveBtn);
        //addSeparator();
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
