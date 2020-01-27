package Test;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Canvas;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * This Window is only opened when the User Correctly enters their user details
 */
public class VennDiagramWindow extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField textField;
	private JButton button_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VennDiagramWindow window = new VennDiagramWindow();
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
	public VennDiagramWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		// Initialize JFrame WIndow
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.LIGHT_GRAY);
		frame.setBounds(100, 100, 1328, 752);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		// Input Text Field
		textField = new JTextField();
		textField.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		textField.setBounds(245, 51, 635, 57);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setEditable(true);

		// Input Text Field Enter Button
		JButton btnNewButton = new JButton("\r\n\r\nEnter");
		btnNewButton.setBounds(879, 51, 135, 57);
		frame.getContentPane().add(btnNewButton);
		btnNewButton.setBackground(Color.GRAY);
		btnNewButton.setFont(new Font("Calibri", Font.BOLD, 30));

		//Shows all Information In Circle 1
		JButton button = new JButton("...\r\n");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
				
                    
				} catch (Exception e1) {
					// Handle Exception
				}
			}
		});
		button.setBounds(377, 599, 76, 28);
		frame.getContentPane().add(button);
		button.setForeground(SystemColor.controlText);
		button.setFont(new Font("Calibri", Font.BOLD, 35));
		button.setBackground(Color.LIGHT_GRAY);

		// Shows all Information In Circle 2
		button_1 = new JButton("...\r\n");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {

				} catch (Exception e1) {
					// Handle Exception
				}
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(new Font("Calibri", Font.BOLD, 35));
		button_1.setBackground(Color.LIGHT_GRAY);
		button_1.setBounds(804, 599, 76, 28);
		frame.getContentPane().add(button_1);

		// Circle Drawing
		Canvas canvas = new Drawing();
		canvas.setLocation(188, 142);
		canvas.setBackground(Color.LIGHT_GRAY);
		canvas.setSize(921, 563);
		frame.getContentPane().add(canvas);

		// Enter Button Action
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {

					// Testing For Invalid Statements
					/*
					 * Situations that will throw an invalid statement
					 * 1.Empty Text
					 * 2.Text Length Over 100
					 * 3.Word Length Over 20
					 * 4.If a word is not All CAPS, NO CAPS, or ONLY THE FIRST LETTER CAPS
					 */

					String testSpaces = textField.getText();
					if (textField.getText().length() > 100
							|| (testSpaces.replaceAll("", " ").length() == textField.getText().length()
									&& textField.getText().length() > 20)
							|| textField.getText().equals("")) {
						throw new Exception();
					}

					int wordLength = 0;
					int capCount = 0;
					boolean isFirst = false;
					ArrayList<Integer> lengths = new ArrayList<Integer>();
					for (int i = 0; i < textField.getText().length(); i++) {
						if (textField.getText().charAt(i) != ' ') {
							wordLength++;
						}

						if (Character.isUpperCase(textField.getText().charAt(i)) && wordLength == 1) {
							capCount++;
							isFirst = true;
							continue;
						}

						if (Character.isUpperCase(textField.getText().charAt(i))) {
							capCount++;
						}

						if (textField.getText().charAt(i) == ' ') {
							lengths.add(wordLength);
							if (capCount != wordLength
									&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
								throw new Exception();
							}
							capCount = 0;
							wordLength = 0;
						}
						if (i == textField.getText().length() - 1) {
							lengths.add(wordLength);
							if (capCount != wordLength
									&& ((capCount == 1 && isFirst == false) || (capCount != 1 && capCount != 0))) {
								throw new Exception();
							}
							capCount = 0;
							wordLength = 0;
						}
					}
					for (int i : lengths) {
						if (i > 20) {
							throw new Exception();
						}
					}

					// Input Valid Text File Into Drag and Drop TextBox
					
					/*
					 * This Function will be composed of 
					 * 1.
					 * 2.
					 * 3.
					 */

				} catch (Exception e1) {
					// Input is Invalid, Text Is Cleared
					JOptionPane.showMessageDialog(null, "Please enter a valid entry");
					textField.setText("");
				}
			}
		});

	}
}
