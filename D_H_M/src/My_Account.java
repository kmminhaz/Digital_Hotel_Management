import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class My_Account extends JFrame {

	private JPanel contentPane;
	private JTextField idTF;
	private JTextField nameTF;
	private JTextField costTF;
	private JTextField passwordField;
	private JComboBox reviewCombo;

	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable dataTable;

	private String a = "";
	private String b = "";
	private String c = "";

	private JScrollPane scrollPane;
	private JLabel lblSelectYourRoom;
	private JButton btnNewButton;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					My_Account frame = new My_Account();
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
	public My_Account() {
		design();

		loginGuest lg = new loginGuest();
		idTF.setText(lg.rid);
		nameTF.setText(lg.name);
		costTF.setText(lg.cost);

		
		
		passwordField = new JTextField();
		passwordField.setBounds(344, 98, 129, 36);
		contentPane.add(passwordField);
		passwordField.setColumns(10);
		passwordField.setText(lg.passfield);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 328, 473, 40);
		contentPane.add(scrollPane);

		dataTable = new JTable();
		dataTable.setForeground(SystemColor.activeCaptionText);
		dataTable.setBackground(SystemColor.control);
		scrollPane.setViewportView(dataTable);

		lblSelectYourRoom = new JLabel("Select your room from the down below to give review");
		lblSelectYourRoom.setForeground(SystemColor.activeCaptionText);
		lblSelectYourRoom.setBackground(SystemColor.desktop);
		lblSelectYourRoom.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblSelectYourRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectYourRoom.setBounds(10, 288, 473, 30);
		contentPane.add(lblSelectYourRoom);

		btnNewButton = new JButton("Order List");
		btnNewButton.setBackground(SystemColor.desktop);
		btnNewButton.setForeground(SystemColor.activeCaptionText);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				orderList ol = new orderList();
				ol.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(216, 382, 127, 36);
		contentPane.add(btnNewButton);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(My_Account.class.getResource("/Image/download 7.jpg")));
		label.setBounds(0, 0, 489, 430);
		contentPane.add(label);
		conn = (Connection) SQLConnection.ConnecrDb();
		//loadTable();
		Search();
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

	private void loadTable() {
		try {
			String query = "Select Room_ID,Room_Price,Room_Facilities,Room_Review,Room_Status FROM room";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void combo() {
		try {
			int row = dataTable.getSelectedRow();
			String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
			String query = "UPDATE room SET Room_Review = ' " + reviewCombo.getSelectedItem().toString()
					+ " ' WHERE Room_ID = ' " + ID + " ' ";
			pst = conn.prepareStatement(query);
			pst.execute();

			JOptionPane.showMessageDialog(null, "Thanks for your Review");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void Search() {
		try {
			
			//Integer selection = Integer.parseInt(idTF.getText());

			String query = "SELECT Room_ID,Room_Price,Room_Facilities,Room_Review,Room_Status FROM room WHERE Room_ID  LIKE '"
					+Integer.parseInt( idTF.getText()) + "%'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void design() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 489, 430);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblName = new JLabel("Name :");
		lblName.setForeground(SystemColor.activeCaptionText);
		lblName.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblName.setHorizontalAlignment(SwingConstants.CENTER);
		lblName.setBounds(25, 168, 94, 36);
		contentPane.add(lblName);

		JLabel lblRoomId = new JLabel("Room ID :");
		lblRoomId.setForeground(SystemColor.desktop);
		lblRoomId.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomId.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomId.setBounds(10, 94, 109, 36);
		contentPane.add(lblRoomId);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setForeground(SystemColor.desktop);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblPassword.setBounds(215, 94, 109, 36);
		contentPane.add(lblPassword);

		idTF = new JTextField();
		idTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		idTF.setColumns(10);
		idTF.setBounds(129, 97, 76, 37);
		contentPane.add(idTF);

		nameTF = new JTextField();
		nameTF.setColumns(10);
		nameTF.setBounds(129, 168, 305, 36);
		contentPane.add(nameTF);

		reviewCombo = new JComboBox();
		reviewCombo.setForeground(SystemColor.activeCaptionText);
		reviewCombo.setBackground(SystemColor.window);
		reviewCombo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				combo();
			}
		});
		reviewCombo.setModel(
				new DefaultComboBoxModel(new String[] { "Hotel Review", "*", "* *", "* * *", "* * * *", "* * * * *" }));
		reviewCombo.setFont(new Font("Tahoma", Font.BOLD, 18));
		reviewCombo.setBounds(10, 378, 171, 36);
		contentPane.add(reviewCombo);

		JLabel lblCostTotal = new JLabel("Cost Total :");
		lblCostTotal.setForeground(SystemColor.activeCaptionText);
		lblCostTotal.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblCostTotal.setHorizontalAlignment(SwingConstants.CENTER);
		lblCostTotal.setBounds(24, 232, 109, 36);
		contentPane.add(lblCostTotal);

		costTF = new JTextField();
		costTF.setColumns(10);
		costTF.setBounds(143, 231, 181, 37);
		contentPane.add(costTF);

		JButton btnBack = new JButton("Back");
		btnBack.setBackground(SystemColor.desktop);
		btnBack.setForeground(SystemColor.activeCaptionText);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest g = new Guest();
				g.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(10, 10, 109, 24);
		contentPane.add(btnBack);

		JButton btnExit = new JButton("Exit");
		btnExit.setForeground(SystemColor.activeCaptionText);
		btnExit.setBackground(SystemColor.desktop);
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnExit.setBounds(374, 385, 109, 24);
		contentPane.add(btnExit);

		JLabel lblMyAccount = new JLabel("My Account");
		lblMyAccount.setForeground(SystemColor.desktop);
		lblMyAccount.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblMyAccount.setHorizontalAlignment(SwingConstants.CENTER);
		lblMyAccount.setBounds(188, 29, 156, 37);
		contentPane.add(lblMyAccount);
		
		setUndecorated(true);
	}
}
