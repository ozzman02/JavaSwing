import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

public class FormPanel extends JPanel {

    private final JLabel nameLabel;
    private final JLabel occupationLabel;
    private final JLabel ageLabel;
    private final JLabel empComboBoxLabel;
    private final JLabel citizenLabel;
    private final JLabel taxLabel;
    private final JLabel genderLabel;
    private final JTextField nameField;
    private final JTextField occupationField;
    private final JButton okBtn;
    private FormEventListener formEventListener;
    private final JList<AgeCategory> ageList;
    private final JComboBox<String> empComboBox;
    private final JCheckBox citizenCheck;
    private final JTextField taxField;
    private final JRadioButton maleRadio;
    private final JRadioButton femaleRadio;
    private final ButtonGroup genderGroup;

    public FormPanel() {
        Dimension dim = getPreferredSize();
        dim.width = 250;
        setPreferredSize(dim);

        nameLabel = new JLabel("Name: ");
        occupationLabel = new JLabel("Occupation: ");
        ageLabel = new JLabel("Age: ");
        empComboBoxLabel = new JLabel("Employment: ");
        taxLabel = new JLabel("Tax ID: ");
        citizenLabel = new JLabel("US Citizen: ");
        genderLabel = new JLabel("Gender: ");
        nameField = new JTextField(10);
        occupationField = new JTextField(10);
        okBtn = new JButton("OK");
        ageList = new JList<>();
        empComboBox = new JComboBox<>();
        citizenCheck = new JCheckBox();
        taxField = new JTextField(10);
        maleRadio = new JRadioButton("male");
        femaleRadio = new JRadioButton("female");
        genderGroup = new ButtonGroup();

        okBtn.setMnemonic(KeyEvent.VK_O);
        nameLabel.setDisplayedMnemonic(KeyEvent.VK_N);
        nameLabel.setLabelFor(nameField);

        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);

        maleRadio.setSelected(true);
        maleRadio.setActionCommand("male");
        femaleRadio.setActionCommand("female");

        taxLabel.setEnabled(false);
        taxField.setEnabled(false);

        citizenCheck.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean isTicked = citizenCheck.isSelected();
                taxLabel.setEnabled(isTicked);
                taxField.setEnabled(isTicked);
            }
        });

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
                String taxId = taxField.getText();
                boolean usCitizen = citizenCheck.isSelected();
                String gender = genderGroup.getSelection().getActionCommand();
                FormEvent formEvent = new FormEvent(this, name, occupation, ageCategory.getId(),
                        employmentCategory, taxId, usCitizen, gender);
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
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(citizenLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(citizenCheck, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(taxLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(taxField, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.05;

        gc.gridx = 0;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0,0,0,5);
        add(genderLabel, gc);

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(maleRadio, gc);

        /* Next Row */
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.2;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0,0,0,0);
        add(femaleRadio, gc);

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
