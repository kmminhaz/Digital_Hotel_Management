import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrontPage extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPage frame = new FrontPage();
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
	public FrontPage() {
		setResizable(false);
		design();
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
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 692, 330);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(65, 105, 225));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setIcon(new ImageIcon(FrontPage.class.getResource("/Image/adminR-icon.png")));
		lblNewLabel.setBounds(72, 95, 138, 143);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(FrontPage.class.getResource("/Image/Rec.(1).png")));
		lblNewLabel_1.setBounds(275, 95, 138, 128);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setIcon(new ImageIcon(FrontPage.class.getResource("/Image/administrator-icon.png")));
		lblNewLabel_2.setBounds(476, 95, 129, 128);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblUseAs = new JLabel("Use As");
		lblUseAs.setForeground(Color.WHITE);
		lblUseAs.setHorizontalAlignment(SwingConstants.CENTER);
		lblUseAs.setFont(new Font("Algerian", Font.BOLD, 18));
		lblUseAs.setBounds(299, 23, 83, 14);
		contentPane.add(lblUseAs);
		
		JButton btnManager = new JButton("Manager");
		btnManager.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginManager lm = new loginManager();
				lm.setVisible(true);
				dispose();
			}
		});
		btnManager.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnManager.setBounds(72, 235, 138, 44);
		contentPane.add(btnManager);
		
		JButton btnReciption = new JButton("Reception");
		btnReciption.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginReception lr = new loginReception();
				lr.setVisible(true);
				dispose();
			}
		});
		btnReciption.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnReciption.setBounds(275, 233, 138, 44);
		contentPane.add(btnReciption);
		
		JButton btnGuest = new JButton("Guest");
		btnGuest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				loginGuest lg = new loginGuest();
				lg.setVisible(true);
				dispose();
			}
		});
		btnGuest.setFont(new Font("Tahoma", Font.BOLD, 18));
		btnGuest.setBounds(476, 233, 129, 44);
		contentPane.add(btnGuest);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(FrontPage.class.getResource("/Image/Tablet.jpg")));
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(0, 0, 692, 335);
		contentPane.add(label);
		
		JButton button = new JButton("");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		button.setIcon(new ImageIcon(FrontPage.class.getResource("/Image/Actions-file-close-icon.png")));
		button.setBounds(645, 0, 47, 38);
		contentPane.add(button);
		
		setUndecorated(true);
	}
}
