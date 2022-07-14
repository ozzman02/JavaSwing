package com.swing.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrefsDialog extends JDialog {

    private JButton okButton;
    private JButton cancelButton;
    private JSpinner portSpinner;
    private SpinnerNumberModel spinnerNumberModel;
    private JTextField userField;
    private JPasswordField passwordField;
    private PrefsListener prefsListener;

    public PrefsDialog(JFrame parent) {
        super(parent,"Preferences", false);

        okButton = new JButton("OK");
        cancelButton = new JButton("Cancel");
        spinnerNumberModel = new SpinnerNumberModel(3306, 0, 9999, 1);
        portSpinner = new JSpinner(spinnerNumberModel);
        userField = new JTextField(10);
        passwordField = new JPasswordField(10);
        passwordField.setEchoChar('*');

        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        gc.gridy = 0;

        /* First Row */
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        add(new JLabel("User: "), gc);
        gc.gridx++;
        add(userField, gc);

        /* Next Row */
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        add(new JLabel("Password: "), gc);
        gc.gridx++;
        add(passwordField, gc);

        /* Next Row */
        gc.gridy++;
        gc.weightx = 1;
        gc.weighty = 1;
        gc.fill = GridBagConstraints.NONE;

        gc.gridx = 0;
        add(new JLabel("Port: "), gc);
        gc.gridx++;
        add(portSpinner, gc);

        /* Next Row */
        gc.gridy++;
        gc.gridx = 0;
        add(okButton, gc);

        gc.gridx++;
        add(cancelButton, gc);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (prefsListener != null) {
                    prefsListener.preferencesSet(
                            userField.getText(),
                            new String(passwordField.getPassword()),
                            (Integer) portSpinner.getValue()
                    );
                }
                setVisible(false);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(400, 300);
        setLocationRelativeTo(parent);
    }

    public void setPrefsListener(PrefsListener prefsListener) {
        this.prefsListener = prefsListener;
    }

    public void setDefaults(String user, String password, int port) {
        userField.setText(user);
        passwordField.setText(password);
        portSpinner.setValue(port);
    }

}
