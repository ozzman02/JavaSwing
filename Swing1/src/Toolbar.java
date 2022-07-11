import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private JButton helloBtn;
    private JButton goodByeBtn;
    private TextPanel textPanel;

    public Toolbar() {
        helloBtn = new JButton("Hello");
        goodByeBtn = new JButton("Good Bye");
        helloBtn.addActionListener(this);
        goodByeBtn.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloBtn);
        add(goodByeBtn);
    }

    public void setTextPanel(TextPanel textPanel) {
        this.textPanel = textPanel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == helloBtn) {
            textPanel.appendText("Hello\n");
        } else if (clicked == goodByeBtn) {
            textPanel.appendText("Good Bye\n");
        }
    }
}
