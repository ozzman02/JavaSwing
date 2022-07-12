import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private TextPanel textPanel;
    private Toolbar toolbar;
    private FormPanel formPanel;

    public MainFrame() {
        super("Hello World");
        setLayout(new BorderLayout());

        toolbar = new Toolbar();
        textPanel = new TextPanel();
        formPanel = new FormPanel();

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
                String name = formEvent.getName();
                String occupation = formEvent.getOccupation();
                int ageCategoryId = formEvent.getAgeCategoryId();
                String employmentCategory = formEvent.getEmploymentCategory();
                String taxId = formEvent.getTaxId();
                boolean usCitizen = formEvent.isUsCitizen();
                String gender = formEvent.getGender();
                textPanel.appendText(name + ": " + occupation + ": " + ageCategoryId + ": "
                        + employmentCategory + ": " + taxId + ": " + usCitizen +
                        ": " + gender + "\n");
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setSize(600, 500);
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
        JMenuItem showFormItem = new JMenuItem("Person Form");
        showMenu.add(showFormItem);
        windowMenu.add(showMenu);

        menuBar.add(fileMenu);
        menuBar.add(windowMenu);

        return menuBar;
    }

}
