package GUI;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import DB.CommInterface;
import DB.ConnectionManager;
import DB.Util;

public class LoginDialog extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField usernameField;
	private JTextField passwordField;

	/**
	 * Create the dialog.
	 */
	public LoginDialog(Frame parent) {
		super(parent, "Login", true);
		Point parentLoc = parent.getLocation();
		
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent arg0) {
				ConnectionManager.Disconnect();
				parent.dispose();
				dispose();
			}
		});
		setBounds(100, 100, 450, 300);
		setLocation(parentLoc.x+20, parentLoc.y+20);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblUsername = new JLabel("Username:");
			lblUsername.setBounds(126, 105, 67, 14);
			contentPanel.add(lblUsername);
		}
		{
			usernameField = new JTextField();
			usernameField.setBounds(208, 102, 86, 20);
			contentPanel.add(usernameField);
			usernameField.setColumns(10);
		}
		{
			JLabel lblPassword = new JLabel("Password:");
			lblPassword.setBounds(126, 136, 65, 14);
			contentPanel.add(lblPassword);
		}
		{
			passwordField = new JPasswordField();
			passwordField.setBounds(208, 133, 86, 20);
			contentPanel.add(passwordField);
			passwordField.setColumns(10);
		}
		
		JLabel lblNewLabel = new JLabel("Please enter your credentials");
		lblNewLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
		lblNewLabel.setBounds(23, 30, 297, 40);
		contentPanel.add(lblNewLabel);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton loginButton = new JButton("Login");
				loginButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						String username = usernameField.getText();
						String password = passwordField.getText();
						if(CommInterface.Auth(username, password) == 1) {
							//Util.Success("loged in");
							parent.setVisible(true);
							setVisible(false);
						}else {
							Util.Alert("Enter valid login info pls");
						}
						
					}
				});
				loginButton.setActionCommand("OK");
				buttonPane.add(loginButton);
				getRootPane().setDefaultButton(loginButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ConnectionManager.Disconnect();
						parent.dispose();
						dispose();
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
