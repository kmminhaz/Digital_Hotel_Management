import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class loginManager extends JFrame {

	private JPanel contentPane;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public static String welcomeName = "";
	
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginManager frame = new loginManager();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public loginManager(){
		design();
		conn = (Connection) SQLConnection.ConnecrDb();
		centerize();
	}
	
	public void centerize() {
		Dimension screenSize, frameSize;
		int x, y;
		screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frameSize = getSize();
		x = (screenSize.width - frameSize.width) / 2;
		y = (screenSize.height - frameSize.height) / 2;
		setLocation(x, y);
	}
	
	private void login() {
		try {
			String query = "SELECT * FROM MANAGER_lOGIN WHERE Username = ? And Password = ?";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, usernameField.getText());
			pst.setString(2, passwordField.getText());
			
			rs = pst.executeQuery();
						
				if(rs.next())
				{
					welcomeName = rs.getString("M_Name");
					
					//JOptionPane.showMessageDialog(null, "Successfully Loged in");
					//JOptionPane.showMessageDialog(null, welcomeName);
					Manager m = new Manager();
					m.setVisible(true);
					dispose();
				}
				else {
					JOptionPane.showMessageDialog(null, "Wrong username or password");
				}
				
				pst.close();
				rs.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 815, 537);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("Password :");
		label_1.setForeground(SystemColor.activeCaptionText);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_1.setBounds(289, 276, 67, 14);
		contentPane.add(label_1);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		usernameField.setText("Enter_Username");
		usernameField.setForeground(SystemColor.textInactiveText);
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setBackground(SystemColor.text);
		usernameField.setColumns(10);
		usernameField.setBounds(289, 215, 258, 39);
		contentPane.add(usernameField);
		
		JButton button = new JButton("LogIn");
		button.setForeground(SystemColor.textInactiveText);
		button.setBackground(SystemColor.control);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Username");
				}
				else if(passwordField.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Enter Password");
					}
				else {
				login();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBounds(451, 369, 165, 44);
		contentPane.add(button);
		
		JButton btnBack = new JButton("Back");
		btnBack.setForeground(SystemColor.textInactiveText);
		btnBack.setBackground(SystemColor.control);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontPage fp = new FrontPage();
				fp.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(221, 371, 125, 39);
		contentPane.add(btnBack);
		
		JLabel lblLoginHere = new JLabel("Manager LogIn");
		lblLoginHere.setForeground(SystemColor.activeCaptionText);
		lblLoginHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblLoginHere.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblLoginHere.setBounds(289, 110, 248, 27);
		contentPane.add(lblLoginHere);
		
		passwordField = new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		passwordField.setEchoChar('&');
		passwordField.setForeground(SystemColor.textInactiveText);
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBackground(SystemColor.text);
		passwordField.setBounds(289, 296, 258, 39);
		contentPane.add(passwordField);
		
		JButton button_1 = new JButton("");
		button_1.setBackground(SystemColor.control);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_1.setIcon(new ImageIcon(loginManager.class.getResource("/Image/Actions-file-close-icon.png")));
		button_1.setBounds(773, 0, 42, 33);
		contentPane.add(button_1);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(loginManager.class.getResource("/Image/manager.png")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(350, 147, 125, 61);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(loginManager.class.getResource("/Image/parallax19.jpg")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 815, 537);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
	}
}
