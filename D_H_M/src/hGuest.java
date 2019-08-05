import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.DefaultComboBoxModel;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class hGuest extends JFrame {

	private JPanel contentPane;
	private JTextField searchTF;
	private JButton btnBack;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable dataTable;
	private JScrollPane scrollPane;
	private JComboBox searchCB;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hGuest frame = new hGuest();
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
	public hGuest() {
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
			String query = "Select Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address,Check_In,Purpose,Check_Out FROM history_guest";
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
				String query = "SELECT Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address FROM history_guest Where "+ selection + " LIKE '"+searchTF.getText()+"%'";
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
		setBounds(100, 100, 906, 537);
		contentPane = new JPanel();
		contentPane.setBackground(Color.BLACK);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchCB = new JComboBox();
		searchCB.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"Search by ", "Room_ID", "G_Name", "G_Email", "G_Contact", "G_Age", "G_Gender", "G_Address"}));
		searchCB.setBounds(10, 21, 327, 47);
		contentPane.add(searchCB);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		searchTF.setBounds(557, 21, 314, 47);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager m = new Manager();
				m.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(10, 477, 87, 23);
		contentPane.add(btnBack);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 100, 872, 353);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		setUndecorated(true);;
	}
}
