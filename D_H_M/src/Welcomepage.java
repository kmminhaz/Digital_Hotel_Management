import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JProgressBar;

public class Welcomepage extends JFrame implements ActionListener {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Welcomepage frame = new Welcomepage();
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
	public Welcomepage() {
		design();
		tm.start();
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
	
	Timer tm = new Timer(100, this);
	int time =0;
	private JLabel loadingLable;
	private JProgressBar progressBar;
	
	public void actionPerformed(ActionEvent e) {
		if(time != 100) {
			time++;
			progressBar.setValue(time);
			loadingLable.setText(time+"%");
		}else {
			tm.stop();
			//JOptionPane.showMessageDialog(null, "Done");
			FrontPage fp = new FrontPage();
			fp.setVisible(true);
			dispose();
		}
	}
	
	private void design(){
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 471, 372);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcome = new JLabel("Digital Hotel Management");
		lblWelcome.setForeground(new Color(255, 250, 240));
		lblWelcome.setFont(new Font("Algerian", Font.BOLD, 32));
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		lblWelcome.setBounds(0, 0, 471, 63);
		contentPane.add(lblWelcome);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Welcomepage.class.getResource("/Image/giphy.gif")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 471, 362);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 0, 471, 293);
		contentPane.add(lblNewLabel_1);
		
		loadingLable = new JLabel("%");
		loadingLable.setFont(new Font("Arial Black", Font.BOLD | Font.ITALIC, 9));
		loadingLable.setForeground(new Color(0, 255, 0));
		loadingLable.setHorizontalAlignment(SwingConstants.CENTER);
		loadingLable.setBounds(208, 361, 37, 11);
		contentPane.add(loadingLable);
		
		progressBar = new JProgressBar();
		progressBar.setBackground(Color.GRAY);
		progressBar.setForeground(new Color(0, 0, 139));
		progressBar.setBounds(0, 361, 471, 11);
		contentPane.add(progressBar);
		
		setUndecorated(true);
	}
}
