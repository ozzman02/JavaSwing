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
        progressBar.setStringPainted(true);
        progressBar.setString("Retrieving messages ...");
        progressBar.setMaximum(10);
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
                if (!visible) {
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
        int progress = 100*value/progressBar.getMaximum();
        progressBar.setString(String.format("%d%% complete", progress));
        progressBar.setValue(value);
    }
}
