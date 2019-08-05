import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class orderList extends JFrame {

	private JPanel contentPane;
	private JTable dataTable;
	private JButton btnThanks;
	private JButton btnBack;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JLabel lblOrderList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					orderList frame = new orderList();
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
	public orderList(){
		design();
		conn = SQLConnection.ConnecrDb();
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
	
	private void thanks() {
			try {
				int action = JOptionPane.showConfirmDialog(null, "Did you get your Food ?", "Delete",
						JOptionPane.YES_NO_CANCEL_OPTION);
				if (action == 0) {
					String query = "TRUNCATE table Order_list";
					pst = conn.prepareStatement(query);
					pst.execute();
					JOptionPane.showMessageDialog(null, "Thanks for ordering");
					pst.close();
					loadTable();
					//resetEverything();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	private void loadTable() {
		try {
			String query = "Select * FROM Order_list";
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
		setBounds(100, 100, 548, 312);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.activeCaptionText);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 76, 522, 177);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		btnThanks = new JButton("Thanks");
		btnThanks.setForeground(SystemColor.activeCaptionText);
		btnThanks.setBackground(SystemColor.control);
		btnThanks.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				thanks();
			}
		});
		btnThanks.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnThanks.setBounds(400, 263, 132, 37);
		contentPane.add(btnThanks);
		
		btnBack = new JButton("Back");
		btnBack.setForeground(SystemColor.activeCaptionText);
		btnBack.setBackground(SystemColor.control);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest o = new Guest();
				o.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(10, 261, 92, 41);
		contentPane.add(btnBack);
		
		lblOrderList = new JLabel("Order List");
		lblOrderList.setFont(new Font("Tempus Sans ITC", Font.BOLD | Font.ITALIC, 18));
		lblOrderList.setForeground(SystemColor.desktop);
		lblOrderList.setHorizontalAlignment(SwingConstants.CENTER);
		lblOrderList.setBounds(196, 26, 132, 37);
		contentPane.add(lblOrderList);
		
		setUndecorated(true);
	}
}
