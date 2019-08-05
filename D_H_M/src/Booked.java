import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.SystemColor;

public class Booked extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JScrollPane scrollPane;
	private JLabel lblBookedRoom;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Booked frame = new Booked();
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
	public Booked() {
		design();
		conn = (Connection) SQLConnection.ConnecrDb();
		loadTable();
	}
	
	private void loadTable() {
		try {
			String a = "booked";
			String query = "Select * FROM room WHERE Room_Status = ' " + a + " ' ";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 703, 475);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 55, 703, 373);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		dataTable.setForeground(SystemColor.textInactiveText);
		dataTable.setBackground(SystemColor.desktop);
		scrollPane.setViewportView(dataTable);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Reception r = new Reception();
				r.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		btnNewButton.setBounds(265, 440, 131, 35);
		contentPane.add(btnNewButton);
		
		lblBookedRoom = new JLabel("Booked Room's");
		lblBookedRoom.setForeground(SystemColor.activeCaptionText);
		lblBookedRoom.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblBookedRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblBookedRoom.setBounds(300, 10, 131, 35);
		contentPane.add(lblBookedRoom);
		
		setUndecorated(true);
	}

}
