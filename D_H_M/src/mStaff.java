import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JScrollPane;
import javax.swing.JComboBox;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.SystemColor;

public class mStaff extends JFrame {

	private JPanel contentPane;
	private JTextField searchTF;
	private JComboBox searchCB;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable dataTable;
	private JButton button;
	private JLabel lblPresentStaff;
	private JLabel label;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mStaff frame = new mStaff();
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
	public mStaff(){
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
			String query = "Select S_Serial,S_Name,S_Email,S_Contact,S_Designation,S_Salary,	S_Age,S_Gender,S_Address,S_Join FROM present_staff";
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
			String query = "SELECT S_Name,S_Email,S_Contact,S_Designation,S_Salary,S_Age,S_Gender,S_Address,S_Join FROM present_staff Where "+ selection + " LIKE '"+searchTF.getText()+"%'";
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
	
	private void delete() {
		try {
			int action = JOptionPane.showConfirmDialog(null, "Are You Sure about Leave ?", "Leave",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (action == 0) {
				int row = dataTable.getSelectedRow();
				String Serial = (dataTable.getModel().getValueAt(row, 0)).toString();
				String query = "DELETE FROM present_staff WHERE S_Serial = '" + Serial + "'";
				pst = conn.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Staff leave confirm");
				pst.close();
				loadTable();
				//resetEverything();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void resetEverything() {
		searchTF.setText(null);
		searchCB.setSelectedItem("Search By");
		loadTable();
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 449);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		searchCB = new JComboBox();
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"Search by ", "S_Name", "S_Email", "S_Contact", "S_Designation", "S_Salary", "S_Age", "S_Gender", "S_Address", "S_Join"}));
		searchCB.setFont(new Font("Tahoma", Font.BOLD, 18));
		searchCB.setBounds(0, 10, 274, 50);
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
		searchTF.setBounds(613, 14, 229, 49);
		contentPane.add(searchTF);
		
		JButton btnLeaveconfirm = new JButton("Leave(Confirm)");
		btnLeaveconfirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnLeaveconfirm.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnLeaveconfirm.setBounds(654, 381, 215, 31);
		contentPane.add(btnLeaveconfirm);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Manager m = new Manager();
				m.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(10, 381, 87, 31);
		contentPane.add(btnNewButton);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 93, 841, 240);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		JButton btnAddStaff = new JButton("Add Staff");
		btnAddStaff.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sign_Staff ss = new Sign_Staff();
				ss.setVisible(true);
				dispose();
			}
		});
		btnAddStaff.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnAddStaff.setBounds(402, 381, 191, 31);
		contentPane.add(btnAddStaff);
		
		button = new JButton("");
		button.setIcon(new ImageIcon(mStaff.class.getResource("/Image/button-round-reload-icon.png")));
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetEverything();
			}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 18));
		button.setBounds(176, 371, 140, 41);
		contentPane.add(button);
		
		lblPresentStaff = new JLabel("Present Staff");
		lblPresentStaff.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresentStaff.setForeground(SystemColor.activeCaptionText);
		lblPresentStaff.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblPresentStaff.setBounds(348, 0, 210, 50);
		contentPane.add(lblPresentStaff);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(mStaff.class.getResource("/Image/galaxy-background-design_2001749.jpg")));
		label.setBounds(0, 0, 855, 449);
		contentPane.add(label);
		
		setUndecorated(true);
	}
}
