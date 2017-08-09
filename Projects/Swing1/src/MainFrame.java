import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	
	private Toolbar toolBar;
	
	public MainFrame() {
		
		super("Hello World");
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolBar = new Toolbar();
		toolBar.setTextPanel(textPanel);
		
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
