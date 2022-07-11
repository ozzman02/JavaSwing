import javax.swing.*;
import java.awt.*;

public class Toolbar extends JPanel {

    private JButton helloBtn;
    private JButton goodByeBtn;

    public Toolbar() {
        helloBtn = new JButton("Hello");
        goodByeBtn = new JButton("Good Bye");
        setLayout(new FlowLayout(FlowLayout.LEFT));
        add(helloBtn);
        add(goodByeBtn);
    }

}
