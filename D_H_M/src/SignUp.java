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

import javax.swing.JCheckBox;
import javax.swing.JPasswordField;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;

public class SignUp extends JFrame {

	private JPanel contentPane;
	private JTextField idTF;
	private JTextField nameTF;
	private JTextField emailTF;
	private JTextField contactTF;
	private JTextField ageTF;
	private JTextField addressTF;
	private JRadioButton rdbtnNewRadioButton,rdbtnNewRadioButton_1;
	private JTextField checkoutTF;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	String v = "";
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	PreparedStatement pst1 = null;
	ResultSet rs1 = null;
	private final ButtonGroup buttonGroup_2 = new ButtonGroup();
	private JTextField purposeTF;
	private JTextField textField;
	private String a = "0.00";
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SignUp frame = new SignUp();
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
	
	public SignUp() {
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
			String query = "INSERT INTO present_guest (Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address,Purpose,Check_Out,G_Cost) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, idTF.getText());
			pst.setString(2, nameTF.getText());
			pst.setString(3, emailTF.getText());
			pst.setString(4, contactTF.getText());
			pst.setString(5, ageTF.getText());
			if(rdbtnNewRadioButton.isSelected()) {
				v = rdbtnNewRadioButton.getText().toString();
			}
			else if(rdbtnNewRadioButton_1.isSelected()) {
				v = rdbtnNewRadioButton_1.getText().toString();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			pst.setString(6, String.valueOf(v));
			pst.setString(7, addressTF.getText());
			pst.setString(8, purposeTF.getText());
			pst.setString(9, checkoutTF.getText());
			pst.setString(10, textField.getText());
			
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "Booking Confirm");
			
			Reception r = new Reception();
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
			String query = "INSERT INTO history_guest (Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address,Purpose,Check_Out,G_Cost) VALUES (?,?,?,?,?,?,?,?,?,?)";
			
			pst1 = conn.prepareStatement(query);
			pst1.setString(1, idTF.getText());
			pst1.setString(2, nameTF.getText());
			pst1.setString(3, emailTF.getText());
			pst1.setString(4, contactTF.getText());
			pst1.setString(5, ageTF.getText());
			if(rdbtnNewRadioButton.isSelected()) {
				v = rdbtnNewRadioButton.getText().toString();
			}
			else if(rdbtnNewRadioButton_1.isSelected()) {
				v = rdbtnNewRadioButton_1.getText().toString();
			}
			else {
				JOptionPane.showMessageDialog(null, "Select Gender");
			}
			pst1.setString(6, String.valueOf(v));
			pst1.setString(7, addressTF.getText());
			pst1.setString(8, purposeTF.getText());
			pst1.setString(9, checkoutTF.getText());
			pst1.setString(10, textField.getText());
			
			pst1.execute();
			pst1.close();
			
			//JOptionPane.showMessageDialog(null, "Booking Confirm");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void other() {
		try {
			String query = "INSERT INTO guest_login (RoomID,Password) VALUES (?,?)";
			
			pst1 = conn.prepareStatement(query);
			pst1.setString(1, idTF.getText());
			pst1.setString(2, passwordField.getText());
			
			
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
		setBounds(100, 100, 656, 683);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		idTF = new JTextField();
		idTF.setHorizontalAlignment(SwingConstants.CENTER);
		idTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		idTF.setColumns(10);
		idTF.setBounds(171, 72, 431, 39);
		contentPane.add(idTF);
		
		nameTF = new JTextField();
		nameTF.setHorizontalAlignment(SwingConstants.CENTER);
		nameTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		nameTF.setColumns(10);
		nameTF.setBounds(171, 121, 431, 39);
		contentPane.add(nameTF);
		
		emailTF = new JTextField();
		emailTF.setHorizontalAlignment(SwingConstants.CENTER);
		emailTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		emailTF.setColumns(10);
		emailTF.setBounds(171, 170, 431, 39);
		contentPane.add(emailTF);
		
		contactTF = new JTextField();
		contactTF.setHorizontalAlignment(SwingConstants.CENTER);
		contactTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		contactTF.setColumns(10);
		contactTF.setBounds(171, 219, 326, 39);
		contentPane.add(contactTF);
		
		ageTF = new JTextField();
		ageTF.setHorizontalAlignment(SwingConstants.CENTER);
		ageTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		ageTF.setColumns(10);
		ageTF.setBounds(171, 268, 172, 32);
		contentPane.add(ageTF);
		
		 rdbtnNewRadioButton = new JRadioButton("Male");
		buttonGroup_2.add(rdbtnNewRadioButton);
		rdbtnNewRadioButton.setFont(new Font("Agency FB", Font.BOLD, 25));
		rdbtnNewRadioButton.setBounds(174, 320, 105, 21);
		contentPane.add(rdbtnNewRadioButton);
		
		 rdbtnNewRadioButton_1 = new JRadioButton("Female");
		buttonGroup_2.add(rdbtnNewRadioButton_1);
		rdbtnNewRadioButton_1.setFont(new Font("Agency FB", Font.BOLD, 25));
		rdbtnNewRadioButton_1.setBounds(281, 320, 105, 21);
		contentPane.add(rdbtnNewRadioButton_1);
		
		addressTF = new JTextField();
		addressTF.setHorizontalAlignment(SwingConstants.CENTER);
		addressTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		addressTF.setColumns(10);
		addressTF.setBounds(173, 358, 431, 49);
		contentPane.add(addressTF);
		
		JLabel lblRoomId = new JLabel("Room ID :");
		lblRoomId.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomId.setForeground(Color.BLACK);
		lblRoomId.setFont(new Font("Arial", Font.BOLD, 15));
		lblRoomId.setBounds(39, 75, 105, 32);
		contentPane.add(lblRoomId);
		
		JLabel lblName = new JLabel("Name :");
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setForeground(Color.BLACK);
		lblName.setFont(new Font("Arial", Font.BOLD, 15));
		lblName.setBounds(31, 124, 130, 32);
		contentPane.add(lblName);
		
		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setHorizontalAlignment(SwingConstants.CENTER);
		lblEmail.setForeground(Color.BLACK);
		lblEmail.setFont(new Font("Arial", Font.BOLD, 15));
		lblEmail.setBounds(31, 173, 130, 32);
		contentPane.add(lblEmail);
		
		JLabel lblContact = new JLabel("Contact :");
		lblContact.setHorizontalAlignment(SwingConstants.CENTER);
		lblContact.setForeground(Color.BLACK);
		lblContact.setFont(new Font("Arial", Font.BOLD, 15));
		lblContact.setBounds(53, 222, 74, 32);
		contentPane.add(lblContact);
		
		JLabel lblAge = new JLabel("Age :");
		lblAge.setHorizontalAlignment(SwingConstants.CENTER);
		lblAge.setForeground(Color.BLACK);
		lblAge.setFont(new Font("Arial", Font.BOLD, 15));
		lblAge.setBounds(70, 268, 57, 32);
		contentPane.add(lblAge);
		
		JLabel lblGender = new JLabel("Gender :");
		lblGender.setHorizontalAlignment(SwingConstants.CENTER);
		lblGender.setForeground(Color.BLACK);
		lblGender.setFont(new Font("Arial", Font.BOLD, 15));
		lblGender.setBounds(53, 320, 74, 32);
		contentPane.add(lblGender);
		
		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setHorizontalAlignment(SwingConstants.CENTER);
		lblAddress.setForeground(Color.BLACK);
		lblAddress.setFont(new Font("Arial", Font.BOLD, 15));
		lblAddress.setBounds(37, 367, 115, 32);
		contentPane.add(lblAddress);
		
		JCheckBox chckbxIAcceptThe = new JCheckBox("I Accept the Terms & Conditions");
		chckbxIAcceptThe.setFont(new Font("Tahoma", Font.BOLD, 15));
		chckbxIAcceptThe.setBounds(171, 571, 425, 32);
		contentPane.add(chckbxIAcceptThe);
		
		JLabel lblSignUpHere = new JLabel("Sign Up here");
		lblSignUpHere.setForeground(Color.BLACK);
		lblSignUpHere.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblSignUpHere.setHorizontalAlignment(SwingConstants.CENTER);
		lblSignUpHere.setBounds(272, 13, 131, 49);
		contentPane.add(lblSignUpHere);
		
		checkoutTF = new JTextField();
		checkoutTF.setHorizontalAlignment(SwingConstants.CENTER);
		checkoutTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		checkoutTF.setColumns(10);
		checkoutTF.setBounds(173, 468, 326, 39);
		contentPane.add(checkoutTF);
		
		JLabel lblCheckout = new JLabel("Checkout :");
		lblCheckout.setHorizontalAlignment(SwingConstants.CENTER);
		lblCheckout.setForeground(Color.BLACK);
		lblCheckout.setFont(new Font("Arial", Font.BOLD, 15));
		lblCheckout.setBounds(31, 472, 115, 32);
		contentPane.add(lblCheckout);
		
		JButton btnBack = new JButton("");
		btnBack.setIcon(new ImageIcon(SignUp.class.getResource("/Image/go-back-icon.png")));
		btnBack.setBackground(Color.WHITE);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reception r = new Reception();
				r.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(0, 0, 105, 49);
		contentPane.add(btnBack);
		
		purposeTF = new JTextField();
		purposeTF.setHorizontalAlignment(SwingConstants.CENTER);
		purposeTF.setFont(new Font("Tahoma", Font.BOLD, 15));
		purposeTF.setColumns(10);
		purposeTF.setBounds(175, 419, 326, 39);
		contentPane.add(purposeTF);
		
		JLabel lblPurpose = new JLabel("Purpose :");
		lblPurpose.setHorizontalAlignment(SwingConstants.CENTER);
		lblPurpose.setForeground(Color.BLACK);
		lblPurpose.setFont(new Font("Arial", Font.BOLD, 15));
		lblPurpose.setBounds(33, 423, 115, 32);
		contentPane.add(lblPurpose);
		
		JButton Register = new JButton("Register");
		Register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(chckbxIAcceptThe.isSelected())
				{
					if(idTF.getText().equals("")) {
						JOptionPane.showMessageDialog(null, "Enter Room ID");
					}
					else if(passwordField.getText().equals("")) {
							JOptionPane.showMessageDialog(null, "Enter Password");
						}
					else {
					other();
					}
					register();
					register1();
				}
				else
				{
					JOptionPane.showMessageDialog(null, "To REGISTER you have to AGREE with our TERMS & CONDITIONS");
				}
			}
		});
		Register.setFont(new Font("Tahoma", Font.BOLD, 18));
		Register.setBounds(245, 618, 212, 56);
		contentPane.add(Register);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setBounds(176, 528, 74, 30);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblCost = new JLabel("Cost :");
		lblCost.setHorizontalAlignment(SwingConstants.CENTER);
		lblCost.setForeground(Color.BLACK);
		lblCost.setFont(new Font("Arial", Font.BOLD, 15));
		lblCost.setBounds(59, 525, 87, 32);
		contentPane.add(lblCost);
		
		passwordField = new JPasswordField();
		passwordField.setHorizontalAlignment(SwingConstants.CENTER);
		passwordField.setBounds(428, 517, 204, 43);
		contentPane.add(passwordField);
		
		JLabel lblNewLabel = new JLabel("Password :");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setBounds(293, 517, 105, 39);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
	}
}
