import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Toolbar extends JPanel implements ActionListener {

    private final JButton helloBtn;
    private final JButton goodByeBtn;
    private TextListener textListener;

    public Toolbar() {
        helloBtn = new JButton("Hello");
        goodByeBtn = new JButton("Good Bye");
        helloBtn.addActionListener(this);
        goodByeBtn.addActionListener(this);
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloBtn);
        add(goodByeBtn);
    }

    public void setTextListener(TextListener listener) {
        this.textListener = listener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JButton clicked = (JButton) e.getSource();
        if (clicked == helloBtn) {
            if (textListener != null) {
                textListener.textEmitted("Hello\n");
            }
        } else if (clicked == goodByeBtn) {
            if (textListener != null) {
                textListener.textEmitted("Good Bye\n");
            }
        }
    }
}
