import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class Room_Signup extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private JTextField idTF;
	private JTextField priceTF;
	private JTextField reviewTF;
	private JTextField facilityTF;
	private JTextField searchTF;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField statusTF;
	
	private String a = "Booked";
	private JComboBox searchCB;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Room_Signup frame = new Room_Signup();
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
	
	public Room_Signup() {
		design();
		conn = (Connection) SQLConnection.ConnecrDb();
		loadTable();
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
			String query = "Select * FROM room";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadToField() {
		try {
			int row = dataTable.getSelectedRow();
			String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
			String query = "Select * FROM room WHERE Room_ID = ' " + ID + " ' ";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();

			while (rs.next()) {
				idTF.setText(rs.getString("Room_ID"));
				priceTF.setText(rs.getString("Room_Price"));
				facilityTF.setText(rs.getString("Room_Facilities"));
				reviewTF.setText(rs.getString("Room_Review"));
				statusTF.setText(rs.getString("Room_Status"));
				
			pst.close();
			rs.close();
			} 
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void status() {
		try {
			int row = dataTable.getSelectedRow();
			String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
			String query = "UPDATE room SET Room_Price = ' " + priceTF.getText() + " ', Room_Facilities =' " + facilityTF.getText()
			+ " ', Room_Review = ' " + reviewTF.getText() + " ',Room_Status = ' " + a
			+ " ' WHERE Room_ID = ' " + ID + " ' ";
			pst = conn.prepareStatement(query);
			pst.execute();
			
			JOptionPane.showMessageDialog(null, "Room Confirmed");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Search() {
		try {
			String selection = (String) searchCB.getSelectedItem();
			String query = "SELECT Room_ID,Room_Price,Room_Facilities,Room_Review,Room_Status FROM room Where "+ selection + " LIKE '"+searchTF.getText()+"%'";
			pst = conn.prepareStatement(query);
			//pst.setString(1, searchTF.getText());
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 719, 619);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRoom = new JButton("Confirm");
		btnRoom.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				status();
				SignUp sup = new SignUp();
				sup.setVisible(true);
				dispose();
			}
		});
		btnRoom.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnRoom.setBounds(257, 556, 167, 53);
		contentPane.add(btnRoom);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 161, 695, 150);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		dataTable.setForeground(Color.GREEN);
		dataTable.setBackground(SystemColor.desktop);
		dataTable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				loadToField();
			}
		});
		scrollPane.setViewportView(dataTable);
		
		JLabel lblNewLabel = new JLabel("Room ID :");
		lblNewLabel.setForeground(SystemColor.activeCaptionText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(20, 352, 104, 28);
		contentPane.add(lblNewLabel);
		
		JLabel lblRoomPrice = new JLabel("Room Price :");
		lblRoomPrice.setForeground(SystemColor.activeCaptionText);
		lblRoomPrice.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomPrice.setBounds(10, 417, 118, 28);
		contentPane.add(lblRoomPrice);
		
		JLabel lblRoomFacility = new JLabel("Room Facility :");
		lblRoomFacility.setForeground(SystemColor.activeCaptionText);
		lblRoomFacility.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomFacility.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomFacility.setBounds(10, 487, 138, 28);
		contentPane.add(lblRoomFacility);
		
		JLabel lblRoomRev = new JLabel("Room Review :");
		lblRoomRev.setForeground(SystemColor.activeCaptionText);
		lblRoomRev.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomRev.setBounds(324, 417, 145, 35);
		contentPane.add(lblRoomRev);
		
		idTF = new JTextField();
		idTF.setForeground(SystemColor.activeCaptionText);
		idTF.setBackground(SystemColor.window);
		idTF.setBounds(134, 352, 157, 35);
		contentPane.add(idTF);
		idTF.setColumns(10);
		
		priceTF = new JTextField();
		priceTF.setForeground(Color.WHITE);
		priceTF.setBackground(SystemColor.window);
		priceTF.setColumns(10);
		priceTF.setBounds(138, 417, 145, 35);
		contentPane.add(priceTF);
		
		reviewTF = new JTextField();
		reviewTF.setForeground(Color.WHITE);
		reviewTF.setBackground(SystemColor.window);
		reviewTF.setColumns(10);
		reviewTF.setBounds(479, 417, 157, 35);
		contentPane.add(reviewTF);
		
		facilityTF = new JTextField();
		facilityTF.setForeground(Color.WHITE);
		facilityTF.setBackground(SystemColor.window);
		facilityTF.setColumns(10);
		facilityTF.setBounds(158, 487, 478, 35);
		contentPane.add(facilityTF);
		
		searchCB = new JComboBox();
		searchCB.setForeground(SystemColor.activeCaptionText);
		searchCB.setBackground(SystemColor.desktop);
		searchCB.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"Search by", "Room_ID", "Room_Price", "Room_Facilities", "Room_Review", "Room_Status"}));
		searchCB.setBounds(49, 62, 337, 53);
		contentPane.add(searchCB);
		
		searchTF = new JTextField();
		searchTF.setForeground(Color.WHITE);
		searchTF.setBackground(SystemColor.scrollbar);
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		searchTF.setBounds(486, 65, 209, 53);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		JButton btnBack = new JButton("");
		btnBack.setBackground(Color.WHITE);
		btnBack.setForeground(new Color(255, 255, 255));
		btnBack.setIcon(new ImageIcon(Room_Signup.class.getResource("/Image/go-back-icon.png")));
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reception r = new Reception();
				r.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(0, 0, 65, 52);
		contentPane.add(btnBack);
		
		JLabel lblChoiceYourRoom = new JLabel("Choice Your Room");
		lblChoiceYourRoom.setForeground(Color.WHITE);
		lblChoiceYourRoom.setFont(new Font("Comic Sans MS", Font.ITALIC, 20));
		lblChoiceYourRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoiceYourRoom.setBounds(99, 20, 478, 35);
		contentPane.add(lblChoiceYourRoom);
		
		JLabel lblRoomStatus = new JLabel("Room Status :");
		lblRoomStatus.setForeground(SystemColor.activeCaptionText);
		lblRoomStatus.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoomStatus.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblRoomStatus.setBounds(324, 352, 132, 28);
		contentPane.add(lblRoomStatus);
		
		statusTF = new JTextField();
		statusTF.setForeground(Color.WHITE);
		statusTF.setBackground(SystemColor.window);
		statusTF.setColumns(10);
		statusTF.setBounds(479, 352, 157, 35);
		contentPane.add(statusTF);
		
		setUndecorated(true);
	}
}
