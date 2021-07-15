package login;

import Tabs.Admin_Functions;
import customerpannel.NewAccount;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.PreparedStatement;
import Conenctivity.DBconnection;
import java.awt.Color;
import javax.swing.JPasswordField;
import java.awt.Font;
import java.awt.Image;
import java.awt.SystemColor;


public class LoginFrontEnd extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	JTextField tlog;
	JLabel Llog, Lpwd;
	JButton Jlogin;
	private JPasswordField passwordField;
	private JLabel lblNewLabel;

	public LoginFrontEnd() {
		setTitle("login panel");
		getContentPane().setBackground(SystemColor.desktop);
		setBackground(new Color(153, 102, 153));
		Llog = new JLabel("Username");
		Llog.setForeground(Color.WHITE);
		Llog.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		Llog.setBackground(new Color(255, 255, 240));
		Llog.setBounds(167, 200, 73, 20);
		Lpwd = new JLabel("Password");
		Lpwd.setForeground(SystemColor.text);
		Lpwd.setFont(new Font("Segoe UI Historic", Font.BOLD, 14));
		Lpwd.setBounds(167, 231, 73, 20);
		tlog = new JTextField();
		tlog.setBounds(250, 200, 120, 20);

		Jlogin = new JButton("Login");
	
		Jlogin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		Jlogin.setForeground(new Color(0, 0, 0));
		Jlogin.setBounds(221, 283, 80, 20);
		getContentPane().setLayout(null);
		
		getContentPane().add(Llog);
		getContentPane().add(Lpwd);
		getContentPane().add(tlog);
		getContentPane().add(Jlogin);
		Jlogin.addActionListener(this);
		
		passwordField = new JPasswordField();
		passwordField.setEchoChar('*');
		passwordField.setBounds(250, 231, 120, 20);
		getContentPane().add(passwordField);
		
		lblNewLabel = new JLabel("BANK Management System");
		lblNewLabel.setForeground(new Color(245, 245, 220));
		lblNewLabel.setFont(new Font("Rockwell Condensed", Font.PLAIN, 25));
		lblNewLabel.setBounds(88, 11, 239, 72);
		getContentPane().add(lblNewLabel);
		
		JLabel logo_lbl = new JLabel("logo");
		logo_lbl.setForeground(new Color(240, 255, 255));
		logo_lbl.setBounds(10, 11, 80, 64);
		getContentPane().add(logo_lbl);
		
		BufferedImage img1 = null;
		try {
		    img1 = ImageIO.read(new File("src/imges/Final_logo.jpg"));
		} catch (Exception e) {
		    e.printStackTrace();
		}
		Image rimg = img1.getScaledInstance(80, 80, Image.SCALE_SMOOTH);
		ImageIcon logo = new ImageIcon(rimg);
		logo_lbl.setIcon(logo);
		
	}
	@Override
		public void actionPerformed(ActionEvent e) {
		if (tlog.getText().trim().equals("") || passwordField.getPassword().length == 0 ) {
			JOptionPane.showMessageDialog(this, "Please fill user and password");
		} else {
			try {
				
				String pass = new String(passwordField.getPassword());
				Connection con = new DBconnection().getConnection();
				String Query = "select * from login where user=? and pwd= ? ";
				PreparedStatement st = con.prepareStatement(Query);
				st.setString(1, tlog.getText());
				st.setString(2, pass);

				ResultSet rs = st.executeQuery();

				int count = 0;

				if (rs.next()) {
					count = +1;
					String post = rs.getString("position");

					if (post.equals("admin")) {
						Admin_Functions af = new Admin_Functions();
						af.setVisible(true);
						af.setBounds(500, 200, 800, 550);
						setVisible(false);

					} else if (post.equals("emp")) {
						NewAccount Emp_tab = new NewAccount();
						Emp_tab.setVisible(true);
						Emp_tab.setBounds(500, 200, 900, 550);
						setVisible(false);
					}
				} else if (count > 1) {
					JOptionPane.showMessageDialog(this, "Duplicate id password");
				} else {
					JOptionPane.showMessageDialog(this, "Invalid User/Password");
					}
			} catch (Exception e1) {
				e1.getStackTrace();		
			}	
		}
	}

	public static void main(String args[]) {

		LoginFrontEnd frame = new LoginFrontEnd();
		frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);
		frame.setSize(600, 500);
		frame.setLocation(600, 200);

		System.out.println("Frame starts......");
	}
}
