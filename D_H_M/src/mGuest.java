import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class mGuest extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private JTextField searchTF;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JComboBox searchCB;
	private JButton btnNewButton;
	private JLabel lblPresentGuest_1;
	private JLabel lblNewLabel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mGuest frame = new mGuest();
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
	public mGuest() {
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
	
	private void resetEverything() {
		loadTable();
	}
	
	private void loadTable() {
		try {
			String query = "Select * FROM present_guest";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Search() {
		try {
			String selection = (String) searchCB.getSelectedItem();
			String query = "SELECT Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address FROM present_guest Where "+ selection + " LIKE '"+searchTF.getText()+"%'";
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
		setBounds(100, 100, 894, 582);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 98, 860, 410);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		searchCB = new JComboBox();
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"Search by ", "Room_ID", "G_Name", "G_Email", "G_Contact", "G_Age", "G_Gender", "G_Address"}));
		searchCB.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchCB.setBounds(10, 38, 260, 50);
		contentPane.add(searchCB);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		searchTF.setHorizontalAlignment(SwingConstants.CENTER);
		searchTF.setColumns(10);
		searchTF.setBounds(673, 38, 197, 49);
		contentPane.add(searchTF);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager m = new Manager();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(10, 539, 92, 24);
		contentPane.add(btnBack);
		
		btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetEverything();
			}
		});
		btnNewButton.setIcon(new ImageIcon(mGuest.class.getResource("/Image/Button-Reload-icon.png")));
		btnNewButton.setBounds(834, 523, 46, 40);
		contentPane.add(btnNewButton);
		
		lblPresentGuest_1 = new JLabel("Present Guest");
		lblPresentGuest_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPresentGuest_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresentGuest_1.setForeground(SystemColor.activeCaptionText);
		lblPresentGuest_1.setBounds(354, 10, 243, 50);
		contentPane.add(lblPresentGuest_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(mGuest.class.getResource("/Image/galaxy-background-design_2001749.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 922, 582);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
	}
}
