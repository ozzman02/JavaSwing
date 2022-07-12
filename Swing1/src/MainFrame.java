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
                textPanel.appendText(name + ": " + occupation + ": " + ageCategoryId + ": "
                        + employmentCategory + ": " + taxId + ": " + usCitizen + "\n");
            }
        });

        add(formPanel, BorderLayout.WEST);
        add(toolbar, BorderLayout.NORTH);
        add(textPanel, BorderLayout.CENTER);

        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

}
