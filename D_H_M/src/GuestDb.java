import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GuestDb extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private JTextField searchTF;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JComboBox searchCB;
	private JButton btnNewButton;
	private JButton btnBack;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuestDb frame = new GuestDb();
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
	public GuestDb(){
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
			String query = "Select Room_ID,G_Name,G_Email,G_Contact,G_Age,G_Gender,G_Address,G_Cost FROM present_guest";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void delete() {
		try {
			int action = JOptionPane.showConfirmDialog(null, "Are You Sure about Checkout ?", "Checkout",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (action == 0) {
				int row = dataTable.getSelectedRow();
				String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
				String query = "DELETE FROM present_guest WHERE Room_ID = '" + ID + "'";
				pst = conn.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Guest Checkout Confirm");
				pst.close();
				loadTable();
				//resetEverything();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Search() {
		try {
			String selection = (String) searchCB.getSelectedItem();
			String query = "SELECT Room_ID,G_Name,G_Cost FROM present_guest Where "+ selection + " LIKE '"+searchTF.getText()+"%'";
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
	
	private void resetEverything() {
		searchTF.setText(null);
		searchCB.setSelectedItem("Search By");
		loadTable();
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 764, 423);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 143, 730, 169);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		searchCB = new JComboBox();
		searchCB.setFont(new Font("Tahoma", Font.BOLD, 16));
		searchCB.setModel(new DefaultComboBoxModel(new String[] {"Search By", "Room_ID", "G_Name", "G_Cost"}));
		searchCB.setBounds(10, 64, 282, 48);
		contentPane.add(searchCB);
		
		searchTF = new JTextField();
		searchTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		searchTF.setBounds(529, 64, 211, 48);
		contentPane.add(searchTF);
		searchTF.setColumns(10);
		
		JButton btnCheckout = new JButton("CheckOut");
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCheckout.setBounds(513, 342, 227, 34);
		contentPane.add(btnCheckout);
		
		JLabel lblPresentGuests = new JLabel("Present Guest's");
		lblPresentGuests.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblPresentGuests.setHorizontalAlignment(SwingConstants.CENTER);
		lblPresentGuests.setBounds(249, 10, 322, 44);
		contentPane.add(lblPresentGuests);
		
		btnNewButton = new JButton("Refresh");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				resetEverything();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(280, 339, 140, 39);
		contentPane.add(btnNewButton);
		
		btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reception r = new Reception();
				r.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(10, 337, 132, 39);
		contentPane.add(btnBack);
	}
}
