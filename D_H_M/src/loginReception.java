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
import java.awt.Color;
import java.awt.SystemColor;
import javax.swing.UIManager;

public class loginReception extends JFrame {

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
					loginReception frame = new loginReception();
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
	public loginReception() {
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
			String query = "SELECT * FROM RECEPTION_lOGIN WHERE Username = ? And Password = ?";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, usernameField.getText());
			pst.setString(2, passwordField.getText());
			
			rs = pst.executeQuery();
						
				if(rs.next())
				{
					welcomeName = rs.getString("R_Name");
					
					JOptionPane.showMessageDialog(null, "Successfully Loged in");
					Reception r = new Reception();
					r.setVisible(true);
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
		setBounds(100, 100, 816, 556);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label_1 = new JLabel("Password :");
		label_1.setForeground(Color.WHITE);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.ITALIC, 12));
		label_1.setBounds(264, 306, 66, 14);
		contentPane.add(label_1);
		
		usernameField = new JTextField();
		usernameField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		usernameField.setText("Enter_username");
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setForeground(SystemColor.textInactiveText);
		usernameField.setBackground(SystemColor.window);
		usernameField.setColumns(10);
		usernameField.setBounds(263, 235, 304, 39);
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
		button.setBounds(451, 395, 165, 44);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.setForeground(SystemColor.textInactiveText);
		button_1.setBackground(SystemColor.control);
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontPage fp = new FrontPage();
				fp.setVisible(true);
				dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.setBounds(221, 397, 125, 39);
		contentPane.add(button_1);
		
		JLabel lblManagerLogin = new JLabel("Reception LogIn");
		lblManagerLogin.setForeground(Color.BLACK);
		lblManagerLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerLogin.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblManagerLogin.setBounds(276, 120, 248, 22);
		contentPane.add(lblManagerLogin);
		
		passwordField =  new JPasswordField();
		passwordField.setFont(new Font("Tahoma", Font.ITALIC, 10));
		passwordField.setEchoChar('&');
		passwordField.setToolTipText("");
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setForeground(SystemColor.textInactiveText);
		passwordField.setBackground(SystemColor.window);
		passwordField.setBounds(263, 322, 304, 39);
		contentPane.add(passwordField);
		
		JButton button_2 = new JButton("");
		button_2.setBackground(Color.WHITE);
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setIcon(new ImageIcon(loginReception.class.getResource("/Image/Actions-file-close-icon.png")));
		button_2.setBounds(774, 0, 42, 33);
		contentPane.add(button_2);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(loginReception.class.getResource("/Image/recep.png")));
		lblNewLabel.setBounds(349, 165, 93, 71);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(loginReception.class.getResource("/Image/download ().jpg")));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 816, 556);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
	}
}
