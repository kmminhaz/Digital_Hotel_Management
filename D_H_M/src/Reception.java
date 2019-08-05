import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.SystemColor;
import javax.swing.ImageIcon;

public class Reception extends JFrame {

	private JPanel contentPane;
	private JButton btnNewButton_1;
	private JTable dataTable;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reception frame = new Reception();
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
	public Reception() {
		design();
		conn = (Connection) SQLConnection.ConnecrDb();
		loadTable();
		JOptionPane.showMessageDialog(null, "Welcome " + loginReception.welcomeName);
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
			String query = "Select * FROM Help";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Done() {
		try {
			int action = JOptionPane.showConfirmDialog(null, "Did you Solve the Problem ?", "Delete",
					JOptionPane.YES_NO_CANCEL_OPTION);
			if (action == 0) {
				String query = "TRUNCATE table Help";
				pst = conn.prepareStatement(query);
				pst.execute();
				JOptionPane.showMessageDialog(null, "Help Done");
				pst.close();
				loadTable();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 480, 533);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("Booked");
		btnNewButton.setForeground(SystemColor.activeCaptionText);
		btnNewButton.setBackground(SystemColor.control);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Booked b = new Booked();
				b.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton.setBounds(126, 137, 223, 28);
		contentPane.add(btnNewButton);
		
		btnNewButton_1 = new JButton("Book Now");
		btnNewButton_1.setForeground(SystemColor.activeCaptionText);
		btnNewButton_1.setBackground(SystemColor.control);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Room_Signup rup = new Room_Signup();
				rup.setVisible(true);
				dispose();
			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.BOLD, 30));
		btnNewButton_1.setBounds(126, 67, 223, 28);
		contentPane.add(btnNewButton_1);
		
		JLabel lblCustomerFor = new JLabel("Customer for ");
		lblCustomerFor.setForeground(SystemColor.activeCaptionText);
		lblCustomerFor.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblCustomerFor.setHorizontalAlignment(SwingConstants.CENTER);
		lblCustomerFor.setBounds(122, 215, 127, 28);
		contentPane.add(lblCustomerFor);
		
		JButton btnCheckout = new JButton("Checkout");
		btnCheckout.setForeground(SystemColor.activeCaptionText);
		btnCheckout.setBackground(SystemColor.control);
		btnCheckout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GuestDb  t = new GuestDb();
				t.setVisible(true);
				dispose();
			}
		});
		btnCheckout.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnCheckout.setBounds(247, 218, 121, 23);
		contentPane.add(btnCheckout);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 300, 480, 205);
		contentPane.add(scrollPane);
		
		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);
		
		JButton btnDone = new JButton("Help's Done");
		btnDone.setForeground(SystemColor.activeCaptionText);
		btnDone.setBackground(SystemColor.control);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Done();
			}
		});
		btnDone.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
		btnDone.setBounds(315, 506, 165, 27);
		contentPane.add(btnDone);
		
		JLabel lblNewLabel = new JLabel("Help Status ->");
		lblNewLabel.setForeground(SystemColor.activeCaptionText);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(180, 275, 138, 20);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton_2 = new JButton("LogOut");
		btnNewButton_2.setForeground(SystemColor.activeCaptionText);
		btnNewButton_2.setBackground(SystemColor.control);
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginReception lr = new loginReception();
				lr.setVisible(true);
				dispose();
			}
		});
		btnNewButton_2.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton_2.setBounds(0, 504, 127, 29);
		contentPane.add(btnNewButton_2);
		
		JLabel lblReceptionPage = new JLabel("Reception page");
		lblReceptionPage.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblReceptionPage.setForeground(SystemColor.activeCaptionText);
		lblReceptionPage.setBackground(SystemColor.control);
		lblReceptionPage.setHorizontalAlignment(SwingConstants.CENTER);
		lblReceptionPage.setBounds(84, 10, 284, 23);
		contentPane.add(lblReceptionPage);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Reception.class.getResource("/Image/giphy (12).gif")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 480, 270);
		contentPane.add(label);
		
		setUndecorated(true);
	}
}
