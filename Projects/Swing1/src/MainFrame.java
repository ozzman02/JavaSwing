import java.awt.BorderLayout;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private TextPanel textPanel;
	
	private Toolbar toolBar;
	
	private FormPanel formPanel;
	
	public MainFrame() {
		
		super("Hello World");
		
		setLayout(new BorderLayout());
		
		textPanel = new TextPanel();
		toolBar = new Toolbar();
		formPanel = new FormPanel();
		
		toolBar.setStringListener(new StringListener() {
			public void textEmitted(String text) {
				textPanel.appendText(text);
			}
		});
		
		formPanel.setFormListener(new FormListener() {
			public void formEventOccurred(FormEvent e) {
				String name = e.getName();
				String occupation = e.getOccupation();
				int ageCategory = e.getAgeCategory();
				String empCat = e.getEmpCat();
				textPanel.appendText(name + ": " + occupation + ": " + ageCategory + ": " + empCat + "\n");
			}
		});
		
		add(formPanel, BorderLayout.WEST);
		add(toolBar, BorderLayout.NORTH);
		add(textPanel, BorderLayout.CENTER);
		
		setSize(600, 500);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}
	
}
