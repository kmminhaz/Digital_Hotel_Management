import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import java.awt.SystemColor;

public class loginGuest extends JFrame {

	private JPanel contentPane;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	
	public static String rid= "";
	public static String passfield="";
	public static String name="";
	public static String cost="";
	
	private JTextField usernameField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					loginGuest frame = new loginGuest();
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
	public loginGuest(){
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
			String query = "SELECT * FROM present_guest,guest_login WHERE present_guest.Room_ID = ? And guest_login.Password = ?";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, usernameField.getText());
			pst.setString(2, passwordField.getText());
			
			rs = pst.executeQuery();
						
				if(rs.next())
				{			
					JOptionPane.showMessageDialog(null, "Successfully Loged in");
					rid = rs.getString("Room_ID");
					passfield = rs.getString("Password");
					name = rs.getString("G_Name");
					cost = rs.getString("G_Cost");
					Guest g = new Guest();
					g.setVisible(true);
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
		setBounds(100, 100, 480, 270);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblRoomId = new JLabel("Room ID :");
		lblRoomId.setForeground(SystemColor.activeCaptionText);
		lblRoomId.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomId.setBounds(30, 54, 125, 44);
		contentPane.add(lblRoomId);
		
		JLabel label_1 = new JLabel("Password :");
		label_1.setForeground(SystemColor.activeCaptionText);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		label_1.setBounds(30, 144, 125, 44);
		contentPane.add(label_1);
		
		usernameField = new JTextField();
		usernameField.setHorizontalAlignment(SwingConstants.CENTER);
		usernameField.setColumns(10);
		usernameField.setBounds(188, 59, 258, 39);
		contentPane.add(usernameField);
		
		JButton button = new JButton("LogIn");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(usernameField.getText().equals("")) {
					JOptionPane.showMessageDialog(null, "Enter Room ID");
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
		button.setBounds(283, 229, 165, 44);
		contentPane.add(button);
		
		JButton button_1 = new JButton("Back");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FrontPage fp = new FrontPage();
				fp.setVisible(true);
				dispose();
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.setBounds(51, 231, 125, 39);
		contentPane.add(button_1);
		
		JLabel lblGuestLogin = new JLabel("Guest LogIn");
		lblGuestLogin.setForeground(SystemColor.activeCaptionText);
		lblGuestLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblGuestLogin.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblGuestLogin.setBounds(130, 0, 248, 44);
		contentPane.add(lblGuestLogin);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(188, 144, 258, 39);
		contentPane.add(passwordField);
		
		JButton button_2 = new JButton("");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button_2.setIcon(new ImageIcon(loginGuest.class.getResource("/Image/Actions-file-close-icon.png")));
		button_2.setBounds(438, 0, 42, 33);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(loginGuest.class.getResource("/Image/giphy (23).gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 480, 270);
		contentPane.add(label);
		
		setUndecorated(true);
	}
}
