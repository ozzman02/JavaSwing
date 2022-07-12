import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormPanel extends JPanel {

    private JLabel nameLabel;
    private JLabel occupationLabel;
    private JLabel ageLabel;
    private JLabel empComboBoxLabel;
    private JTextField nameField;
    private JTextField occupationField;
    private JButton okBtn;
    private FormEventListener formEventListener;
    private JList<AgeCategory> ageList;
    private JComboBox<String> empComboBox;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        ageLabel = new JLabel("Age: ");
        empComboBoxLabel = new JLabel("Employment: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("OK");
        ageList = new JList<>();
        empComboBox = new JComboBox<>();

        DefaultListModel<AgeCategory> ageModel = new DefaultListModel<>();
        ageModel.addElement(new AgeCategory(0, "Under 18"));
        ageModel.addElement(new AgeCategory(1, "18 to 65"));
        ageModel.addElement(new AgeCategory(2, "65 or over"));
        ageList.setModel(ageModel);
        ageList.setSelectedIndex(1);
        ageList.setPreferredSize(new Dimension(110, 70));
        ageList.setBorder(BorderFactory.createEtchedBorder());

        DefaultComboBoxModel<String> empModel = new DefaultComboBoxModel<>();
        empModel.addElement("employed");
        empModel.addElement("self-employed");
        empModel.addElement("unemployed");
        empComboBox.setModel(empModel);
        empComboBox.setSelectedIndex(0);
        empComboBox.setEditable(true);

        okBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String occupation = occupationField.getText();
                AgeCategory ageCategory = ageList.getSelectedValue();
                String employmentCategory = (String) empComboBox.getSelectedItem();
                FormEvent formEvent = new FormEvent(this, name, occupation, ageCategory.getId(),
                        employmentCategory);
                if (formEventListener != null) {
                    formEventListener.formEventOccurred(formEvent);
                }
            }
        });

        Border innerBorder = BorderFactory.createTitledBorder("Add Person");
        Border outerBorder = BorderFactory.createEmptyBorder(5,5,5,5);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        layoutComponents();
    }

    public void setFormEventListener(FormEventListener formEventListener) {
        this.formEventListener = formEventListener;
    }

    public void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();

        /* First Row */
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;

        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(nameLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(nameField, gc);

        /* Second Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(occupationLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(occupationField, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(ageLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(ageList, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(empComboBoxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(empComboBox, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(okBtn, gc);
    }

}
