package Test;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JTextField;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/*
 * This is the starting window where users can create new accounts and enter the main VennDiagram Application
 */
public class LoginWindow extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame frame;
	private JTextField txtLogin;
	private JTextField textField;
	private JPasswordField passwordField;
	private JButton btnDeleteUser;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 814, 565);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// VennDiagramLogin Label
		txtLogin = new JTextField();
		txtLogin.setBackground(Color.LIGHT_GRAY);
		txtLogin.setText("       VennDiagramLogin");
		txtLogin.setEditable(false);
		txtLogin.setFont(new Font("Calibri", Font.BOLD, 35));
		txtLogin.setBounds(184, 34, 388, 94);
		frame.getContentPane().add(txtLogin);
		txtLogin.setColumns(10);

		// Username Label
		JLabel lblUsername = new JLabel("Username:");
		lblUsername.setFont(new Font("Calibri", Font.BOLD, 25));
		lblUsername.setBounds(84, 206, 139, 44);
		frame.getContentPane().add(lblUsername);

		// Password Label
		JLabel lblPassword = new JLabel("Password:");
		lblPassword.setFont(new Font("Calibri", Font.BOLD, 25));
		lblPassword.setBounds(84, 312, 139, 44);
		frame.getContentPane().add(lblPassword);

		// Username TextField
		textField = new JTextField();
		textField.setBounds(206, 207, 335, 44);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		// Password TextField
		passwordField = new JPasswordField();
		passwordField.setBounds(206, 313, 335, 44);
		frame.getContentPane().add(passwordField);
		passwordField.setEchoChar('*');

		// Login Button
		JButton btnLogin = new JButton("Login");
		btnLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * Login Button Function:
				 * 
				 */
				try {
					if (textField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()
							|| textField.getText().trim().length() != textField.getText().length()
							|| String.valueOf(passwordField.getPassword()).trim().length() != String
									.valueOf(passwordField.getPassword()).length()) {

						throw new Exception();
					}
					Connection connection = DriverManager.getConnection(AccessToLogin.getJdbcURL(),
							AccessToLogin.getUsername(), AccessToLogin.getPassword());

					String sql = "SELECT * FROM user WHERE Username='" + textField.getText() + "'";
					java.sql.Statement statement = connection.createStatement();
					ResultSet result = null;
					result = statement.executeQuery(sql);
					if (result.next() == false) {
						throw new Exception();
					}
					sql = "SELECT * FROM user WHERE Password='" + String.valueOf(passwordField.getPassword()) + "'";
					java.sql.Statement statement1 = connection.createStatement();
					result = null;
					result = statement1.executeQuery(sql);
					if (result.next() == false) {
						throw new Exception();
					} else {
						VennDiagramWindow newFrame = new VennDiagramWindow();
						newFrame.pack();
						newFrame.setLocationRelativeTo(null);
						newFrame.setVisible(true);
						frame.setVisible(false);
						JOptionPane.showMessageDialog(null, "Welcome!");
					}

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Incorrect");
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		btnLogin.setFont(new Font("Calibri", Font.BOLD, 22));
		btnLogin.setBounds(554, 408, 139, 51);
		frame.getContentPane().add(btnLogin);

		// New User Button
		JButton btnNewUser = new JButton("New User?\r\n");
		btnNewUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * New User Function:
				 * 
				 */

				try {
					if (textField.getText().isEmpty() || String.valueOf(passwordField.getPassword()).isEmpty()
							|| textField.getText().trim().length() != textField.getText().length()
							|| String.valueOf(passwordField.getPassword()).trim().length() != String
									.valueOf(passwordField.getPassword()).length()) {
						throw new Exception();
					}
					Connection connection = DriverManager.getConnection(AccessToLogin.getJdbcURL(),
							AccessToLogin.getUsername(), AccessToLogin.getPassword());
					String sql = "INSERT INTO user (Username, Password)" + " VALUES ( '" + textField.getText() + "', '"
							+ String.valueOf(passwordField.getPassword()) + "')";
					java.sql.Statement statement = connection.createStatement();
					statement.executeUpdate(sql);
					JOptionPane.showMessageDialog(null, "New User: " + textField.getText() + " has been created");
					textField.setText("");
					passwordField.setText("");

				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Please enter a valid entry");
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		btnNewUser.setFont(new Font("Calibri", Font.BOLD, 22));
		btnNewUser.setBounds(327, 408, 139, 51);
		frame.getContentPane().add(btnNewUser);

		// Delete User Button
		btnDeleteUser = new JButton("Delete User?");
		btnDeleteUser.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				/*
				 * Delete User Function:
				 *
				 * 
				 */
				try {

					Connection connection = DriverManager.getConnection(AccessToLogin.getJdbcURL(),
							AccessToLogin.getUsername(), AccessToLogin.getPassword());

					String sql = "SELECT * FROM user WHERE Username='" + textField.getText() + "'";
					java.sql.Statement statement = connection.createStatement();
					ResultSet result = null;
					result = statement.executeQuery(sql);
					if (result.next() == false) {
						throw new Exception();
					} else {
						String sql1 = "DELETE FROM user WHERE Username = '" + textField.getText() + "'";
						java.sql.Statement statement1 = connection.createStatement();
						statement1.executeUpdate(sql1);
						JOptionPane.showMessageDialog(null, "User: " + textField.getText() + " has been deleted");
						textField.setText("");
						passwordField.setText("");
					}
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Username Does Not Exist");
					textField.setText("");
					passwordField.setText("");
				}
			}
		});
		btnDeleteUser.setFont(new Font("Calibri", Font.BOLD, 23));
		btnDeleteUser.setBounds(84, 407, 169, 51);
		frame.getContentPane().add(btnDeleteUser);

	}
}
