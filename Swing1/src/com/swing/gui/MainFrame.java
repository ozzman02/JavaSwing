package com.swing.gui;

import com.swing.controller.Controller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.prefs.Preferences;

import static java.awt.event.ActionEvent.CTRL_MASK;

public class MainFrame extends JFrame {

    private static final String EXIT_CONFIRMATION_MESSAGE = "Do you really want to exit the application?";
    private static final String EXIT_CONFIRMATION_TITLE = "Confirm Exit";
    private static final String IMPORT_ERROR_MESSAGE = "Unable to load data from file";
    private static final String IMPORT_ERROR_MESSAGE_TITLE = "Import error";
    private static final String EXPORT_ERROR_MESSAGE = "Unable to save data to file";
    private static final String EXPORT_ERROR_MESSAGE_TITLE = "Export error";
    private static final String USER_PREFERENCE_KEY = "user";
    private static final String PASSWORD_PREFERENCE_KEY = "password";
    private static final String PORT_PREFERENCE_KEY = "port";
    private static final int PORT_PREFERENCE_DEFAULT_VALUE = 3306;

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;
    private JFileChooser fileChooser;
    private Controller controller;
    private TablePanel tablePanel;
    private PrefsDialog prefsDialog;
    private Preferences preferences;

    public MainFrame() {
        super("Hello World");
        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();
        controller = new Controller();
        tablePanel = new TablePanel();
        prefsDialog = new PrefsDialog(this);
        preferences = Preferences.userRoot().node("db");

        tablePanel.setData(controller.getPeople());

        tablePanel.setPersonTableListener(new PersonTableListener() {
            public void rowDeleted(int row) {
                controller.removePerson(row);
            }
        });

        prefsDialog.setPrefsListener(new PrefsListener() {
            @Override
            public void preferencesSet(String user, String password, int port) {
                preferences.put(USER_PREFERENCE_KEY, user);
                preferences.put(PASSWORD_PREFERENCE_KEY, password);
                preferences.putInt(PORT_PREFERENCE_KEY, port);
            }
        });

        prefsDialog.setDefaults(
                preferences.get(USER_PREFERENCE_KEY, ""),
                preferences.get(PASSWORD_PREFERENCE_KEY, ""),
                preferences.getInt(PORT_PREFERENCE_KEY, PORT_PREFERENCE_DEFAULT_VALUE)
        );

        fileChooser = new JFileChooser();
        fileChooser.addChoosableFileFilter(new PersonFileFilter());

        setJMenuBar(createMenuBar());

        toolbar.setTextListener(new TextListener() {
            @Override
            public void textEmitted(String text) {
                textPanel.appendText(text);
            }
        });

        formPanel.setFormEventListener(new FormEventListener() {
            @Override
            public void formEventOccurred(FormEvent formEvent) {
                controller.addPerson(formEvent);
                tablePanel.refresh();
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(tablePanel, BorderLayout.CENTER);


        setSize(600, 500);
        setMinimumSize(new Dimension(500, 400));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        JMenuItem exportDataItem = new JMenuItem("Export Data...");
        JMenuItem importDataItem = new JMenuItem("Import Data...");
        JMenuItem exitItem = new JMenuItem("Exit");

        fileMenu.add(exportDataItem);
        fileMenu.add(importDataItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        JMenu windowMenu = new JMenu("Window");
        JMenu showMenu = new JMenu("Show");
        JMenuItem prefsItem = new JMenuItem("Preferences...");
        JCheckBoxMenuItem showFormItem = new JCheckBoxMenuItem("Person Form");
        showFormItem.setSelected(true);
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);
        windowMenu.add(prefsItem);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        prefsItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prefsDialog.setVisible(true);
            }
        });

        showFormItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JCheckBoxMenuItem menuItem = (JCheckBoxMenuItem) e.getSource();
                formPanel.setVisible(menuItem.isSelected());
            }
        });

        fileMenu.setMnemonic(KeyEvent.VK_F);
        exitItem.setMnemonic(KeyEvent.VK_X);
        exitItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, CTRL_MASK));

        importDataItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_I, CTRL_MASK));

        exportDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showSaveDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.saveToFile(fileChooser.getSelectedFile());
                    } catch (IOException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, EXPORT_ERROR_MESSAGE,
                                EXPORT_ERROR_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        importDataItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (fileChooser.showOpenDialog(MainFrame.this) == JFileChooser.APPROVE_OPTION) {
                    try {
                        controller.loadFromFile(fileChooser.getSelectedFile());
                        tablePanel.refresh();
                    } catch (IOException | ClassNotFoundException ex) {
                        JOptionPane.showMessageDialog(MainFrame.this, IMPORT_ERROR_MESSAGE,
                                IMPORT_ERROR_MESSAGE_TITLE, JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int action = JOptionPane.showConfirmDialog(MainFrame.this, EXIT_CONFIRMATION_MESSAGE,
                        EXIT_CONFIRMATION_TITLE , JOptionPane.OK_CANCEL_OPTION);
                if (action == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });

        return menuBar;
    }

}
