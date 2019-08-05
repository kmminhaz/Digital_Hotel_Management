import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTabbedPane;
import javax.swing.JTable;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.MouseMotionAdapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.Toolkit;

import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class FoodCatalouge extends JFrame {

	private JPanel contentPane;

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
					FoodCatalouge frame = new FoodCatalouge();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public static int Serial;
	public static int sum = 0;
	
	private JTable dataTable;
	private JTextField idTF;
	// loginGuest lg = new loginGuest();
	// String a = lg.rid;

	/**
	 * Create the frame.
	 */
	public FoodCatalouge() {
		// loginGuest lg = new loginGuest();
		// idTF.setText(lg.rid);
		design();
		loginGuest lg = new loginGuest();
		idTF.setText(lg.rid);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Guest g = new Guest();
				g.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBack.setBounds(538, 10, 87, 23);
		contentPane.add(btnBack);
		conn = SQLConnection.ConnecrDb();
		//loadTable();
		Search();
		okk();
		next();
		centerize();
		// cost();
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

	private void cost() {
		System.out.println();
		try {
			int row = dataTable.getSelectedRow();
			// String ID = (dataTable.getModel().getValueAt(row, 0)).toString();
			String query = "UPDATE present_guest SET G_Cost = ' " + String.valueOf(sum) + " ' WHERE Room_ID = ' " + 15
					+ " ' ";
			loginGuest lg = new loginGuest();
			String q = "UPDATE `present_guest` SET `G_Cost` = ' " + Integer.valueOf(sum) + " ' WHERE `present_guest`.`Serial` = ' " + Integer.valueOf(Serial) + " '";
			pst = conn.prepareStatement(q);
			pst.execute();

			//JOptionPane.showMessageDialog(null, "Thanks for your Review");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void next() {
		try {
			//JOptionPane.showMessageDialog(null, "you get it");
			
			loginGuest lg = new loginGuest();
			String query = "Select `present_guest`.`G_Cost` FROM present_guest WHERE `present_guest`.`Room_ID` = '" + lg.rid + "'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			if (rs.next()) {
				 sum = rs.getInt("G_Cost");
				//tr3 = cost;
				//JOptionPane.showMessageDialog(null, +sum);
			}

			else {
				JOptionPane.showMessageDialog(null, "Wrong username or password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void okk() {
		try {
			//JOptionPane.showMessageDialog(null, "you get it");
			
			loginGuest lg = new loginGuest();
			String query = "Select `present_guest`.`Serial` FROM present_guest WHERE `present_guest`.`Room_ID` = '" + lg.rid + "'";
			pst = conn.prepareStatement(query);
			rs = pst.executeQuery();
			if (rs.next()) {
				Serial = rs.getInt("Serial");
				//tr3 = cost;
				//JOptionPane.showMessageDialog(null, +sum);
			}

			else {
				//JOptionPane.showMessageDialog(null, "Wrong username or password");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void Search() {
		try {
			
			//Integer selection = Integer.parseInt(idTF.getText());

			String query = "SELECT Room_ID,G_Cost FROM present_guest WHERE Room_ID  LIKE '"
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
		setBackground(new Color(240, 240, 240));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 639, 694);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.control);
		contentPane.setBorder(new EmptyBorder(5, 5, 50, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(SystemColor.controlShadow);
		tabbedPane.setBounds(5, 26, 628, 592);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBorder(new EmptyBorder(0, 0, 0, 0));
		panel.setToolTipText("");
		panel.setBackground(new Color(240, 240, 240));
		tabbedPane.addTab("BreakFast", null, panel, null);
		panel.setLayout(null);

		JCheckBox chckbxA = new JCheckBox("Omlate.");
		chckbxA.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxA.setForeground(new Color(0, 0, 0));
		chckbxA.setBackground(new Color(245, 245, 245));
		chckbxA.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = 50;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxA.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					//JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chckbxA.setBounds(162, 104, 151, 23);
		panel.add(chckbxA);

		JCheckBox chckbxB = new JCheckBox("Omlate Porotta.");
		chckbxB.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxB.setForeground(new Color(0, 0, 0));
		chckbxB.setBackground(new Color(245, 245, 245));
		chckbxB.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = 70;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxB.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					//JOptionPane.showMessageDialog(null, "Sum"+sum);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chckbxB.setBounds(158, 130, 155, 23);
		panel.add(chckbxB);

		JCheckBox chckbxNewCheckBox = new JCheckBox("Keema Porotta");
		chckbxNewCheckBox.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox.setBounds(158, 156, 155, 23);
		panel.add(chckbxNewCheckBox);

		JCheckBox chckbxNewCheckBox_1 = new JCheckBox("Porotta Pak");
		chckbxNewCheckBox_1.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_1.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_1.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_1.setBounds(158, 184, 155, 23);
		panel.add(chckbxNewCheckBox_1);

		JCheckBox chckbxNewCheckBox_2 = new JCheckBox("Normal Porotta");
		chckbxNewCheckBox_2.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_2.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_2.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_2.setBounds(158, 210, 155, 23);
		panel.add(chckbxNewCheckBox_2);

		JCheckBox chckbxNewCheckBox_3 = new JCheckBox("Semolina");
		chckbxNewCheckBox_3.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_3.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_3.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_3.setBounds(158, 236, 155, 23);
		panel.add(chckbxNewCheckBox_3);

		JCheckBox chckbxNewCheckBox_4 = new JCheckBox("Tandoor Rotti");
		chckbxNewCheckBox_4.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_4.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_4.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_4.setBounds(158, 262, 155, 23);
		panel.add(chckbxNewCheckBox_4);

		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Chicken Porotta");
		chckbxNewCheckBox_5.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_5.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_5.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_5.setBounds(158, 288, 155, 23);
		panel.add(chckbxNewCheckBox_5);

		JCheckBox chckbxNewCheckBox_6 = new JCheckBox("Porotta With Liver");
		chckbxNewCheckBox_6.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_6.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_6.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		chckbxNewCheckBox_6.setBounds(158, 314, 155, 23);
		panel.add(chckbxNewCheckBox_6);

		JCheckBox chckbxNewCheckBox_7 = new JCheckBox("Porotta With Meat");
		chckbxNewCheckBox_7.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_7.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_7.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = 100;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxNewCheckBox_7.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}
		});
		chckbxNewCheckBox_7.setBounds(158, 340, 155, 23);
		panel.add(chckbxNewCheckBox_7);

		JCheckBox chckbxNewCheckBox_8 = new JCheckBox("Porotta with Paya");
		chckbxNewCheckBox_8.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_8.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_8.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = 200;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxNewCheckBox_8.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					JOptionPane.showMessageDialog(null, "Sum"+sum);
				} catch (Exception e20) {
					e20.printStackTrace();
				}

			}
		});
		chckbxNewCheckBox_8.setBounds(158, 366, 155, 23);
		panel.add(chckbxNewCheckBox_8);

		JCheckBox chckbxNewCheckBox_9 = new JCheckBox("Brain With Porotta");
		chckbxNewCheckBox_9.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_9.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_9.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = 150;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxNewCheckBox_9.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					JOptionPane.showMessageDialog(null, "Sum"+sum);
				} catch (Exception e19) {
					e19.printStackTrace();
				}

			}
		});
		chckbxNewCheckBox_9.setBounds(158, 392, 155, 23);
		panel.add(chckbxNewCheckBox_9);

		JCheckBox chckbxNewCheckBox_10 = new JCheckBox("Egg Burgi");
		chckbxNewCheckBox_10.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_10.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_10.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_10.setBounds(158, 418, 155, 23);
		panel.add(chckbxNewCheckBox_10);

		JCheckBox chckbxNewCheckBox_11 = new JCheckBox("Bread Pudding");
		chckbxNewCheckBox_11.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_11.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_11.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_11.setBounds(158, 444, 155, 23);
		panel.add(chckbxNewCheckBox_11);

		JCheckBox chckbxNewCheckBox_12 = new JCheckBox("Fruit Salad");
		chckbxNewCheckBox_12.setFont(new Font("Times New Roman", Font.BOLD, 12));
		chckbxNewCheckBox_12.setForeground(new Color(0, 0, 0));
		chckbxNewCheckBox_12.setBackground(new Color(245, 245, 245));
		chckbxNewCheckBox_12.setBounds(158, 470, 155, 30);
		panel.add(chckbxNewCheckBox_12);

		JLabel label_17 = new JLabel("ITEM");
		label_17.setHorizontalAlignment(SwingConstants.CENTER);
		label_17.setFont(new Font("Algerian", Font.BOLD, 17));
		label_17.setBounds(151, 40, 108, 30);
		panel.add(label_17);

		JLabel label_18 = new JLabel("PRICE");
		label_18.setHorizontalAlignment(SwingConstants.CENTER);
		label_18.setFont(new Font("Algerian", Font.BOLD, 17));
		label_18.setBounds(386, 40, 101, 30);
		panel.add(label_18);

		JLabel lblTk_30 = new JLabel("50 TK");
		lblTk_30.setForeground(new Color(0, 0, 0));
		lblTk_30.setBackground(new Color(204, 255, 255));
		lblTk_30.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_30.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_30.setBounds(392, 107, 105, 23);
		panel.add(lblTk_30);

		JLabel label_71 = new JLabel("70 TK");
		label_71.setForeground(new Color(0, 0, 0));
		label_71.setBackground(new Color(204, 255, 255));
		label_71.setHorizontalAlignment(SwingConstants.CENTER);
		label_71.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_71.setBounds(386, 131, 111, 23);
		panel.add(label_71);

		JLabel lblTk_31 = new JLabel("50 TK");
		lblTk_31.setForeground(new Color(0, 0, 0));
		lblTk_31.setBackground(new Color(204, 255, 255));
		lblTk_31.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_31.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_31.setBounds(392, 157, 105, 23);
		panel.add(lblTk_31);

		JLabel label_73 = new JLabel("50TK");
		label_73.setForeground(new Color(0, 0, 0));
		label_73.setBackground(new Color(204, 255, 255));
		label_73.setHorizontalAlignment(SwingConstants.CENTER);
		label_73.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_73.setBounds(392, 187, 105, 19);
		panel.add(label_73);

		JLabel lblTk_32 = new JLabel("30 TK");
		lblTk_32.setForeground(new Color(0, 0, 0));
		lblTk_32.setBackground(new Color(204, 255, 255));
		lblTk_32.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_32.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_32.setBounds(392, 211, 105, 23);
		panel.add(lblTk_32);

		JLabel lblTk_33 = new JLabel("100 TK");
		lblTk_33.setForeground(new Color(0, 0, 0));
		lblTk_33.setBackground(new Color(204, 255, 255));
		lblTk_33.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_33.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_33.setBounds(392, 239, 105, 19);
		panel.add(lblTk_33);

		JLabel lblTk_34 = new JLabel("30 TK");
		lblTk_34.setForeground(new Color(0, 0, 0));
		lblTk_34.setBackground(new Color(204, 255, 255));
		lblTk_34.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_34.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_34.setBounds(392, 263, 105, 23);
		panel.add(lblTk_34);

		JLabel lblTk_36 = new JLabel("80 TK");
		lblTk_36.setForeground(new Color(0, 0, 0));
		lblTk_36.setBackground(new Color(204, 255, 255));
		lblTk_36.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_36.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_36.setBounds(392, 291, 105, 19);
		panel.add(lblTk_36);

		JLabel lblTk_35 = new JLabel("100 TK");
		lblTk_35.setForeground(new Color(0, 0, 0));
		lblTk_35.setBackground(new Color(204, 255, 255));
		lblTk_35.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_35.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_35.setBounds(392, 315, 105, 23);
		panel.add(lblTk_35);

		JLabel lblTk_68 = new JLabel("150 TK");
		lblTk_68.setForeground(new Color(0, 0, 0));
		lblTk_68.setBackground(new Color(204, 255, 255));
		lblTk_68.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_68.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_68.setBounds(392, 343, 105, 19);
		panel.add(lblTk_68);

		JLabel label_80 = new JLabel("100 TK");
		label_80.setForeground(new Color(0, 0, 0));
		label_80.setBackground(new Color(204, 255, 255));
		label_80.setHorizontalAlignment(SwingConstants.CENTER);
		label_80.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_80.setBounds(386, 369, 111, 23);
		panel.add(label_80);

		JLabel label_81 = new JLabel("300 TK");
		label_81.setForeground(new Color(0, 0, 0));
		label_81.setBackground(new Color(204, 255, 255));
		label_81.setHorizontalAlignment(SwingConstants.CENTER);
		label_81.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_81.setBounds(392, 393, 105, 23);
		panel.add(label_81);

		JLabel label_82 = new JLabel("50 TK");
		label_82.setForeground(new Color(0, 0, 0));
		label_82.setBackground(new Color(204, 255, 255));
		label_82.setHorizontalAlignment(SwingConstants.CENTER);
		label_82.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_82.setBounds(408, 419, 79, 19);
		panel.add(label_82);

		JLabel lblTk_69 = new JLabel("70 TK");
		lblTk_69.setForeground(new Color(0, 0, 0));
		lblTk_69.setBackground(new Color(204, 255, 255));
		lblTk_69.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_69.setFont(new Font("Times New Roman", Font.BOLD, 18));
		lblTk_69.setBounds(392, 443, 105, 23);
		panel.add(lblTk_69);

		JLabel label_84 = new JLabel("100 TK");
		label_84.setForeground(new Color(0, 0, 0));
		label_84.setBackground(new Color(204, 255, 255));
		label_84.setHorizontalAlignment(SwingConstants.CENTER);
		label_84.setFont(new Font("Times New Roman", Font.BOLD, 18));
		label_84.setBounds(386, 469, 105, 30);
		panel.add(label_84);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(0, 0, 623, 589);
		panel.add(lblNewLabel_2);

		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("Lunch", null, panel_1, null);
		panel_1.setLayout(null);

		JCheckBox chckbxC = new JCheckBox("Plain Rice");
		chckbxC.setForeground(Color.BLACK);
		chckbxC.setBackground(Color.WHITE);
		chckbxC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = 400;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxA.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chckbxC.setBounds(156, 46, 97, 23);
		panel_1.add(chckbxC);

		JCheckBox chckbxNewCheckBox_13 = new JCheckBox("Chicken Biriyani.");
		chckbxNewCheckBox_13.setForeground(Color.BLACK);
		chckbxNewCheckBox_13.setBackground(Color.WHITE);
		chckbxNewCheckBox_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int a = 400;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxNewCheckBox_13.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e18) {
					e18.printStackTrace();
				}

			}
		});
		chckbxNewCheckBox_13.setBounds(156, 92, 132, 23);
		panel_1.add(chckbxNewCheckBox_13);

		JCheckBox chckbxNewCheckBox_14 = new JCheckBox("Meat Biriyani");
		chckbxNewCheckBox_14.setBackground(Color.WHITE);
		chckbxNewCheckBox_14.setForeground(Color.BLACK);
		chckbxNewCheckBox_14.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxNewCheckBox_14.setBounds(156, 145, 132, 23);
		panel_1.add(chckbxNewCheckBox_14);

		JCheckBox chckbxNewCheckBox_15 = new JCheckBox("Fish Biriyani");
		chckbxNewCheckBox_15.setBackground(Color.WHITE);
		chckbxNewCheckBox_15.setForeground(Color.BLACK);
		chckbxNewCheckBox_15.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxNewCheckBox_15.setBounds(156, 118, 97, 23);
		panel_1.add(chckbxNewCheckBox_15);

		JCheckBox chckbxNewCheckBox_16 = new JCheckBox("Briyani Rice");
		chckbxNewCheckBox_16.setForeground(Color.BLACK);
		chckbxNewCheckBox_16.setBackground(Color.WHITE);
		chckbxNewCheckBox_16.setBounds(156, 72, 97, 23);
		panel_1.add(chckbxNewCheckBox_16);

		JCheckBox chckbxNewCheckBox_17 = new JCheckBox("Chicken with Rice");
		chckbxNewCheckBox_17.setBackground(Color.WHITE);
		chckbxNewCheckBox_17.setForeground(Color.BLACK);
		chckbxNewCheckBox_17.setBounds(156, 171, 142, 23);
		panel_1.add(chckbxNewCheckBox_17);

		JCheckBox chckbxNewCheckBox_18 = new JCheckBox("Vegetable Biriyani");
		chckbxNewCheckBox_18.setBackground(Color.WHITE);
		chckbxNewCheckBox_18.setForeground(Color.BLACK);
		chckbxNewCheckBox_18.setBounds(156, 197, 132, 23);
		panel_1.add(chckbxNewCheckBox_18);

		JCheckBox chckbxNewCheckBox_19 = new JCheckBox("Fish with Rice");
		chckbxNewCheckBox_19.setBackground(Color.WHITE);
		chckbxNewCheckBox_19.setForeground(Color.BLACK);
		chckbxNewCheckBox_19.setBounds(156, 223, 132, 23);
		panel_1.add(chckbxNewCheckBox_19);

		JCheckBox chckbxNewCheckBox_20 = new JCheckBox("Meat with Rice");
		chckbxNewCheckBox_20.setBackground(Color.WHITE);
		chckbxNewCheckBox_20.setForeground(Color.BLACK);
		chckbxNewCheckBox_20.setBounds(156, 249, 142, 23);
		panel_1.add(chckbxNewCheckBox_20);

		JCheckBox chckbxNewCheckBox_21 = new JCheckBox("Vegetable with Rice");
		chckbxNewCheckBox_21.setBackground(Color.WHITE);
		chckbxNewCheckBox_21.setForeground(Color.BLACK);
		chckbxNewCheckBox_21.setBounds(156, 275, 161, 23);
		panel_1.add(chckbxNewCheckBox_21);

		JCheckBox chckbxKacchiBiriyani = new JCheckBox("Kacchi Biriyani.");
		chckbxKacchiBiriyani.setBackground(Color.WHITE);
		chckbxKacchiBiriyani.setForeground(Color.BLACK);
		chckbxKacchiBiriyani.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 500;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxKacchiBiriyani.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e17) {
					e17.printStackTrace();
				}

			}
		});
		chckbxKacchiBiriyani.setBounds(156, 301, 161, 23);
		panel_1.add(chckbxKacchiBiriyani);

		JCheckBox chckbxMartton = new JCheckBox("Mutton Vuna Khichuri");
		chckbxMartton.setBackground(Color.WHITE);
		chckbxMartton.setForeground(Color.BLACK);
		chckbxMartton.setBounds(156, 354, 161, 23);
		panel_1.add(chckbxMartton);

		JCheckBox chckbxKacchiBiriyaniWith = new JCheckBox("Kacchi Biriyani with Kabab");
		chckbxKacchiBiriyaniWith.setBackground(Color.WHITE);
		chckbxKacchiBiriyaniWith.setForeground(Color.BLACK);
		chckbxKacchiBiriyaniWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxKacchiBiriyaniWith.setBounds(156, 328, 152, 23);
		panel_1.add(chckbxKacchiBiriyaniWith);

		JCheckBox chckbxMuttonTeheri = new JCheckBox("Mutton Teheri");
		chckbxMuttonTeheri.setBackground(Color.WHITE);
		chckbxMuttonTeheri.setForeground(Color.BLACK);
		chckbxMuttonTeheri.setBounds(156, 380, 132, 23);
		panel_1.add(chckbxMuttonTeheri);

		JCheckBox chckbxWhiteRice = new JCheckBox("White Rice ");
		chckbxWhiteRice.setBackground(Color.WHITE);
		chckbxWhiteRice.setForeground(Color.BLACK);
		chckbxWhiteRice.setBounds(156, 406, 97, 23);
		panel_1.add(chckbxWhiteRice);

		JCheckBox chckbxKhasiRezala = new JCheckBox("Khasi Rezala");
		chckbxKhasiRezala.setBackground(Color.WHITE);
		chckbxKhasiRezala.setForeground(Color.BLACK);
		chckbxKhasiRezala.setBounds(156, 432, 121, 23);
		panel_1.add(chckbxKhasiRezala);

		JCheckBox chckbxKhichuriKhazana = new JCheckBox("Khichuri Khazana");
		chckbxKhichuriKhazana.setBackground(Color.WHITE);
		chckbxKhichuriKhazana.setForeground(Color.BLACK);
		chckbxKhichuriKhazana.setBounds(156, 458, 132, 23);
		panel_1.add(chckbxKhichuriKhazana);

		JCheckBox chckbxFishKabiraji = new JCheckBox("Fish Kabiraji");
		chckbxFishKabiraji.setBackground(Color.WHITE);
		chckbxFishKabiraji.setForeground(Color.BLACK);
		chckbxFishKabiraji.setBounds(156, 484, 97, 23);
		panel_1.add(chckbxFishKabiraji);

		JCheckBox chckbxBengaliDoiMachch = new JCheckBox(" Bengali Doi Machch.");
		chckbxBengaliDoiMachch.setBackground(Color.WHITE);
		chckbxBengaliDoiMachch.setForeground(Color.BLACK);
		chckbxBengaliDoiMachch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 500;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxBengaliDoiMachch.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e16) {
					e16.printStackTrace();
				}

			}
		});
		chckbxBengaliDoiMachch.setBounds(156, 509, 173, 23);
		panel_1.add(chckbxBengaliDoiMachch);

		JCheckBox chckbxFishBiryani = new JCheckBox("Fish Biryani");
		chckbxFishBiryani.setBackground(Color.WHITE);
		chckbxFishBiryani.setForeground(Color.BLACK);
		chckbxFishBiryani.setBounds(156, 532, 121, 23);
		panel_1.add(chckbxFishBiryani);

		JCheckBox chckbxFishPulao = new JCheckBox(" Fish Pulao");
		chckbxFishPulao.setBounds(36, 585, 97, 23);
		panel_1.add(chckbxFishPulao);

		JLabel label_14 = new JLabel("ITEM");
		label_14.setHorizontalAlignment(SwingConstants.CENTER);
		label_14.setFont(new Font("Algerian", Font.BOLD, 17));
		label_14.setBounds(145, 10, 108, 30);
		panel_1.add(label_14);

		JLabel label_15 = new JLabel("PRICE");
		label_15.setHorizontalAlignment(SwingConstants.CENTER);
		label_15.setFont(new Font("Algerian", Font.BOLD, 17));
		label_15.setBounds(366, 10, 101, 30);
		panel_1.add(label_15);

		JLabel lblTk_37 = new JLabel("50 TK");
		lblTk_37.setForeground(Color.BLACK);
		lblTk_37.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_37.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_37.setBounds(386, 47, 81, 23);
		panel_1.add(lblTk_37);

		JLabel lblTk_38 = new JLabel("100 TK");
		lblTk_38.setForeground(Color.BLACK);
		lblTk_38.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_38.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_38.setBounds(386, 73, 81, 19);
		panel_1.add(lblTk_38);

		JLabel lblTk_39 = new JLabel("100 TK");
		lblTk_39.setForeground(Color.BLACK);
		lblTk_39.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_39.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_39.setBounds(387, 91, 87, 23);
		panel_1.add(lblTk_39);

		JLabel lbltk_3 = new JLabel("120TK");
		lbltk_3.setForeground(Color.BLACK);
		lbltk_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_3.setBounds(386, 117, 81, 23);
		panel_1.add(lbltk_3);

		JLabel lbltk_4 = new JLabel("150TK");
		lbltk_4.setForeground(Color.BLACK);
		lbltk_4.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_4.setBounds(386, 142, 81, 19);
		panel_1.add(lbltk_4);

		JLabel lbltk_5 = new JLabel("100TK");
		lbltk_5.setForeground(Color.BLACK);
		lbltk_5.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_5.setBounds(386, 166, 81, 23);
		panel_1.add(lbltk_5);

		JLabel lblTk_40 = new JLabel("70 TK");
		lblTk_40.setForeground(Color.BLACK);
		lblTk_40.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_40.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_40.setBounds(386, 194, 81, 19);
		panel_1.add(lblTk_40);

		JLabel lbltk_6 = new JLabel("130TK");
		lbltk_6.setForeground(Color.BLACK);
		lbltk_6.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_6.setBounds(386, 218, 81, 23);
		panel_1.add(lbltk_6);

		JLabel label_59 = new JLabel("150 TK");
		label_59.setForeground(Color.BLACK);
		label_59.setHorizontalAlignment(SwingConstants.CENTER);
		label_59.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_59.setBounds(386, 250, 81, 19);
		panel_1.add(label_59);

		JLabel lblTk_41 = new JLabel("70 TK");
		lblTk_41.setForeground(Color.BLACK);
		lblTk_41.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_41.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_41.setBounds(393, 274, 81, 23);
		panel_1.add(lblTk_41);

		JLabel lblTk_42 = new JLabel("150 TK");
		lblTk_42.setForeground(Color.BLACK);
		lblTk_42.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_42.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_42.setBounds(393, 328, 81, 19);
		panel_1.add(lblTk_42);

		JLabel lblTk_43 = new JLabel("200 TK");
		lblTk_43.setForeground(Color.BLACK);
		lblTk_43.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_43.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_43.setBounds(387, 353, 87, 23);
		panel_1.add(lblTk_43);

		JLabel lblTk_44 = new JLabel("200 TK");
		lblTk_44.setForeground(Color.BLACK);
		lblTk_44.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_44.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_44.setBounds(393, 379, 81, 23);
		panel_1.add(lblTk_44);

		JLabel lblTk_45 = new JLabel("150 TK");
		lblTk_45.setForeground(Color.BLACK);
		lblTk_45.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_45.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_45.setBounds(393, 407, 81, 19);
		panel_1.add(lblTk_45);

		JLabel label_65 = new JLabel("50 TK");
		label_65.setForeground(Color.BLACK);
		label_65.setHorizontalAlignment(SwingConstants.CENTER);
		label_65.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_65.setBounds(393, 431, 81, 23);
		panel_1.add(label_65);

		JLabel lblTk_46 = new JLabel("150 TK");
		lblTk_46.setForeground(Color.BLACK);
		lblTk_46.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_46.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_46.setBounds(393, 459, 81, 19);
		panel_1.add(lblTk_46);

		JLabel lblTk_47 = new JLabel("200 TK");
		lblTk_47.setForeground(Color.BLACK);
		lblTk_47.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_47.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_47.setBounds(387, 483, 87, 23);
		panel_1.add(lblTk_47);

		JLabel lblTk_48 = new JLabel("150 TK");
		lblTk_48.setForeground(Color.BLACK);
		lblTk_48.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_48.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_48.setBounds(393, 505, 81, 23);
		panel_1.add(lblTk_48);

		JLabel label_51 = new JLabel("150 TK");
		label_51.setHorizontalAlignment(SwingConstants.CENTER);
		label_51.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_51.setBounds(296, 583, 81, 23);
		panel_1.add(label_51);

		JLabel label_53 = new JLabel("150 TK");
		label_53.setForeground(Color.BLACK);
		label_53.setHorizontalAlignment(SwingConstants.CENTER);
		label_53.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_53.setBounds(393, 533, 81, 19);
		panel_1.add(label_53);

		JLabel label_20 = new JLabel("200 TK");
		label_20.setForeground(Color.BLACK);
		label_20.setHorizontalAlignment(SwingConstants.CENTER);
		label_20.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_20.setBounds(387, 302, 87, 23);
		panel_1.add(label_20);
		
		JLabel label_13 = new JLabel("");
		label_13.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		label_13.setHorizontalAlignment(SwingConstants.CENTER);
		label_13.setBounds(0, 0, 623, 568);
		panel_1.add(label_13);

		JPanel panel_2 = new JPanel();
		tabbedPane.addTab("Dinner", null, panel_2, null);
		panel_2.setLayout(null);

		JCheckBox chckbxChickenBhuna = new JCheckBox("Chicken Bhuna.");
		chckbxChickenBhuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenBhuna.setForeground(Color.BLACK);
		chckbxChickenBhuna.setBackground(Color.WHITE);
		chckbxChickenBhuna.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 250;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxChickenBhuna.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e15) {
					e15.printStackTrace();
				}

			}
		});
		chckbxChickenBhuna.setBounds(158, 64, 185, 23);
		panel_2.add(chckbxChickenBhuna);

		JCheckBox checkBox_1 = new JCheckBox("Chicken Biriyani");
		checkBox_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox_1.setForeground(Color.BLACK);
		checkBox_1.setBackground(Color.WHITE);
		checkBox_1.setBounds(158, 110, 132, 23);
		panel_2.add(checkBox_1);

		JCheckBox chckbxChickenRezala = new JCheckBox("Chicken Rezala");
		chckbxChickenRezala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenRezala.setForeground(Color.BLACK);
		chckbxChickenRezala.setBackground(Color.WHITE);
		chckbxChickenRezala.setBounds(158, 163, 161, 23);
		panel_2.add(chckbxChickenRezala);

		JCheckBox chckbxChickenDoPiaza = new JCheckBox("Chicken Do Piaza");
		chckbxChickenDoPiaza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenDoPiaza.setForeground(Color.BLACK);
		chckbxChickenDoPiaza.setBackground(Color.WHITE);
		chckbxChickenDoPiaza.setBounds(158, 136, 132, 23);
		panel_2.add(chckbxChickenDoPiaza);

		JCheckBox chckbxChickenKorma = new JCheckBox("Chicken Korma.");
		chckbxChickenKorma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenKorma.setForeground(Color.BLACK);
		chckbxChickenKorma.setBackground(Color.WHITE);
		chckbxChickenKorma.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 300;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxChickenKorma.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e14) {
					e14.printStackTrace();
				}

			}
		});
		chckbxChickenKorma.setBounds(158, 90, 185, 23);
		panel_2.add(chckbxChickenKorma);

		JCheckBox chckbxButterChicken = new JCheckBox("Butter Chicken");
		chckbxButterChicken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxButterChicken.setForeground(Color.BLACK);
		chckbxButterChicken.setBackground(Color.WHITE);
		chckbxButterChicken.setBounds(158, 189, 142, 23);
		panel_2.add(chckbxButterChicken);

		JCheckBox chckbxShorsheChicken = new JCheckBox("Shorshe Chicken");
		chckbxShorsheChicken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxShorsheChicken.setForeground(Color.BLACK);
		chckbxShorsheChicken.setBackground(Color.WHITE);
		chckbxShorsheChicken.setBounds(158, 215, 132, 23);
		panel_2.add(chckbxShorsheChicken);

		JCheckBox chckbxChickenJhalFrazee = new JCheckBox("Chicken Jhal Frazee");
		chckbxChickenJhalFrazee.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenJhalFrazee.setForeground(Color.BLACK);
		chckbxChickenJhalFrazee.setBackground(Color.WHITE);
		chckbxChickenJhalFrazee.setBounds(158, 241, 161, 23);
		panel_2.add(chckbxChickenJhalFrazee);

		JCheckBox chckbxBeefCurry = new JCheckBox("Beef Curry.");
		chckbxBeefCurry.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefCurry.setForeground(Color.BLACK);
		chckbxBeefCurry.setBackground(Color.WHITE);
		chckbxBeefCurry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 100;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxBeefCurry.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e13) {
					e13.printStackTrace();
				}

			}
		});
		chckbxBeefCurry.setBounds(158, 267, 97, 23);
		panel_2.add(chckbxBeefCurry);

		JCheckBox chckbxBeefBhuna = new JCheckBox("Beef Bhuna");
		chckbxBeefBhuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefBhuna.setForeground(Color.BLACK);
		chckbxBeefBhuna.setBackground(Color.WHITE);
		chckbxBeefBhuna.setBounds(158, 293, 132, 23);
		panel_2.add(chckbxBeefBhuna);

		JCheckBox chckbxBeefRezala = new JCheckBox("Beef Rezala");
		chckbxBeefRezala.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefRezala.setForeground(Color.BLACK);
		chckbxBeefRezala.setBackground(Color.WHITE);
		chckbxBeefRezala.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxBeefRezala.setBounds(158, 319, 97, 23);
		panel_2.add(chckbxBeefRezala);

		JCheckBox chckbxBeefDoPiaza = new JCheckBox("Beef Do Piaza");
		chckbxBeefDoPiaza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefDoPiaza.setForeground(Color.BLACK);
		chckbxBeefDoPiaza.setBackground(Color.WHITE);
		chckbxBeefDoPiaza.setBounds(158, 372, 161, 23);
		panel_2.add(chckbxBeefDoPiaza);

		JCheckBox chckbxBeefKorma = new JCheckBox("Beef Korma");
		chckbxBeefKorma.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefKorma.setForeground(Color.BLACK);
		chckbxBeefKorma.setBackground(Color.WHITE);
		chckbxBeefKorma.setBounds(158, 345, 161, 23);
		panel_2.add(chckbxBeefKorma);

		JCheckBox chckbxHandiKabab = new JCheckBox("Handi Kabab.");
		chckbxHandiKabab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxHandiKabab.setForeground(Color.BLACK);
		chckbxHandiKabab.setBackground(Color.WHITE);
		chckbxHandiKabab.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 525;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxHandiKabab.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e12) {
					e12.printStackTrace();
				}

			}
		});
		chckbxHandiKabab.setBounds(158, 398, 132, 23);
		panel_2.add(chckbxHandiKabab);

		JCheckBox chckbxAccharBeef = new JCheckBox("Acchar Beef");
		chckbxAccharBeef.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAccharBeef.setForeground(Color.BLACK);
		chckbxAccharBeef.setBackground(Color.WHITE);
		chckbxAccharBeef.setBounds(158, 424, 97, 23);
		panel_2.add(chckbxAccharBeef);

		JCheckBox chckbxFishBhuna = new JCheckBox("Fish Bhuna");
		chckbxFishBhuna.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxFishBhuna.setForeground(Color.BLACK);
		chckbxFishBhuna.setBackground(Color.WHITE);
		chckbxFishBhuna.setBounds(158, 450, 97, 23);
		panel_2.add(chckbxFishBhuna);

		JCheckBox chckbxPrawnDoPiazza = new JCheckBox("Prawn Do piazza");
		chckbxPrawnDoPiazza.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrawnDoPiazza.setForeground(Color.BLACK);
		chckbxPrawnDoPiazza.setBackground(Color.WHITE);
		chckbxPrawnDoPiazza.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}

		});
		chckbxPrawnDoPiazza.setBounds(158, 476, 132, 23);
		panel_2.add(chckbxPrawnDoPiazza);

		JCheckBox chckbxChingriMacherMalaikari = new JCheckBox("Chingri Macher Malaikari");
		chckbxChingriMacherMalaikari.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChingriMacherMalaikari.setForeground(Color.BLACK);
		chckbxChingriMacherMalaikari.setBackground(Color.WHITE);
		chckbxChingriMacherMalaikari.setBounds(158, 503, 185, 23);
		panel_2.add(chckbxChingriMacherMalaikari);

		JCheckBox chckbxMixedVegetable = new JCheckBox("Mixed Vegetable");
		chckbxMixedVegetable.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMixedVegetable.setForeground(Color.BLACK);
		chckbxMixedVegetable.setBackground(Color.WHITE);
		chckbxMixedVegetable.setBounds(158, 535, 161, 23);
		panel_2.add(chckbxMixedVegetable);

		JCheckBox chckbxChineseStyleVegetable = new JCheckBox("Chinese Style Vegetable");
		chckbxChineseStyleVegetable.setBounds(16, 587, 185, 23);
		panel_2.add(chckbxChineseStyleVegetable);

		JLabel label_11 = new JLabel("ITEM");
		label_11.setForeground(Color.BLACK);
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setFont(new Font("Algerian", Font.BOLD, 17));
		label_11.setBounds(147, 27, 108, 30);
		panel_2.add(label_11);

		JLabel label_12 = new JLabel("PRICE");
		label_12.setForeground(Color.BLACK);
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setFont(new Font("Algerian", Font.BOLD, 17));
		label_12.setBounds(379, 27, 101, 30);
		panel_2.add(label_12);

		JLabel lblTk_49 = new JLabel("400 TK");
		lblTk_49.setForeground(Color.BLACK);
		lblTk_49.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_49.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_49.setBounds(388, 64, 81, 23);
		panel_2.add(lblTk_49);

		JLabel lblTk_50 = new JLabel("400 TK");
		lblTk_50.setForeground(Color.BLACK);
		lblTk_50.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_50.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_50.setBounds(388, 90, 81, 19);
		panel_2.add(lblTk_50);

		JLabel lblTk_51 = new JLabel("400 TK");
		lblTk_51.setForeground(Color.BLACK);
		lblTk_51.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_51.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_51.setBounds(379, 108, 87, 23);
		panel_2.add(lblTk_51);

		JLabel lblTk_52 = new JLabel("400 TK");
		lblTk_52.setForeground(Color.BLACK);
		lblTk_52.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_52.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_52.setBounds(379, 134, 81, 23);
		panel_2.add(lblTk_52);

		JLabel lblTk_53 = new JLabel("400 TK");
		lblTk_53.setForeground(Color.BLACK);
		lblTk_53.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_53.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_53.setBounds(381, 163, 81, 19);
		panel_2.add(lblTk_53);

		JLabel lblTk_54 = new JLabel("400 TK");
		lblTk_54.setForeground(Color.BLACK);
		lblTk_54.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_54.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_54.setBounds(381, 187, 81, 23);
		panel_2.add(lblTk_54);

		JLabel lblTk_55 = new JLabel("400 TK");
		lblTk_55.setForeground(Color.BLACK);
		lblTk_55.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_55.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_55.setBounds(381, 215, 81, 19);
		panel_2.add(lblTk_55);

		JLabel lblTk_56 = new JLabel("400 TK");
		lblTk_56.setForeground(Color.BLACK);
		lblTk_56.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_56.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_56.setBounds(381, 239, 81, 23);
		panel_2.add(lblTk_56);

		JLabel lblTk_57 = new JLabel("500 TK");
		lblTk_57.setForeground(Color.BLACK);
		lblTk_57.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_57.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_57.setBounds(381, 271, 81, 19);
		panel_2.add(lblTk_57);

		JLabel lblTk_58 = new JLabel("500 TK");
		lblTk_58.setForeground(Color.BLACK);
		lblTk_58.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_58.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_58.setBounds(381, 295, 81, 23);
		panel_2.add(lblTk_58);

		JLabel lblTk_59 = new JLabel("500 TK");
		lblTk_59.setForeground(Color.BLACK);
		lblTk_59.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_59.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_59.setBounds(381, 327, 81, 19);
		panel_2.add(lblTk_59);

		JLabel lblTk_60 = new JLabel("500 TK");
		lblTk_60.setForeground(Color.BLACK);
		lblTk_60.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_60.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_60.setBounds(375, 345, 87, 23);
		panel_2.add(lblTk_60);

		JLabel lblTk_61 = new JLabel("500 TK");
		lblTk_61.setForeground(Color.BLACK);
		lblTk_61.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_61.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_61.setBounds(381, 374, 81, 23);
		panel_2.add(lblTk_61);

		JLabel lblTk_62 = new JLabel("500 TK");
		lblTk_62.setForeground(Color.BLACK);
		lblTk_62.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_62.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_62.setBounds(381, 402, 81, 19);
		panel_2.add(lblTk_62);

		JLabel lblTk_63 = new JLabel("500 TK");
		lblTk_63.setForeground(Color.BLACK);
		lblTk_63.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_63.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_63.setBounds(381, 426, 81, 23);
		panel_2.add(lblTk_63);

		JLabel lblTk_64 = new JLabel("450 TK");
		lblTk_64.setForeground(Color.BLACK);
		lblTk_64.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_64.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_64.setBounds(381, 450, 81, 19);
		panel_2.add(lblTk_64);

		JLabel lblTk_65 = new JLabel("676 TK");
		lblTk_65.setForeground(Color.BLACK);
		lblTk_65.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_65.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_65.setBounds(375, 472, 87, 23);
		panel_2.add(lblTk_65);

		JLabel lblTk_66 = new JLabel("550 TK");
		lblTk_66.setForeground(Color.BLACK);
		lblTk_66.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_66.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_66.setBounds(381, 501, 81, 23);
		panel_2.add(lblTk_66);

		JLabel label_54 = new JLabel("150 TK");
		label_54.setHorizontalAlignment(SwingConstants.CENTER);
		label_54.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_54.setBounds(277, 586, 81, 23);
		panel_2.add(label_54);

		JLabel lblTk_67 = new JLabel("600 TK");
		lblTk_67.setForeground(Color.BLACK);
		lblTk_67.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_67.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_67.setBounds(381, 535, 81, 19);
		panel_2.add(lblTk_67);
		
		JLabel label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		label_1.setBounds(0, 0, 623, 589);
		panel_2.add(label_1);

		JPanel panel_3 = new JPanel();
		tabbedPane.addTab("Snacks", null, panel_3, null);
		panel_3.setLayout(null);

		JCheckBox chckbxKidsSpecialNoodles = new JCheckBox("Kids Special Noodles");
		chckbxKidsSpecialNoodles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxKidsSpecialNoodles.setForeground(Color.BLACK);
		chckbxKidsSpecialNoodles.setBackground(Color.WHITE);
		chckbxKidsSpecialNoodles.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxKidsSpecialNoodles.setBounds(156, 94, 161, 23);
		panel_3.add(chckbxKidsSpecialNoodles);

		JCheckBox chckbxSpringRoll = new JCheckBox("Spring roll");
		chckbxSpringRoll.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSpringRoll.setForeground(Color.BLACK);
		chckbxSpringRoll.setBackground(Color.WHITE);
		chckbxSpringRoll.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {

			}
		});
		chckbxSpringRoll.setBounds(156, 140, 161, 23);
		panel_3.add(chckbxSpringRoll);

		JCheckBox chckbxVegetableSingara = new JCheckBox("Vegetable Singara");
		chckbxVegetableSingara.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxVegetableSingara.setForeground(Color.BLACK);
		chckbxVegetableSingara.setBackground(Color.WHITE);
		chckbxVegetableSingara.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxVegetableSingara.setBounds(156, 193, 142, 23);
		panel_3.add(chckbxVegetableSingara);

		JCheckBox chckbxButterflyPrawn = new JCheckBox("Butterfly Prawn");
		chckbxButterflyPrawn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxButterflyPrawn.setForeground(Color.BLACK);
		chckbxButterflyPrawn.setBackground(Color.WHITE);
		chckbxButterflyPrawn.setBounds(156, 166, 142, 23);
		panel_3.add(chckbxButterflyPrawn);

		JCheckBox chckbxBeefSamosa = new JCheckBox("Beef Samosa ");
		chckbxBeefSamosa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefSamosa.setForeground(Color.BLACK);
		chckbxBeefSamosa.setBackground(Color.WHITE);
		chckbxBeefSamosa.setBounds(156, 120, 132, 23);
		panel_3.add(chckbxBeefSamosa);

		JCheckBox chckbxChickenNugget = new JCheckBox("Chicken Nugget");
		chckbxChickenNugget.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenNugget.setForeground(Color.BLACK);
		chckbxChickenNugget.setBackground(Color.WHITE);
		chckbxChickenNugget.setBounds(156, 219, 142, 23);
		panel_3.add(chckbxChickenNugget);

		JCheckBox chckbxChenKiev = new JCheckBox("Chen Kiev ");
		chckbxChenKiev.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChenKiev.setForeground(Color.BLACK);
		chckbxChenKiev.setBackground(Color.WHITE);
		chckbxChenKiev.setBounds(156, 245, 132, 23);
		panel_3.add(chckbxChenKiev);

		JCheckBox chckbxFishFinger = new JCheckBox("Fish Finger");
		chckbxFishFinger.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxFishFinger.setForeground(Color.BLACK);
		chckbxFishFinger.setBackground(Color.WHITE);
		chckbxFishFinger.setBounds(156, 271, 121, 23);
		panel_3.add(chckbxFishFinger);

		JCheckBox chckbxFrenchFries = new JCheckBox("French Fries");
		chckbxFrenchFries.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxFrenchFries.setForeground(Color.BLACK);
		chckbxFrenchFries.setBackground(Color.WHITE);
		chckbxFrenchFries.setBounds(156, 297, 132, 23);
		panel_3.add(chckbxFrenchFries);

		JCheckBox chckbxChickenCutlet = new JCheckBox("Chicken Cutlet .");
		chckbxChickenCutlet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChickenCutlet.setForeground(Color.BLACK);
		chckbxChickenCutlet.setBackground(Color.WHITE);
		chckbxChickenCutlet.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 500;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxChickenCutlet.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e11) {
					e11.printStackTrace();
				}

			}
		});
		chckbxChickenCutlet.setBounds(156, 323, 132, 23);
		panel_3.add(chckbxChickenCutlet);

		JCheckBox chckbxBeefCutlet = new JCheckBox("Beef Cutlet ");
		chckbxBeefCutlet.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeefCutlet.setForeground(Color.BLACK);
		chckbxBeefCutlet.setBackground(Color.WHITE);
		chckbxBeefCutlet.setBounds(156, 349, 97, 23);
		panel_3.add(chckbxBeefCutlet);

		JCheckBox chckbxMiniChickenShashlic = new JCheckBox("Mini Chicken Shashlic .");
		chckbxMiniChickenShashlic.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMiniChickenShashlic.setForeground(Color.BLACK);
		chckbxMiniChickenShashlic.setBackground(Color.WHITE);
		chckbxMiniChickenShashlic.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxMiniChickenShashlic.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e10) {
					e10.printStackTrace();
				}

			}
		});
		chckbxMiniChickenShashlic.setBounds(156, 402, 169, 23);
		panel_3.add(chckbxMiniChickenShashlic);

		JCheckBox chckbxPrawnOnToas = new JCheckBox("Prawn on Toas");
		chckbxPrawnOnToas.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrawnOnToas.setForeground(Color.BLACK);
		chckbxPrawnOnToas.setBackground(Color.WHITE);
		chckbxPrawnOnToas.setBounds(156, 375, 161, 23);
		panel_3.add(chckbxPrawnOnToas);

		JCheckBox chckbxFrenchToast = new JCheckBox("French Toast");
		chckbxFrenchToast.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxFrenchToast.setForeground(Color.BLACK);
		chckbxFrenchToast.setBackground(Color.WHITE);
		chckbxFrenchToast.setBounds(156, 428, 132, 23);
		panel_3.add(chckbxFrenchToast);

		JCheckBox chckbxKeemaChop = new JCheckBox("Keema Chop");
		chckbxKeemaChop.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxKeemaChop.setForeground(Color.BLACK);
		chckbxKeemaChop.setBackground(Color.WHITE);
		chckbxKeemaChop.setBounds(156, 454, 121, 23);
		panel_3.add(chckbxKeemaChop);

		JCheckBox chckbxShammiKabab = new JCheckBox("Shammi Kabab");
		chckbxShammiKabab.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxShammiKabab.setForeground(Color.BLACK);
		chckbxShammiKabab.setBackground(Color.WHITE);
		chckbxShammiKabab.setBounds(156, 480, 142, 23);
		panel_3.add(chckbxShammiKabab);

		JCheckBox chckbxPantarash = new JCheckBox("Pantarash");
		chckbxPantarash.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPantarash.setForeground(Color.BLACK);
		chckbxPantarash.setBackground(Color.WHITE);
		chckbxPantarash.setBounds(156, 506, 132, 23);
		panel_3.add(chckbxPantarash);

		JCheckBox chckbxPrawnBhunhomemadeChicken = new JCheckBox("Cheese Singara Platter");
		chckbxPrawnBhunhomemadeChicken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPrawnBhunhomemadeChicken.setForeground(Color.BLACK);
		chckbxPrawnBhunhomemadeChicken.setBackground(Color.WHITE);
		chckbxPrawnBhunhomemadeChicken.setBounds(156, 532, 240, 23);
		panel_3.add(chckbxPrawnBhunhomemadeChicken);

		JLabel label_8 = new JLabel("ITEM");
		label_8.setForeground(Color.BLACK);
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setFont(new Font("Algerian", Font.BOLD, 17));
		label_8.setBounds(151, 57, 108, 30);
		panel_3.add(label_8);

		JLabel label_9 = new JLabel("PRICE");
		label_9.setForeground(Color.BLACK);
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Algerian", Font.BOLD, 17));
		label_9.setBounds(382, 57, 101, 30);
		panel_3.add(label_9);

		JLabel label_19 = new JLabel("70 TK");
		label_19.setForeground(Color.BLACK);
		label_19.setHorizontalAlignment(SwingConstants.CENTER);
		label_19.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_19.setBounds(395, 95, 81, 23);
		panel_3.add(label_19);

		JLabel lblTk_25 = new JLabel("50 TK");
		lblTk_25.setForeground(Color.BLACK);
		lblTk_25.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_25.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_25.setBounds(395, 121, 81, 19);
		panel_3.add(lblTk_25);

		JLabel label_21 = new JLabel("70 TK");
		label_21.setForeground(Color.BLACK);
		label_21.setHorizontalAlignment(SwingConstants.CENTER);
		label_21.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_21.setBounds(389, 135, 87, 23);
		panel_3.add(label_21);

		JLabel label_22 = new JLabel("70 TK");
		label_22.setForeground(Color.BLACK);
		label_22.setHorizontalAlignment(SwingConstants.CENTER);
		label_22.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_22.setBounds(395, 162, 81, 23);
		panel_3.add(label_22);

		JLabel lbltk_2 = new JLabel("50TK");
		lbltk_2.setForeground(Color.BLACK);
		lbltk_2.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_2.setBounds(395, 190, 81, 19);
		panel_3.add(lbltk_2);

		JLabel label_24 = new JLabel("70 TK");
		label_24.setForeground(Color.BLACK);
		label_24.setHorizontalAlignment(SwingConstants.CENTER);
		label_24.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_24.setBounds(395, 214, 81, 23);
		panel_3.add(label_24);

		JLabel label_25 = new JLabel("100 TK");
		label_25.setForeground(Color.BLACK);
		label_25.setHorizontalAlignment(SwingConstants.CENTER);
		label_25.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_25.setBounds(395, 242, 81, 19);
		panel_3.add(label_25);

		JLabel lblTk_26 = new JLabel("50 TK");
		lblTk_26.setForeground(Color.BLACK);
		lblTk_26.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_26.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_26.setBounds(395, 266, 81, 23);
		panel_3.add(lblTk_26);

		JLabel label_27 = new JLabel("150 TK");
		label_27.setForeground(Color.BLACK);
		label_27.setHorizontalAlignment(SwingConstants.CENTER);
		label_27.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_27.setBounds(395, 298, 81, 19);
		panel_3.add(label_27);

		JLabel label_30 = new JLabel("250 TK");
		label_30.setForeground(Color.BLACK);
		label_30.setHorizontalAlignment(SwingConstants.CENTER);
		label_30.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_30.setBounds(395, 322, 81, 23);
		panel_3.add(label_30);

		JLabel label_31 = new JLabel("100 TK");
		label_31.setForeground(Color.BLACK);
		label_31.setHorizontalAlignment(SwingConstants.CENTER);
		label_31.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_31.setBounds(395, 354, 81, 19);
		panel_3.add(label_31);

		JLabel label_32 = new JLabel("100 TK");
		label_32.setForeground(Color.BLACK);
		label_32.setHorizontalAlignment(SwingConstants.CENTER);
		label_32.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_32.setBounds(389, 372, 87, 23);
		panel_3.add(label_32);

		JLabel label_33 = new JLabel("300 TK");
		label_33.setForeground(Color.BLACK);
		label_33.setHorizontalAlignment(SwingConstants.CENTER);
		label_33.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_33.setBounds(395, 401, 81, 23);
		panel_3.add(label_33);

		JLabel lblTk_27 = new JLabel("50 TK");
		lblTk_27.setForeground(Color.BLACK);
		lblTk_27.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_27.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_27.setBounds(395, 429, 81, 19);
		panel_3.add(lblTk_27);

		JLabel lblTk_28 = new JLabel("50 TK");
		lblTk_28.setForeground(Color.BLACK);
		lblTk_28.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_28.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_28.setBounds(395, 453, 81, 23);
		panel_3.add(lblTk_28);

		JLabel label_44 = new JLabel("100 TK");
		label_44.setForeground(Color.BLACK);
		label_44.setHorizontalAlignment(SwingConstants.CENTER);
		label_44.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_44.setBounds(395, 481, 81, 19);
		panel_3.add(label_44);

		JLabel label_45 = new JLabel("100 TK");
		label_45.setForeground(Color.BLACK);
		label_45.setHorizontalAlignment(SwingConstants.CENTER);
		label_45.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_45.setBounds(389, 503, 87, 23);
		panel_3.add(label_45);

		JLabel lblTk_29 = new JLabel("100 TK");
		lblTk_29.setForeground(Color.BLACK);
		lblTk_29.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_29.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_29.setBounds(395, 532, 81, 23);
		panel_3.add(lblTk_29);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		lblNewLabel_3.setBounds(0, 0, 623, 589);
		panel_3.add(lblNewLabel_3);

		JPanel panel_4 = new JPanel();
		tabbedPane.addTab("FastFood", null, panel_4, null);
		panel_4.setLayout(null);

		JCheckBox chckbxHomemadeChickenSandwich = new JCheckBox("Homemade Chicken Sandwich.");
		chckbxHomemadeChickenSandwich.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxHomemadeChickenSandwich.setForeground(Color.BLACK);
		chckbxHomemadeChickenSandwich.setBackground(Color.WHITE);
		chckbxHomemadeChickenSandwich.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxHomemadeChickenSandwich.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e9) {
					e9.printStackTrace();
				}

			}
		});
		chckbxHomemadeChickenSandwich.setBounds(119, 112, 204, 23);
		panel_4.add(chckbxHomemadeChickenSandwich);

		JCheckBox chckbxChefsSpecialBeef = new JCheckBox("Chef\u2019s special Beef Pizza.");
		chckbxChefsSpecialBeef.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChefsSpecialBeef.setForeground(Color.BLACK);
		chckbxChefsSpecialBeef.setBackground(Color.WHITE);
		chckbxChefsSpecialBeef.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxChefsSpecialBeef.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e8) {
					e8.printStackTrace();
				}

			}
		});
		chckbxChefsSpecialBeef.setBounds(119, 158, 223, 23);
		panel_4.add(chckbxChefsSpecialBeef);

		JCheckBox chckbxChefsSpecialShrimp = new JCheckBox("Chef\u2019s Special Shrimp Pizza");
		chckbxChefsSpecialShrimp.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChefsSpecialShrimp.setForeground(Color.BLACK);
		chckbxChefsSpecialShrimp.setBackground(Color.WHITE);
		chckbxChefsSpecialShrimp.setBounds(119, 211, 182, 23);
		panel_4.add(chckbxChefsSpecialShrimp);

		JCheckBox chckbxChefsSpecialChicken = new JCheckBox("Chef\u2019s Special Chicken Pizza");
		chckbxChefsSpecialChicken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChefsSpecialChicken.setForeground(Color.BLACK);
		chckbxChefsSpecialChicken.setBackground(Color.WHITE);
		chckbxChefsSpecialChicken.setBounds(119, 184, 204, 23);
		panel_4.add(chckbxChefsSpecialChicken);

		JCheckBox chckbxClubSandwichWith = new JCheckBox("Club Sandwich with Chicken & Vegetable");
		chckbxClubSandwichWith.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxClubSandwichWith.setForeground(Color.BLACK);
		chckbxClubSandwichWith.setBackground(Color.WHITE);
		chckbxClubSandwichWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxClubSandwichWith.setBounds(119, 138, 268, 23);
		panel_4.add(chckbxClubSandwichWith);

		JCheckBox chckbxSpecialChickenPasta = new JCheckBox("Special Chicken Pasta\t");
		chckbxSpecialChickenPasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSpecialChickenPasta.setForeground(Color.BLACK);
		chckbxSpecialChickenPasta.setBackground(Color.WHITE);
		chckbxSpecialChickenPasta.setBounds(119, 237, 204, 23);
		panel_4.add(chckbxSpecialChickenPasta);

		JCheckBox chckbxSpecialBeefPasta = new JCheckBox("Special Beef pasta");
		chckbxSpecialBeefPasta.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSpecialBeefPasta.setForeground(Color.BLACK);
		chckbxSpecialBeefPasta.setBackground(Color.WHITE);
		chckbxSpecialBeefPasta.setBounds(119, 263, 204, 23);
		panel_4.add(chckbxSpecialBeefPasta);

		JCheckBox chckbxBbqChicken = new JCheckBox("BBQ Chicken .");
		chckbxBbqChicken.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBbqChicken.setForeground(Color.BLACK);
		chckbxBbqChicken.setBackground(Color.WHITE);
		chckbxBbqChicken.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxBbqChicken.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e7) {
					e7.printStackTrace();
				}

			}
		});
		chckbxBbqChicken.setBounds(119, 289, 121, 23);
		panel_4.add(chckbxBbqChicken);

		JCheckBox checkBox_9 = new JCheckBox("French Fries");
		checkBox_9.setFont(new Font("Tahoma", Font.PLAIN, 14));
		checkBox_9.setForeground(Color.BLACK);
		checkBox_9.setBackground(Color.WHITE);
		checkBox_9.setBounds(119, 315, 108, 23);
		panel_4.add(checkBox_9);

		JLabel label_5 = new JLabel("ITEM");
		label_5.setBackground(Color.BLACK);
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Algerian", Font.BOLD, 17));
		label_5.setBounds(119, 67, 108, 30);
		panel_4.add(label_5);

		JLabel label_6 = new JLabel("PRICE");
		label_6.setBackground(Color.BLACK);
		label_6.setHorizontalAlignment(SwingConstants.CENTER);
		label_6.setFont(new Font("Algerian", Font.BOLD, 17));
		label_6.setBounds(384, 67, 101, 30);
		panel_4.add(label_6);

		JLabel lblTk_17 = new JLabel("100 TK");
		lblTk_17.setForeground(Color.BLACK);
		lblTk_17.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_17.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_17.setBounds(394, 112, 81, 23);
		panel_4.add(lblTk_17);

		JLabel lblTk_18 = new JLabel("200 TK");
		lblTk_18.setForeground(Color.BLACK);
		lblTk_18.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_18.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_18.setBounds(394, 140, 81, 19);
		panel_4.add(lblTk_18);

		JLabel lblTk_19 = new JLabel("525 TK");
		lblTk_19.setForeground(Color.BLACK);
		lblTk_19.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_19.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_19.setBounds(394, 158, 81, 23);
		panel_4.add(lblTk_19);

		JLabel lblTk_20 = new JLabel("525 TK");
		lblTk_20.setForeground(Color.BLACK);
		lblTk_20.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_20.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_20.setBounds(394, 186, 81, 19);
		panel_4.add(lblTk_20);

		JLabel lblTk_21 = new JLabel("525 TK");
		lblTk_21.setForeground(Color.BLACK);
		lblTk_21.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_21.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_21.setBounds(394, 211, 81, 23);
		panel_4.add(lblTk_21);

		JLabel lblTk_22 = new JLabel("350 TK");
		lblTk_22.setForeground(Color.BLACK);
		lblTk_22.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_22.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_22.setBounds(394, 239, 81, 19);
		panel_4.add(lblTk_22);

		JLabel lblTk_23 = new JLabel("450 TK");
		lblTk_23.setForeground(Color.BLACK);
		lblTk_23.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_23.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_23.setBounds(394, 263, 81, 23);
		panel_4.add(lblTk_23);

		JLabel lblTk_24 = new JLabel("500 TK");
		lblTk_24.setForeground(Color.BLACK);
		lblTk_24.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_24.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_24.setBounds(394, 291, 81, 19);
		panel_4.add(lblTk_24);

		JLabel label_29 = new JLabel("250 TK");
		label_29.setForeground(Color.BLACK);
		label_29.setHorizontalAlignment(SwingConstants.CENTER);
		label_29.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_29.setBounds(394, 315, 81, 23);
		panel_4.add(label_29);
		
		JLabel lblNewLabel_4 = new JLabel("");
		lblNewLabel_4.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		lblNewLabel_4.setBounds(0, 0, 623, 589);
		panel_4.add(lblNewLabel_4);

		JPanel panel_5 = new JPanel();
		tabbedPane.addTab("Drinks", null, panel_5, null);
		panel_5.setLayout(null);

		JCheckBox chckbxCocacola = new JCheckBox("Cocacola.");
		chckbxCocacola.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCocacola.setForeground(Color.BLACK);
		chckbxCocacola.setBackground(Color.WHITE);
		chckbxCocacola.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 100;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxCocacola.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e6) {
					e6.printStackTrace();
				}

			}
		});
		chckbxCocacola.setBounds(133, 147, 204, 23);
		panel_5.add(chckbxCocacola);

		JCheckBox chckbxTea = new JCheckBox("Tea.");
		chckbxTea.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxTea.setForeground(Color.BLACK);
		chckbxTea.setBackground(Color.WHITE);
		chckbxTea.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxTea.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e5) {
					e5.printStackTrace();
				}

			}
		});
		chckbxTea.setBounds(133, 71, 161, 23);
		panel_5.add(chckbxTea);

		JCheckBox chckbxMountainDew = new JCheckBox("Mountain Dew");
		chckbxMountainDew.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxMountainDew.setForeground(Color.BLACK);
		chckbxMountainDew.setBackground(Color.WHITE);
		chckbxMountainDew.setBounds(133, 225, 182, 23);
		panel_5.add(chckbxMountainDew);

		JCheckBox chckbxPepsi = new JCheckBox("Pepsi");
		chckbxPepsi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxPepsi.setForeground(Color.BLACK);
		chckbxPepsi.setBackground(Color.WHITE);
		chckbxPepsi.setBounds(133, 199, 204, 23);
		panel_5.add(chckbxPepsi);

		JCheckBox chckbxSprite = new JCheckBox("Sprite");
		chckbxSprite.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxSprite.setForeground(Color.BLACK);
		chckbxSprite.setBackground(Color.WHITE);
		chckbxSprite.setBounds(133, 173, 240, 23);
		panel_5.add(chckbxSprite);

		JCheckBox chckbxup = new JCheckBox("7 up");
		chckbxup.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxup.setForeground(Color.BLACK);
		chckbxup.setBackground(Color.WHITE);
		chckbxup.setBounds(133, 251, 142, 23);
		panel_5.add(chckbxup);

		JCheckBox chckbxHC = new JCheckBox("Hot Coffe.");
		chckbxHC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxHC.setForeground(Color.BLACK);
		chckbxHC.setBackground(Color.WHITE);
		chckbxHC.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 70;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxHC.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e4) {
					e4.printStackTrace();
				}

			}
		});
		chckbxHC.setBounds(133, 95, 132, 23);
		panel_5.add(chckbxHC);

		JCheckBox chckbxAppleHuice = new JCheckBox("Apple juice");
		chckbxAppleHuice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxAppleHuice.setForeground(Color.BLACK);
		chckbxAppleHuice.setBackground(Color.WHITE);
		chckbxAppleHuice.setBounds(133, 281, 121, 23);
		panel_5.add(chckbxAppleHuice);

		JCheckBox chckbxBeetJuice = new JCheckBox("Beet juice");
		chckbxBeetJuice.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxBeetJuice.setForeground(Color.BLACK);
		chckbxBeetJuice.setBackground(Color.WHITE);
		chckbxBeetJuice.setBounds(133, 307, 97, 23);
		panel_5.add(chckbxBeetJuice);

		JCheckBox chckbxCC = new JCheckBox("Cold Coffe");
		chckbxCC.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxCC.setForeground(Color.BLACK);
		chckbxCC.setBackground(Color.WHITE);
		chckbxCC.setBounds(133, 121, 97, 23);
		panel_5.add(chckbxCC);

		JCheckBox chckbxIzze = new JCheckBox("Izze");
		chckbxIzze.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxIzze.setForeground(Color.BLACK);
		chckbxIzze.setBackground(Color.WHITE);
		chckbxIzze.setBounds(133, 333, 97, 23);
		panel_5.add(chckbxIzze);

		JCheckBox chckbxLacchi = new JCheckBox("Lacchi");
		chckbxLacchi.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxLacchi.setForeground(Color.BLACK);
		chckbxLacchi.setBackground(Color.WHITE);
		chckbxLacchi.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxLacchi.setBounds(133, 359, 97, 23);
		panel_5.add(chckbxLacchi);

		JCheckBox chckbxTejuino = new JCheckBox("Tejuino");
		chckbxTejuino.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxTejuino.setForeground(Color.BLACK);
		chckbxTejuino.setBackground(Color.WHITE);
		chckbxTejuino.setBounds(133, 385, 97, 23);
		panel_5.add(chckbxTejuino);

		JLabel label_2 = new JLabel("ITEM");
		label_2.setForeground(Color.BLACK);
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Algerian", Font.BOLD, 17));
		label_2.setBounds(122, 24, 108, 30);
		panel_5.add(label_2);

		JLabel label_3 = new JLabel("PRICE");
		label_3.setForeground(Color.BLACK);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Algerian", Font.BOLD, 17));
		label_3.setBounds(386, 24, 101, 30);
		panel_5.add(label_3);

		JLabel lblTk_6 = new JLabel("70 TK");
		lblTk_6.setForeground(Color.BLACK);
		lblTk_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_6.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_6.setBounds(386, 69, 81, 23);
		panel_5.add(lblTk_6);

		JLabel lblTk_7 = new JLabel("70 TK");
		lblTk_7.setForeground(Color.BLACK);
		lblTk_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_7.setBounds(386, 95, 81, 19);
		panel_5.add(lblTk_7);

		JLabel lblTk_8 = new JLabel("70 TK");
		lblTk_8.setForeground(Color.BLACK);
		lblTk_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_8.setBounds(386, 120, 87, 23);
		panel_5.add(lblTk_8);

		JLabel lblTk_9 = new JLabel("70 TK");
		lblTk_9.setForeground(Color.BLACK);
		lblTk_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_9.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_9.setBounds(386, 145, 81, 23);
		panel_5.add(lblTk_9);

		JLabel lbltk_1 = new JLabel("70TK");
		lbltk_1.setForeground(Color.BLACK);
		lbltk_1.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_1.setBounds(386, 173, 81, 19);
		panel_5.add(lbltk_1);

		JLabel lblTk_10 = new JLabel("70 TK");
		lblTk_10.setForeground(Color.BLACK);
		lblTk_10.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_10.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_10.setBounds(386, 197, 81, 23);
		panel_5.add(lblTk_10);

		JLabel lblTk_11 = new JLabel("100 TK");
		lblTk_11.setForeground(Color.BLACK);
		lblTk_11.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_11.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_11.setBounds(386, 225, 81, 19);
		panel_5.add(lblTk_11);

		JLabel lblTk_12 = new JLabel("100 TK");
		lblTk_12.setForeground(Color.BLACK);
		lblTk_12.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_12.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_12.setBounds(386, 249, 81, 23);
		panel_5.add(lblTk_12);

		JLabel lblTk_13 = new JLabel("150 TK");
		lblTk_13.setForeground(Color.BLACK);
		lblTk_13.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_13.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_13.setBounds(386, 281, 81, 19);
		panel_5.add(lblTk_13);

		JLabel label_28 = new JLabel("250 TK");
		label_28.setForeground(Color.BLACK);
		label_28.setHorizontalAlignment(SwingConstants.CENTER);
		label_28.setFont(new Font("Tahoma", Font.BOLD, 14));
		label_28.setBounds(386, 305, 81, 23);
		panel_5.add(label_28);

		JLabel lblTk_14 = new JLabel("100 TK");
		lblTk_14.setForeground(Color.BLACK);
		lblTk_14.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_14.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_14.setBounds(386, 333, 81, 19);
		panel_5.add(lblTk_14);

		JLabel lblTk_15 = new JLabel("100 TK");
		lblTk_15.setForeground(Color.BLACK);
		lblTk_15.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_15.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_15.setBounds(386, 357, 87, 23);
		panel_5.add(lblTk_15);

		JLabel lblTk_16 = new JLabel("300 TK");
		lblTk_16.setForeground(Color.BLACK);
		lblTk_16.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_16.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_16.setBounds(386, 383, 81, 23);
		panel_5.add(lblTk_16);

		JCheckBox chckbxRedWine = new JCheckBox("Red Wine");
		chckbxRedWine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxRedWine.setForeground(Color.BLACK);
		chckbxRedWine.setBackground(Color.WHITE);
		chckbxRedWine.setBounds(133, 411, 97, 23);
		panel_5.add(chckbxRedWine);

		JCheckBox chckbxWhiteWine = new JCheckBox("White Wine");
		chckbxWhiteWine.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxWhiteWine.setForeground(Color.BLACK);
		chckbxWhiteWine.setBackground(Color.WHITE);
		chckbxWhiteWine.setBounds(133, 437, 97, 23);
		panel_5.add(chckbxWhiteWine);

		JCheckBox chckbxChampagne = new JCheckBox("Champagne.");
		chckbxChampagne.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxChampagne.setForeground(Color.BLACK);
		chckbxChampagne.setBackground(Color.WHITE);
		chckbxChampagne.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a = 4000;
				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxA.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		chckbxChampagne.setBounds(133, 463, 97, 23);
		panel_5.add(chckbxChampagne);

		JCheckBox chckbxTaqila = new JCheckBox("Tequila.");
		chckbxTaqila.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxTaqila.setForeground(Color.BLACK);
		chckbxTaqila.setBackground(Color.WHITE);
		chckbxTaqila.setBounds(133, 489, 97, 23);
		panel_5.add(chckbxTaqila);

		JCheckBox chckbxRabbles = new JCheckBox("Rabbles.");
		chckbxRabbles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxRabbles.setForeground(Color.BLACK);
		chckbxRabbles.setBackground(Color.WHITE);
		chckbxRabbles.setBounds(133, 514, 97, 23);
		panel_5.add(chckbxRabbles);

		JCheckBox chckbxRedBull = new JCheckBox("Red Bull.");
		chckbxRedBull.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chckbxRedBull.setForeground(Color.BLACK);
		chckbxRedBull.setBackground(Color.WHITE);
		chckbxRedBull.setBounds(133, 540, 97, 23);
		panel_5.add(chckbxRedBull);

		JLabel lblTk_70 = new JLabel("2000 TK");
		lblTk_70.setForeground(Color.BLACK);
		lblTk_70.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_70.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_70.setBounds(386, 406, 81, 23);
		panel_5.add(lblTk_70);

		JLabel lbltk_7 = new JLabel("2500TK");
		lbltk_7.setForeground(Color.BLACK);
		lbltk_7.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_7.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_7.setBounds(386, 438, 81, 19);
		panel_5.add(lbltk_7);

		JLabel lbltk_8 = new JLabel("4000TK");
		lbltk_8.setForeground(Color.BLACK);
		lbltk_8.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk_8.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk_8.setBounds(386, 462, 81, 23);
		panel_5.add(lbltk_8);

		JLabel lblTk_71 = new JLabel("3000 TK");
		lblTk_71.setForeground(Color.BLACK);
		lblTk_71.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_71.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_71.setBounds(386, 490, 81, 19);
		panel_5.add(lblTk_71);

		JLabel lblTk_72 = new JLabel("2500 TK");
		lblTk_72.setForeground(Color.BLACK);
		lblTk_72.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_72.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_72.setBounds(386, 514, 87, 23);
		panel_5.add(lblTk_72);

		JLabel lblTk_73 = new JLabel("2000 TK");
		lblTk_73.setForeground(Color.BLACK);
		lblTk_73.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_73.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_73.setBounds(386, 540, 81, 23);
		panel_5.add(lblTk_73);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		lblNewLabel_5.setBounds(0, 0, 623, 589);
		panel_5.add(lblNewLabel_5);

		JPanel panel_6 = new JPanel();
		tabbedPane.addTab("Vegetarian", null, panel_6, null);
		panel_6.setLayout(null);

		JCheckBox chckbxButternutSquashAnd = new JCheckBox("Butternut Squash and Spinach Lasagna");
		chckbxButternutSquashAnd.setForeground(Color.BLACK);
		chckbxButternutSquashAnd.setBackground(Color.WHITE);
		chckbxButternutSquashAnd.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 150;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxButternutSquashAnd.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e3) {
					e3.printStackTrace();
				}

			}
		});
		chckbxButternutSquashAnd.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxButternutSquashAnd.setBounds(134, 142, 254, 23);
		panel_6.add(chckbxButternutSquashAnd);

		JCheckBox chckbxAcornSquashWith = new JCheckBox("Acorn Squash With Wild Rice Stuffing");
		chckbxAcornSquashWith.setForeground(Color.BLACK);
		chckbxAcornSquashWith.setBackground(Color.WHITE);
		chckbxAcornSquashWith.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxAcornSquashWith.setBounds(134, 188, 254, 23);
		panel_6.add(chckbxAcornSquashWith);

		JCheckBox chckbxHasselbackButternutSquash = new JCheckBox("Hasselback Butternut Squash");
		chckbxHasselbackButternutSquash.setForeground(Color.BLACK);
		chckbxHasselbackButternutSquash.setBackground(Color.WHITE);
		chckbxHasselbackButternutSquash.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxHasselbackButternutSquash.setBounds(134, 241, 240, 23);
		panel_6.add(chckbxHasselbackButternutSquash);

		JCheckBox chckbxVeganLentilChili = new JCheckBox("Vegan Lentil Chili");
		chckbxVeganLentilChili.setForeground(Color.BLACK);
		chckbxVeganLentilChili.setBackground(Color.WHITE);
		chckbxVeganLentilChili.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxVeganLentilChili.setBounds(134, 215, 204, 23);
		panel_6.add(chckbxVeganLentilChili);

		JCheckBox chckbxLentilCakesWith = new JCheckBox("Lentil Cakes With Mint Yogurt.");
		chckbxLentilCakesWith.setForeground(Color.BLACK);
		chckbxLentilCakesWith.setBackground(Color.WHITE);
		chckbxLentilCakesWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

				int a = 300;

				try {
					String query = "INSERT INTO order_list (Food_Name,Food_Price) VALUES (?,?)";

					pst = conn.prepareStatement(query);
					pst.setString(1, chckbxLentilCakesWith.getText());
					pst.setInt(2, a);

					pst.execute();
					pst.close();

					sum = sum + a;

					// JOptionPane.showMessageDialog(null, "Thanks for ordering");
				} catch (Exception e2) {
					e2.printStackTrace();
				}

			}
		});
		chckbxLentilCakesWith.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxLentilCakesWith.setBounds(134, 168, 240, 23);
		panel_6.add(chckbxLentilCakesWith);

		JCheckBox chckbxSpicyTofuCurry = new JCheckBox("Spicy Tofu Curry");
		chckbxSpicyTofuCurry.setForeground(Color.BLACK);
		chckbxSpicyTofuCurry.setBackground(Color.WHITE);
		chckbxSpicyTofuCurry.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxSpicyTofuCurry.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxSpicyTofuCurry.setBounds(134, 267, 142, 23);
		panel_6.add(chckbxSpicyTofuCurry);

		JCheckBox chckbxAglioEOlio = new JCheckBox("Aglio E Olio with Roasted Tomatoes");
		chckbxAglioEOlio.setForeground(Color.BLACK);
		chckbxAglioEOlio.setBackground(Color.WHITE);
		chckbxAglioEOlio.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxAglioEOlio.setBounds(134, 293, 240, 23);
		panel_6.add(chckbxAglioEOlio);

		JCheckBox chckbxChickpeaAndWinter = new JCheckBox("Chickpea and Winter Vegetable Stew");
		chckbxChickpeaAndWinter.setForeground(Color.BLACK);
		chckbxChickpeaAndWinter.setBackground(Color.WHITE);
		chckbxChickpeaAndWinter.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxChickpeaAndWinter.setBounds(134, 319, 254, 23);
		panel_6.add(chckbxChickpeaAndWinter);

		JCheckBox chckbxCauliflowerSaladWith = new JCheckBox("Cauliflower Salad with Tahini Dressing");
		chckbxCauliflowerSaladWith.setForeground(Color.BLACK);
		chckbxCauliflowerSaladWith.setBackground(Color.WHITE);
		chckbxCauliflowerSaladWith.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {

			}
		});
		chckbxCauliflowerSaladWith.setFont(new Font("Tahoma", Font.BOLD, 12));
		chckbxCauliflowerSaladWith.setBounds(134, 345, 254, 23);
		panel_6.add(chckbxCauliflowerSaladWith);

		JLabel lblItem = new JLabel("ITEM");
		lblItem.setForeground(Color.BLACK);
		lblItem.setHorizontalAlignment(SwingConstants.CENTER);
		lblItem.setFont(new Font("Algerian", Font.BOLD, 17));
		lblItem.setBounds(168, 78, 108, 30);
		panel_6.add(lblItem);

		JLabel lblPrice = new JLabel("PRICE");
		lblPrice.setForeground(Color.BLACK);
		lblPrice.setFont(new Font("Algerian", Font.BOLD, 17));
		lblPrice.setHorizontalAlignment(SwingConstants.CENTER);
		lblPrice.setBounds(374, 78, 101, 30);
		panel_6.add(lblPrice);

		JLabel lblTk = new JLabel("250 TK");
		lblTk.setForeground(Color.BLACK);
		lblTk.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk.setBounds(394, 142, 81, 23);
		panel_6.add(lblTk);

		JLabel lblTk_1 = new JLabel("300 TK");
		lblTk_1.setForeground(Color.BLACK);
		lblTk_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_1.setBounds(394, 168, 81, 19);
		panel_6.add(lblTk_1);

		JLabel lblNewLabel = new JLabel("300 TK");
		lblNewLabel.setForeground(Color.BLACK);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(388, 187, 87, 23);
		panel_6.add(lblNewLabel);

		JLabel label = new JLabel("250 TK");
		label.setForeground(Color.BLACK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 14));
		label.setBounds(394, 215, 81, 23);
		panel_6.add(label);

		JLabel lbltk = new JLabel("250TK");
		lbltk.setForeground(Color.BLACK);
		lbltk.setHorizontalAlignment(SwingConstants.CENTER);
		lbltk.setFont(new Font("Tahoma", Font.BOLD, 14));
		lbltk.setBounds(394, 241, 81, 19);
		panel_6.add(lbltk);

		JLabel lblTk_2 = new JLabel("350 TK");
		lblTk_2.setForeground(Color.BLACK);
		lblTk_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_2.setBounds(394, 267, 81, 23);
		panel_6.add(lblTk_2);

		JLabel lblTk_3 = new JLabel("350 TK");
		lblTk_3.setForeground(Color.BLACK);
		lblTk_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_3.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_3.setBounds(394, 293, 81, 19);
		panel_6.add(lblTk_3);

		JLabel lblTk_4 = new JLabel("350 TK");
		lblTk_4.setForeground(Color.BLACK);
		lblTk_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_4.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_4.setBounds(394, 319, 81, 23);
		panel_6.add(lblTk_4);

		JLabel lblTk_5 = new JLabel("400 TK");
		lblTk_5.setForeground(Color.BLACK);
		lblTk_5.setHorizontalAlignment(SwingConstants.CENTER);
		lblTk_5.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblTk_5.setBounds(394, 345, 81, 19);
		panel_6.add(lblTk_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(FoodCatalouge.class.getResource("/Image/Background_six.png")));
		lblNewLabel_6.setBounds(0, 0, 623, 589);
		panel_6.add(lblNewLabel_6);

		JButton btnDone = new JButton("Done");
		btnDone.setBounds(5, 618, 185, 33);
		btnDone.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cost();
				orderList ol = new orderList();
				ol.setVisible(true);
				dispose();
			}
		});
		btnDone.setFont(new Font("Algerian", Font.PLAIN, 18));
		contentPane.add(btnDone);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(197, 617, 436, 72);
		contentPane.add(scrollPane);

		dataTable = new JTable();
		scrollPane.setViewportView(dataTable);

		idTF = new JTextField();
		idTF.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				Search();
			}
		});
		idTF.setBounds(99, 661, 64, 30);
		contentPane.add(idTF);
		idTF.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("Room ID :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setBounds(5, 661, 76, 30);
		contentPane.add(lblNewLabel_1);
		
		setUndecorated(true);
	}
}
