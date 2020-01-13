import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import java.awt.Font;

public class Satistics extends JFrame{

	private JFrame frame;
	public JTextField textField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Satistics window = new Satistics();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Satistics() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setText("\r\n\r\n");
		textField.setFont(new Font("Calibri", Font.BOLD, 30));
		textField.setBounds(29, 24, 390, 159);
		getContentPane().add(textField);
		textField.setColumns(10);
	}
}
