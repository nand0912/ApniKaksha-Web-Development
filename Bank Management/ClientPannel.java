package customerpannel;
import login.*;
import Conenctivity.*;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.text.spi.DateFormatProvider;
import java.util.Locale;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import com.toedter.calendar.JDateChooser;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.GraphicsEnvironment;
import javax.swing.UIManager;

public class NewAccount extends JFrame {
	private JTextField taccount;
	private JTextField tFname;
	private JTextField tLname;
	private JTextField tpin;
	private JTextField srch_account;
	private JTextField tdeposite;
	private JTextField srch_account1;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField tcontact;
	private JTextField taddress;
	private JTextField regName;
	private JTextField regAccType;
	private JTextField to_deposite;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					NewAccount frame =new NewAccount();
					frame.setVisible(true);
					frame.setBounds(500, 200, 880, 550);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	 public String[] getAllCountries() {
	    String[] countries = new String[Locale.getISOCountries().length];
	    String[] countryCodes = Locale.getISOCountries();
	    for (int i = 0; i < countryCodes.length; i++) {
	        Locale obj = new Locale("", countryCodes[i]);
	        countries[i] = obj.getDisplayCountry();
	    }
	    return countries ;
	 }

	
	public String getAlphaNumericString() {

		String AlphaNumericString = "0123456789" ;

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
			
		}
		String add = "202";
		String rand = sb.toString();
		String final_Accno = add+rand;
		return final_Accno;
	}
	
	public NewAccount() {
		setBackground(UIManager.getColor("CheckBox.interiorBackground"));
		getContentPane().setBackground(UIManager.getColor("Button.highlight"));
		setResizable(false);
		setTitle("Employee Panel");
		getContentPane().setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBackground(UIManager.getColor("Button.disabledShadow"));
		tabbedPane.setBounds(33, 60, 799, 377);
		getContentPane().add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("New Account", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Acc No.");
		lblNewLabel.setBounds(10, 39, 72, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("First Name");
		lblNewLabel_1.setBounds(10, 84, 72, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Last Name");
		lblNewLabel_2.setBounds(10, 126, 72, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("PIN");
		lblNewLabel_3.setBounds(440, 39, 46, 14);
		panel.add(lblNewLabel_3);

		taccount = new JTextField();
		taccount.setEditable(false);
		taccount.setBounds(92, 36, 160, 20);
		panel.add(taccount);
		taccount.setColumns(10);
		
		tFname = new JTextField();
		tFname.setBounds(92, 81, 160, 20);
		panel.add(tFname);
		tFname.setColumns(10);

		tLname = new JTextField();
		tLname.setBounds(92, 123, 160, 20);
		panel.add(tLname);
		tLname.setColumns(10);

		tpin = new JTextField();
		tpin.setBounds(529, 36, 172, 20);
		panel.add(tpin);
		tpin.setColumns(10);

		JLabel lblNewLabel_14 = new JLabel("Contact");
		lblNewLabel_14.setBounds(10, 168, 46, 14);
		panel.add(lblNewLabel_14);

		tcontact = new JTextField();
		tcontact.setBounds(92, 165, 160, 20);
		panel.add(tcontact);
		tcontact.setColumns(10);

		JLabel lblNewLabel_15 = new JLabel("Address");
		lblNewLabel_15.setBounds(10, 208, 61, 14);
		panel.add(lblNewLabel_15);

		taddress = new JTextField();
		taddress.setBounds(92, 205, 160, 20);
		panel.add(taddress);
		taddress.setColumns(10);

		JLabel lblNewLabel_4 = new JLabel("Gender");
		lblNewLabel_4.setBounds(440, 211, 46, 14);
		panel.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel("Nationality");
		lblNewLabel_5.setBounds(440, 84, 66, 14);
		panel.add(lblNewLabel_5);

		JLabel lblNewLabel_6 = new JLabel("Date of Birth");
		lblNewLabel_6.setBounds(440, 126, 79, 14);
		panel.add(lblNewLabel_6);

		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(529, 123, 163, 20);
		panel.add(dateChooser);

		JLabel lblNewLabel_7 = new JLabel("Account Type");
		lblNewLabel_7.setBounds(440, 168, 85, 14);
		panel.add(lblNewLabel_7);

		JComboBox comboBox = new JComboBox(getAllCountries());
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		comboBox.setBounds(529, 80, 172, 22);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox();
		comboBox_1.setModel(new DefaultComboBoxModel(new String[] {"Savings", "Current ", "Retirement", "Fixed Deposit  ", "NRI"}));
		comboBox_1.setBounds(529, 164, 172, 22);
		panel.add(comboBox_1);
		
		
		JComboBox comboBox_2 = new JComboBox();
		comboBox_2.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Others"}));
		comboBox_2.setBounds(529, 207, 72, 22);
		panel.add(comboBox_2);
		
		
		String Acc = getAlphaNumericString();
		taccount.setText(Acc);

		JButton btnNewButton = new JButton("Submit");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				DateFormat df = new SimpleDateFormat();
				String date = df.format(dateChooser.getDate());
				String fname = tFname.getText();
				String lname = tLname.getText();
				String address = taddress.getText();
				String contact = tcontact.getText();
				String pin = tpin.getText();
				String aNO = taccount.getText();

				Connection con = new DBconnection().getConnection();
				String updatecombo = comboBox.getSelectedItem().toString();
				String comboaccType = comboBox_1.getSelectedItem().toString();
				String comboGen = comboBox_2.getSelectedItem().toString();
				
				Date date1 = new Date();
				Date sqlDate = new Date(date1.getTime()); 
			    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
			    String formatedDate = formatter.format(sqlDate); 

				String Query = "insert into customer(accountNumber, first_name, last_name, contact, address, pin, nationality, DOB, accType, gender, Balance,Date)"
						+ "values( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

				if ( fname.trim().equals("") || lname.trim().equals("")
						|| address.trim().equals("") || contact.trim().equals("") || pin.trim().equals("")) {
					JOptionPane.showMessageDialog(panel, "Please fill Details", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					try {

						PreparedStatement ps = con.prepareStatement(Query);
						ps.setString(1, aNO);
						ps.setString(2, fname);
						ps.setString(3, lname);
						ps.setString(4, contact);
						ps.setString(5, address);
						ps.setString(6, pin);
						ps.setString(7, updatecombo);
						ps.setString(8, date);
						ps.setString(9, comboaccType);
						ps.setString(10, comboGen);
						ps.setInt(11, 0);
						ps.setString(12, formatedDate);
			
						ps.execute();
						JOptionPane.showMessageDialog(panel, "Successful");
						
						taccount.setText("");
						tFname.setText("");
						tLname.setText("");
						taddress.setText("");
						tcontact.setText("");
						tpin.setText("");
					
						
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		btnNewButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 13));
		btnNewButton.setBounds(299, 293, 89, 37);
		panel.add(btnNewButton);
		
		JLabel lblNewLabel_20 = new JLabel("* Note Account Number Before Submission");
		lblNewLabel_20.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblNewLabel_20.setBounds(77, 305, 212, 14);
		panel.add(lblNewLabel_20);

//--------------------------------------------------------------------------------------------//
//DEPOSITE//

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Deposite", null, panel_1, null);
		panel_1.setLayout(null);

		JLabel lblNewLabel_8 = new JLabel("Account Number");
		lblNewLabel_8.setBounds(194, 36, 100, 14);
		panel_1.add(lblNewLabel_8);

		srch_account = new JTextField();
		srch_account.setBounds(327, 33, 152, 20);
		panel_1.add(srch_account);
		srch_account.setColumns(10);

		JLabel lblNewLabel_9 = new JLabel("Deposite Amount");
		lblNewLabel_9.setBounds(194, 233, 100, 14);
		panel_1.add(lblNewLabel_9);

		tdeposite = new JTextField();
		tdeposite.setEditable(false);
		tdeposite.setBounds(327, 179, 152, 20);
		panel_1.add(tdeposite);
		tdeposite.setColumns(10);

		JLabel lblNewLabel_16 = new JLabel("Balance");
		lblNewLabel_16.setBounds(194, 182, 82, 14);
		panel_1.add(lblNewLabel_16);

		JLabel lblNewLabel_17 = new JLabel("Name");
		lblNewLabel_17.setBounds(194, 86, 100, 14);
		panel_1.add(lblNewLabel_17);

		regName = new JTextField();
		regName.setEditable(false);
		regName.setBounds(327, 83, 152, 20);
		panel_1.add(regName);
		regName.setColumns(10);

		regAccType = new JTextField();
		regAccType.setEditable(false);
		regAccType.setBounds(327, 129, 152, 20);
		panel_1.add(regAccType);
		regAccType.setColumns(10);

		JLabel lblNewLabel_18 = new JLabel("Account Type");
		lblNewLabel_18.setBounds(194, 132, 100, 14);
		panel_1.add(lblNewLabel_18);

		to_deposite = new JTextField();
		to_deposite.setBounds(327, 228, 152, 25);
		panel_1.add(to_deposite);
		to_deposite.setColumns(10);

		JButton btnDeposite = new JButton("Deposite");
		btnDeposite.setBounds(501, 224, 100, 33);
		panel_1.add(btnDeposite);
		
		btnDeposite.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String account1 = srch_account.getText();
				
				if (account1.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Details", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Connection con = new DBconnection().getConnection();

						String value1 = tdeposite.getText();
						String balance = to_deposite.getText();
						int sum = Integer.parseInt(value1) + Integer.parseInt(balance);
						String sum1 = String.valueOf(sum);
						System.out.println(sum1);
						

						PreparedStatement ps = con.prepareStatement("update customer set Balance = '" + sum1 + "' where accountnumber = ? ");
						ps.setString(1, account1);
						ps.execute();
						JOptionPane.showMessageDialog(null, "SuccessFully Deposited");
												
						java.util.Date date=new java.util.Date();
						java.sql.Date sqlDate=new java.sql.Date(date.getTime()); 
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
					    String format = formatter.format(sqlDate);  
					    
//this query will add daily transcations.

						PreparedStatement ps1 = con.prepareStatement("insert into transactions (AccNO, Amount, transactionMode, Date) values(?, ?, ?, ?)");
						ps1.setNString(1, srch_account.getText());
						ps1.setString(2, to_deposite.getText());
						ps1.setString(3, "Deposit");
						ps1.setString(4,format);
						
						ps1.execute();
						
						srch_account.setText("");
						tdeposite.setText("");
						regAccType.setText("");
						regName.setText("");
						to_deposite.setText("");

					} catch (Exception e1) {
						e1.getStackTrace();
					}
				}
			}
		});

		JButton btnSearch = new JButton("Search");
		btnSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String account = srch_account.getText();
				
				if (account.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Details", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Connection con = new DBconnection().getConnection();
						String sql = "select * from customer where accountNumber =? ";
						PreparedStatement stmt1 = con.prepareStatement(sql);
						
						stmt1.setString(1, account);
						ResultSet rs = stmt1.executeQuery();
	
						if (rs.next()) {
	
							String add_name = rs.getString("first_name");
							String add_name2 = rs.getString("last_name");
							String printname = add_name + " " + add_name2;
							regName.setText(printname);
	
							String acc_Type = rs.getString("accType");
							regAccType.setText(acc_Type);
	
							String add_Balance = rs.getString("Balance");
							tdeposite.setText(add_Balance);
					
						}else {
							JOptionPane.showMessageDialog(null, "Invalid Account No. ");
						}
				
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}
			
			}
		});
		btnSearch.setBounds(501, 32, 89, 23);
		panel_1.add(btnSearch);

// -----------------------------------------------------------//
// WITHDRAWL

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Withdrawl", null, panel_2, null);
		panel_2.setLayout(null);

		JLabel lblNewLabel_10 = new JLabel("Account Number");
		lblNewLabel_10.setBounds(189, 37, 105, 14);
		panel_2.add(lblNewLabel_10);

		srch_account1 = new JTextField();
		srch_account1.setBounds(315, 34, 169, 20);
		panel_2.add(srch_account1);
		srch_account1.setColumns(10);

		JLabel lblNewLabel_11 = new JLabel("Withdrawl Amount");
		lblNewLabel_11.setBounds(189, 176, 105, 25);
		panel_2.add(lblNewLabel_11);

		JLabel lblNewLabel_12 = new JLabel("Balance");
		lblNewLabel_12.setBounds(189, 133, 85, 14);
		panel_2.add(lblNewLabel_12);

		textField_7 = new JTextField();
		textField_7.setEditable(false);
		textField_7.setBounds(315, 130, 169, 20);
		panel_2.add(textField_7);
		textField_7.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setEditable(false);
		textField_8.setBounds(315, 82, 169, 20);
		panel_2.add(textField_8);
		textField_8.setColumns(10);

		JLabel lblNewLabel_13 = new JLabel("Name");
		lblNewLabel_13.setBounds(189, 85, 46, 14);
		panel_2.add(lblNewLabel_13);

		textField_9 = new JTextField();
		textField_9.setBounds(315, 173, 169, 28);
		panel_2.add(textField_9);
		textField_9.setColumns(10);

		JButton btnNewButton_1 = new JButton("Withdraw");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String account2 = srch_account1.getText();
				
				if (account2.trim().equals("")) {
					JOptionPane.showMessageDialog(null, "Please fill Details", "Alert", JOptionPane.WARNING_MESSAGE);
				} else {
					try {
						Connection con1 = new DBconnection().getConnection();
						String value1 = textField_7.getText();
						String balance = textField_9.getText();
						int diff = Integer.parseInt(value1) - Integer.parseInt(balance);
						String calculatedDiff = String.valueOf(diff);
						
						java.util.Date date=new java.util.Date();
						java.sql.Date sqlDate=new java.sql.Date(date.getTime()); 
					    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
					    String format = formatter.format(sqlDate);  
	
						PreparedStatement ps = con1.prepareStatement("update customer set Balance = '" + calculatedDiff + "' where accountnumber = ? ");
						ps.setString(1, account2);
						
						if(Integer.parseInt(balance) > Integer.parseInt(value1) ){
							JOptionPane.showMessageDialog(null, "Can't Withdraw due to insufficient Balance");
							srch_account1.setText("");
							textField_7.setText("");
							textField_9.setText("");
							textField_8.setText("");
						}else {
							ps.execute();
							JOptionPane.showMessageDialog(null, "SuccessFully Withdrawl");
							
							PreparedStatement ps1 = con1.prepareStatement("insert into transactions (AccNO, Amount, transactionMode, Date) values(?, ?, ?, ?)");
							ps1.setString(1, srch_account1.getText());
							ps1.setString(3, "Withdrawn");
							ps1.setString(2,balance);
							ps1.setNString(4, format);
							ps1.execute();	
							
							srch_account1.setText("");
							textField_7.setText("");
							textField_9.setText("");
							textField_8.setText("");
						}

					} catch (Exception e1) {
						e1.getStackTrace();
					}
				}
			}
		});
		btnNewButton_1.setBounds(514, 172, 94, 29);
		panel_2.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Search");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				Connection con = new DBconnection().getConnection();
				try {
					String sql = "select * from customer where accountNumber =? ";
					PreparedStatement stmt2 = con.prepareStatement(sql);
					String account = srch_account1.getText();
					stmt2.setString(1, account);

					ResultSet rs = stmt2.executeQuery();

					while (rs.next()) {
						
						btnNewButton_1.setEnabled(true);
					
						String add_name = rs.getString("first_name");
						String add_name2 = rs.getString("last_name");
						String printname = add_name + " " + add_name2;
						textField_8.setText(printname);

						String add_Balance = rs.getString("Balance");
						System.out.println(add_Balance);
						textField_7.setText(add_Balance);

						if (Integer.parseInt(add_Balance) < 200) {
							String printMsg = "Cannot Withdraw" + "\n" + "Balance reached it's Minimunm Limit";
							JOptionPane.showMessageDialog(null, printMsg);
							btnNewButton_1.setEnabled(false);
							srch_account1.setText("");
							textField_7.setText("");
							textField_9.setText("");
							textField_8.setText("");
						}
					}
				} catch (Exception e1) {
					e1.getMessage();
				}
			}
		});
		btnNewButton_2.setBounds(514, 33, 77, 23);
		panel_2.add(btnNewButton_2);
		
		JButton btnLogout = new JButton("Logout");
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				LoginFrontEnd log = new LoginFrontEnd();
				log.setVisible(true);
				log.setLocationRelativeTo(null);
				log.setBounds(500, 200, 600, 550);
			}
		});
		btnLogout.setBounds(718, 26, 89, 23);
		getContentPane().add(btnLogout);
		JLabel lblNewLabel_19 = new JLabel("Welcome To Employee Panel");
		lblNewLabel_19.setBackground(new Color(0, 0, 0));
		lblNewLabel_19.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel_19.setForeground(UIManager.getColor("Button.focus"));
		lblNewLabel_19.setBounds(249, 11, 375, 38);
		getContentPane().add(lblNewLabel_19);

	}
}
