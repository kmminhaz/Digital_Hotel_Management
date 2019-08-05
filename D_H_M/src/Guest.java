import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class Guest extends JFrame {

	private JPanel contentPane;
	private JTextField helpTF;
	
	Connection conn = null;
	PreparedStatement pst = null;
	ResultSet rs = null;
	private JTextField idTF;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Guest frame = new Guest();
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
	public Guest() {
		design();
		loginGuest lg = new loginGuest();
		idTF.setText(lg.rid);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(Guest.class.getResource("/Image/download ).jpg")));
		label.setBounds(0, 0, 481, 331);
		contentPane.add(label);
		conn = SQLConnection.ConnecrDb();
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
	
	private void Help() {
		try {
			String query = "INSERT INTO help (Room_ID,Help) VALUES (?,?)";
			
			pst = conn.prepareStatement(query);
			pst.setString(1, idTF.getText());
			pst.setString(2, helpTF.getText());
			
			pst.execute();
			pst.close();
			
			JOptionPane.showMessageDialog(null, "We will try our best to solve your problem");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 481, 331);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton = new JButton("My Account");
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(Color.BLACK);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				My_Account ma = new My_Account();
				ma.setVisible(true);
				dispose();
			}
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnNewButton.setBounds(62, 90, 339, 42);
		contentPane.add(btnNewButton);
		
		JButton btnOrder = new JButton("Order");
		btnOrder.setForeground(Color.WHITE);
		btnOrder.setBackground(Color.BLACK);
		btnOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FoodCatalouge o = new FoodCatalouge();
				o.setVisible(true);
				dispose();
			}
		});
		btnOrder.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnOrder.setBounds(62, 170, 339, 42);
		contentPane.add(btnOrder);
		
		helpTF = new JTextField();
		helpTF.setForeground(SystemColor.activeCaptionText);
		helpTF.setBackground(SystemColor.control);
		helpTF.setFont(new Font("Tahoma", Font.PLAIN, 18));
		helpTF.setHorizontalAlignment(SwingConstants.CENTER);
		helpTF.setText("Any Help ??");
		helpTF.setBounds(0, 245, 244, 42);
		contentPane.add(helpTF);
		helpTF.setColumns(10);
		
		JButton btnBack = new JButton("Log Out");
		btnBack.setForeground(SystemColor.activeCaptionText);
		btnBack.setBackground(SystemColor.desktop);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginGuest lg = new loginGuest();
				lg.setVisible(true);
				dispose();
			}
		});
		btnBack.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnBack.setBounds(356, 302, 125, 29);
		contentPane.add(btnBack);
		
		JButton btnSend = new JButton("Send");
		btnSend.setForeground(SystemColor.activeCaptionText);
		btnSend.setBackground(SystemColor.desktop);
		btnSend.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Help();
			}
		});
		btnSend.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnSend.setBounds(265, 246, 87, 42);
		contentPane.add(btnSend);
		
		idTF = new JTextField();
		idTF.setForeground(SystemColor.activeCaptionText);
		idTF.setBackground(SystemColor.control);
		idTF.setBounds(265, 13, 63, 42);
		contentPane.add(idTF);
		idTF.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Room ID :");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 15));
		lblNewLabel.setBounds(167, 10, 87, 42);
		contentPane.add(lblNewLabel);
		
		setUndecorated(true);
	}
}
