import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.SwingConstants;
import javax.swing.JCheckBox;
import javax.swing.JButton;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;

public class Sign_Staff extends JFrame {

	private JPanel contentPane;
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField contactTF;
	private JTextField ageTF;
	private JTextField addressTF;
	private JTextField designationTF;
	private JTextField salaryTF;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	PreparedStatement pst1 = null;
	ResultSet rs1 = null;
	private JRadioButton rdbtnNewRadioButton;
	private JRadioButton rdbtnNewRadioButton_1;
	String v = "";
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sign_Staff frame = new Sign_Staff();
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
	public Sign_Staff() {
		design();
		conn = SQLConnection.ConnecrDb();
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
	
	private void register(){
		try {
			String query = "INSERT INTO present_staff (S_Name,S_Email,S_Contact,S_Designation,S_Salary,	S_Age,S_Gender,S_Address) VALUES (?,?,?,?,?,?,?,?)";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, nameTF.getText());
			pst.setString(2, emailTF.getText());
			pst.setString(3, contactTF.getText());
			pst.setString(4, designationTF.getText());
			pst.setString(5, salaryTF.getText());
			pst.setString(6, ageTF.getText());
			if(rdbtnNewRadioButton.isSelected()) {
				v = rdbtnNewRadioButton.getText().toString();
			}
			else if(rdbtnNewRadioButton_1.isSelected()) {
				v = rdbtnNewRadioButton_1.getText().toString();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			pst.setString(7, String.valueOf(v));
			pst.setString(8, addressTF.getText());
			
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "New STAFF Added");
			
			mStaff r = new mStaff();
			r.setVisible(true);
			dispose();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void register1(){
		try {
			String query = "INSERT INTO history_staff (S_Name,S_Email,S_Contact,S_Designation,S_Salary,S_Age,S_Gender,S_Address) VALUES (?,?,?,?,?,?,?,?)";
			
			pst1 = conn.prepareStatement(query);
			pst1.setString(1, nameTF.getText());
			pst1.setString(2, emailTF.getText());
			pst1.setString(3, contactTF.getText());
			pst1.setString(4, designationTF.getText());
			pst1.setString(5, salaryTF.getText());
			pst1.setString(6, ageTF.getText());
			if(rdbtnNewRadioButton.isSelected()) {
				v = rdbtnNewRadioButton.getText().toString();
			}
			else if(rdbtnNewRadioButton_1.isSelected()) {
				v = rdbtnNewRadioButton_1.getText().toString();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			pst1.setString(7, String.valueOf(v));
			pst1.setString(8, addressTF.getText());
			
			pst1.execute();
			pst1.close();
			
			//JOptionPane.showMessageDialog(null, "Booking Confirm");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 639);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		nameTF = new JTextField();
		nameTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		nameTF.setColumns(10);
		nameTF.setBounds(193, 73, 431, 39);
		contentPane.add(nameTF);
		
		emailTF = new JTextField();
		emailTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		emailTF.setColumns(10);
		emailTF.setBounds(193, 122, 431, 39);
		contentPane.add(emailTF);
		
		contactTF = new JTextField();
		contactTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		contactTF.setColumns(10);
		contactTF.setBounds(193, 171, 326, 39);
		contentPane.add(contactTF);
		
		ageTF = new JTextField();
		ageTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		ageTF.setColumns(10);
		ageTF.setBounds(193, 328, 172, 32);
		contentPane.add(ageTF);
		
		rdbtnNewRadioButton = new JRadioButton("Male");
		buttonGroup.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Agency FB", Font.BOLD, 25));
		rdbtnNewRadioButton.setBounds(193, 389, 105, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		rdbtnNewRadioButton_1 = new JRadioButton("Female");
		buttonGroup.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		rdbtnNewRadioButton_1.setBounds(300, 389, 105, 21);
		contentPane.add(rdbtnNewRadioButton_1);
		
		addressTF = new JTextField();
		addressTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		addressTF.setColumns(10);
		addressTF.setBounds(193, 439, 431, 57);
		contentPane.add(addressTF);
		
		JLabel label_1 = new JLabel("Name :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.YELLOW);
		label_1.setFont(new Font("Arial", Font.BOLD, 15));
		label_1.setBounds(53, 76, 130, 32);
		contentPane.add(label_1);
		
		JLabel label_2 = new JLabel("Email :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(Color.YELLOW);
		label_2.setFont(new Font("Arial", Font.BOLD, 15));
		label_2.setBounds(53, 125, 130, 32);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("Age :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(Color.YELLOW);
		label_3.setFont(new Font("Arial", Font.BOLD, 15));
		label_3.setBounds(61, 327, 122, 32);
		contentPane.add(label_3);
		
		JCheckBox chckbxIAcceptThe = new JCheckBox("I Accept the Terms & Conditions");
		chckbxIAcceptThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxIAcceptThe.setBounds(199, 503, 425, 32);
		contentPane.add(chckbxIAcceptThe);
		
		JButton button_1 = new JButton("Register");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIAcceptThe.isSelected())
				{
					register();
					register1();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "To REGISTER you have to AGREE with our TERMS & CONDITIONS");
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		button_1.setBounds(218, 541, 212, 56);
		contentPane.add(button_1);
		
		JLabel label_6 = new JLabel("Contact :");
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setForeground(Color.YELLOW);
		label_6.setFont(new Font("Arial", Font.BOLD, 15));
		label_6.setBounds(39, 178, 144, 32);
		contentPane.add(label_6);
		
		JLabel label_7 = new JLabel("Gender :");
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setForeground(Color.YELLOW);
		label_7.setFont(new Font("Arial", Font.BOLD, 15));
		label_7.setBounds(43, 389, 144, 32);
		contentPane.add(label_7);
		
		JLabel label_8 = new JLabel("Address :");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setForeground(Color.YELLOW);
		label_8.setFont(new Font("Arial", Font.BOLD, 15));
		label_8.setBounds(39, 452, 144, 32);
		contentPane.add(label_8);
		
		designationTF = new JTextField();
		designationTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		designationTF.setColumns(10);
		designationTF.setBounds(193, 220, 431, 39);
		contentPane.add(designationTF);
		
		JLabel lblDe = new JLabel("Designation :");
		lblDe.setHorizontalAlignment(SwingConstants.CENTER);
		lblDe.setForeground(Color.YELLOW);
		lblDe.setFont(new Font("Arial", Font.BOLD, 15));
		lblDe.setBounds(49, 224, 105, 32);
		contentPane.add(lblDe);
		
		salaryTF = new JTextField();
		salaryTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		salaryTF.setColumns(10);
		salaryTF.setBounds(193, 279, 431, 39);
		contentPane.add(salaryTF);
		
		JLabel label_4 = new JLabel("Salary :");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setForeground(Color.YELLOW);
		label_4.setFont(new Font("Arial", Font.BOLD, 15));
		label_4.setBounds(67, 283, 105, 32);
		contentPane.add(label_4);
		
		JLabel label = new JLabel("Sign Up here");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Tahoma", Font.BOLD, 18));
		label.setBounds(299, 14, 131, 49);
		contentPane.add(label);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager m = new Manager();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setForeground(SystemColor.activeCaptionText);
		btnBack.setBackground(SystemColor.control);
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(10, 560, 93, 39);
		contentPane.add(btnBack);
		
		setUndecorated(true);
	}

}
