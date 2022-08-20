package com.swing.gui;

import javax.swing.*;
import java.awt.*;

public class ProgressDialog extends JDialog {

    private JButton cancelButton;
    private JProgressBar progressBar;

    public ProgressDialog(Window parent) {
        super(parent, "Message Downloading...", ModalityType.APPLICATION_MODAL);
        cancelButton = new JButton("Cancel");
        progressBar = new JProgressBar();
        setLayout(new FlowLayout());
        Dimension size = cancelButton.getPreferredSize();
        size.width = 400;
        progressBar.setPreferredSize(size);
        add(progressBar);
        add(cancelButton);
        pack();
        setLocationRelativeTo(parent);
    }

    @Override
    public void setVisible(final boolean visible) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                if (visible == false) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    progressBar.setValue(0);
                }
                ProgressDialog.super.setVisible(visible);
            }
        });
    }

    public void setMaximum(int value) {
        progressBar.setMaximum(value);
    }

    public void setValue(int value) {
        progressBar.setValue(value);
    }
}
