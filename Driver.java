import javax.swing.JFrame;
/**
 * Driver class
 */

public class Driver {
	
	private JFrame frame;

	public Driver() {
		frame = new JFrame("Java-based Mini Twitter");
		frame.getContentPane().add(AdminControlPanel.getInstance(frame));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(600, 500);

	}

	public void show() {
		frame.setVisible(true);
	}

	public static void main(String[] args) {
		new Driver().show();
	}
}
