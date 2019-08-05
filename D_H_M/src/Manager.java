import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTabbedPane;
import javax.swing.ImageIcon;
import java.awt.SystemColor;

public class Manager extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTable dataTable;
	private JTable dataTable_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager frame = new Manager();
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
	public Manager(){
		design();
		conn = SQLConnection.ConnecrDb();
		JOptionPane.showMessageDialog(null, "Welcome " + loginManager.welcomeName);
		loadTable();
		loadTable1();
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
			String query = "Select * FROM reception_login";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void loadTable1() {
		try {
			String query = "Select * FROM manager_login";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			dataTable_1.setModel(DbUtils.resultSetToTableModel(rs));
			pst.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void design(){
		setTitle("Manager Access");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 674, 528);
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setForeground(SystemColor.activeCaptionText);
		comboBox.setBackground(SystemColor.window);
		comboBox.setBounds(0, 62, 274, 51);
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox.getSelectedItem().toString().equals("Guest")) {
					mGuest mg = new mGuest();
					mg.setVisible(true);
					dispose();
				}
				else if(comboBox.getSelectedItem().toString().equals("Staff")){
					mStaff ms = new mStaff();
					ms.setVisible(true);
					dispose();
				}else {
					System.out.println("Select");
				}
			
			}
		});
		comboBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				}
		});
		contentPane.setLayout(null);
		comboBox.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Present Data", "Guest", "Staff"}));
		contentPane.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setBackground(SystemColor.window);
		comboBox_1.setForeground(SystemColor.activeCaptionText);
		comboBox_1.setBounds(400, 62, 274, 51);
		comboBox_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(comboBox_1.getSelectedItem().toString().equals("Guest")) {
					hGuest hg = new hGuest();
					hg.setVisible(true);
					dispose();
				}
				else if(comboBox_1.getSelectedItem().toString().equals("Staff")){
					hStaff hs = new hStaff();
					hs.setVisible(true);
					dispose();
				}else {
					System.out.println("Select");
				}
			}
		});
		comboBox_1.setFont(new Font("Tahoma", Font.BOLD, 18));
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"History Data", "Guest", "Staff"}));
		contentPane.add(comboBox_1);
		
		JButton btnStaffs = new JButton("LogOut");
		btnStaffs.setBounds(288, 492, 120, 36);
		btnStaffs.setBackground(Color.DARK_GRAY);
		btnStaffs.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginManager lm = new loginManager();
				lm.setVisible(true);
				dispose();
			}
		});
		btnStaffs.setForeground(Color.WHITE);
		btnStaffs.setFont(new Font("Tahoma", Font.BOLD, 18));
		contentPane.add(btnStaffs);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.control);
		tabbedPane.setBounds(0, 166, 674, 323);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("Receptionist's", null, panel, null);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 669, 299);
		panel.add(scrollPane);
		
		dataTable = new JTable();
		dataTable.setForeground(SystemColor.activeCaptionText);
		dataTable.setBackground(SystemColor.window);
		scrollPane.setViewportView(dataTable);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Manager's", null, panel_1, null);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(0, 0, 669, 299);
		panel_1.add(scrollPane_1);
		
		dataTable_1 = new JTable();
		dataTable_1.setForeground(SystemColor.activeCaptionText);
		dataTable_1.setBackground(SystemColor.window);
		scrollPane_1.setViewportView(dataTable_1);
		
		JLabel lblManagerAccess = new JLabel("Manager Access");
		lblManagerAccess.setForeground(SystemColor.activeCaptionText);
		lblManagerAccess.setFont(new Font("Tahoma", Font.ITALIC, 18));
		lblManagerAccess.setHorizontalAlignment(SwingConstants.CENTER);
		lblManagerAccess.setBounds(258, 0, 150, 36);
		contentPane.add(lblManagerAccess);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Manager.class.getResource("/Image/parallax19.jpg")));
		label.setBounds(0, 0, 674, 528);
		contentPane.add(label);
		
		setUndecorated(true);
	}
}
