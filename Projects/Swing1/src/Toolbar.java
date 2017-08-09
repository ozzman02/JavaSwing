import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Toolbar extends JPanel implements ActionListener {
	
	private JButton helloButton;
	private JButton goodbyeButton;
	private TextPanel textPanel;
	
	public Toolbar() {
		
		helloButton = new JButton("Hello");
		goodbyeButton = new JButton("Good Bye");
		
		helloButton.addActionListener(this);
		goodbyeButton.addActionListener(this);
		
		setLayout(new FlowLayout(FlowLayout.LEFT));
		
		add(helloButton);
		add(goodbyeButton);
	}
	
	public void setTextPanel(TextPanel textPanel) {
		this.textPanel = textPanel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		JButton clicked = (JButton) e.getSource();
		
		if (clicked == helloButton) {
			textPanel.appendText("Hello\n");
		} else if (clicked == goodbyeButton) {
			textPanel.appendText("Good Bye\n");
		}
		
	}
	
}
