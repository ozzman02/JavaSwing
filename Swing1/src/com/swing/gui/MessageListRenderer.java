package com.swing.gui;

import com.swing.model.Message;

import javax.swing.*;
import java.awt.*;

import static com.swing.commons.Constants.MESSAGE_LIST_RENDERER_LABEL_ICON;

/*
    This demonstrates using an arbitrary component as a list box renderer.
    (Probably overkill in this case to use JPanel when JLabel could be used directly)
 */
public class MessageListRenderer implements ListCellRenderer {

    private JPanel panel;

    private JLabel label;

    private Color selectedColor;

    private Color normalColor;

    public MessageListRenderer() {
        panel = new JPanel();
        label = new JLabel();
        label.setFont(Utils.createFont("/CrimewaveBB.ttf").deriveFont(Font.PLAIN, 20));
        selectedColor = new Color(210, 210, 255);
        normalColor = Color.WHITE;
        label.setIcon(Utils.createIcon(MESSAGE_LIST_RENDERER_LABEL_ICON));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        panel.add(label);
    }

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
        Message message = (Message) value;
        label.setText(message.getTitle());
        panel.setBackground(cellHasFocus ? selectedColor : normalColor);
        return panel;
    }

}
