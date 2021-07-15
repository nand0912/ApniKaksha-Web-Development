package Tabs;

import login.LoginFrontEnd;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.swing.JTabbedPane;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;

import java.awt.event.ActionListener;
import java.sql.Connection;
import java.awt.event.ActionEvent;
import Conenctivity.DBconnection;
import javax.swing.JTable;
import java.awt.Color;
import javax.swing.ButtonGroup;
import javax.swing.UIManager;

public class Admin_Functions extends JFrame {

	private JPanel contentPane;
	private JTextField tfname;
	private JTextField taddress;
	private JTextField tcontact;
	Connection con = null;
	JTable table = null;
	private JTextField total_amount_credited;
	private JTextField total_amount_withdrawl;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Admin_Functions frame = new Admin_Functions();
					frame.setVisible(true);
					frame.setBounds(500, 200, 850, 550);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public String getdate() {
	    Date date1 = new Date();
		Date sqlDate = new Date(date1.getTime()); 
	    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");  
	    String formatedDate = formatter.format(sqlDate); 
	    
		return formatedDate;
	}

	public String getAlphaNumericString() {

		String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	public String getUsername() {

		String AlphaNumericString = "1234567890"+"abcdef";

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i <4 ; i++) {
			int index = (int) (AlphaNumericString.length() * Math.random());

			sb.append(AlphaNumericString.charAt(index));
		}	
		String a = sb.toString();
		String b = "BNK";
		String concatenate = b+a;	
		return concatenate;
		
	}
	

	public Admin_Functions() {
		setTitle("Admin Panel");
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 880, 534);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.highlight"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(53, 61, 734, 386);
		contentPane.add(tabbedPane);

		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 255));
		panel.setBackground(new Color(230, 230, 250));
		tabbedPane.addTab("Add New Employee", null, panel, null);
		panel.setLayout(null);

		JLabel lblNewLabel = new JLabel("Full Name");
		lblNewLabel.setBounds(208, 67, 63, 14);
		panel.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Address");
		lblNewLabel_1.setBounds(208, 103, 63, 14);
		panel.add(lblNewLabel_1);

		JLabel lblNewLabel_2 = new JLabel("Contact");
		lblNewLabel_2.setBounds(208, 144, 63, 14);
		panel.add(lblNewLabel_2);

		JLabel lblNewLabel_3 = new JLabel("Gender");
		lblNewLabel_3.setBounds(208, 190, 63, 14);
		panel.add(lblNewLabel_3);

		tfname = new JTextField();
		tfname.setBounds(311, 64, 168, 20);
		panel.add(tfname);
		tfname.setColumns(10);

		taddress = new JTextField();
		taddress.setBounds(311, 100, 168, 20);
		panel.add(taddress);
		taddress.setColumns(10);

		tcontact = new JTextField();
		tcontact.setBounds(311, 141, 168, 20);
		panel.add(tcontact);
		tcontact.setColumns(10);

		JRadioButton MRadiobtn = new JRadioButton("Male");
		MRadiobtn.setBackground(new Color(230, 230, 250));
		MRadiobtn.setBounds(311, 186, 81, 23);
		panel.add(MRadiobtn);

		JRadioButton FRadiobtn = new JRadioButton("Female");
		FRadiobtn.setBackground(new Color(230, 230, 250));
		FRadiobtn.setBounds(394, 186, 85, 23);
		panel.add(FRadiobtn);
		
		ButtonGroup G = new ButtonGroup();
		G.add(MRadiobtn);
		G.add(FRadiobtn);

		JButton btnNewButton = new JButton("Create");
		btnNewButton.setFont(new Font("Segoe UI Historic", Font.PLAIN, 12));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String gender = "female";
				if (MRadiobtn.isSelected()) {
					gender = "male";
				}
				
				if ((MRadiobtn.isSelected()==false && FRadiobtn.isSelected()==false)) {
					JOptionPane.showMessageDialog(null, "Please Select 1 Option");
				}else {
					
					con = new DBconnection().getConnection();

					String getname = tfname.getText();
					String getaddress = taddress.getText();
					String getcontact = tcontact.getText();

					String query = "INSERT INTO employees(emp_code, name, address, contact, Gender) VALUES (?, ?, ?, ?,'"+ gender + "')";

					if (getname.trim().equals("") || getaddress.trim().equals("") || getcontact.trim().equals("")) {
						JOptionPane.showMessageDialog(panel, "Please fill Details", "Alert",
								JOptionPane.WARNING_MESSAGE);
					} else
						try {
							int emp_code = 1;
							Statement st2 = con.createStatement();
							ResultSet rs1 = st2.executeQuery("select max(cast(emp_code as int)) as code from employees ");
							if (rs1.next()) {
								emp_code = rs1.getInt("code") + 1;

							}
							String emp_code2 = String.format("%05d", emp_code);

							PreparedStatement st = con.prepareStatement(query);

							System.out.println("statement connncection established");
							st.setString(1, emp_code2);
							st.setString(2, getname);
							st.setString(3, getaddress);
							st.setString(4, getcontact);

							JOptionPane.showMessageDialog(null, "Account Created");
							st.execute();

							String Login = getUsername();
							String Password = getAlphaNumericString();
							String employee = "emp";
							String logquery = "INSERT INTO login (user, pwd, position) values (?, ?,'" + employee+ "')";
							PreparedStatement pstmt = con.prepareStatement(logquery);
							pstmt.setString(1, Login);
							pstmt.setString(2, Password);
							pstmt.execute();

							String msg = "Employee Username: " + Login + "\n" + "Employee Password:  "+ Password;
							JOptionPane.showMessageDialog(null, msg);
							
							tfname.setText("");
							taddress.setText("");
							tcontact.setText("");
							
							con.close();

						} catch (SQLException e1) {
							e1.getMessage();
						}
				}
			}
		});
		btnNewButton.setBounds(277, 263, 89, 23);
		panel.add(btnNewButton);

		JLabel lblNewLabel_4 = new JLabel("Create New Employee");
		lblNewLabel_4.setFont(new Font("MS Reference Sans Serif", Font.PLAIN, 18));
		lblNewLabel_4.setBounds(10, 11, 222, 30);
		panel.add(lblNewLabel_4);

//-------------------------------------------------------------------------------------------------------------//
//Table
		
		JButton updateBtn = new JButton("update");
		
		updateBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try
				{
					con=new DBconnection().getConnection();  
				    con.setAutoCommit(false);
				    int rows= table.getRowCount();
				    String queryco = "Insert into employees(emp_code,name, address,contact,Gender) values (?, ?, ?, ?, ?)";
				    PreparedStatement pst = con.prepareStatement(queryco);

				for(int row = 0; row<rows; row++)
				{
					System.out.println("rows: "+row);
				    String emp_code = (String)table.getValueAt(row, 0);
				    String name = (String)table.getValueAt(row, 1);
				    String address = (String)table.getValueAt(row, 2);
				    String contact = (String)table.getValueAt(row, 3);
				    String gender = (String)table.getValueAt(row, 4);
	
				        pst.setString(1, emp_code);
				        pst.setString(2, name);
				        pst.setString(3, address);
				        pst.setString(4, contact);
				        pst.setString(5, gender);
				        
				        pst.addBatch(); 
				}    
				   
				PreparedStatement ps=con.prepareStatement("truncate table employees");
				ps.executeUpdate();
				pst.executeBatch();
				con.commit();
				
				JOptionPane.showMessageDialog(null,"Updated SuccessFully");
				}
				catch(Exception e3){
					e3.getStackTrace();
				    JOptionPane.showMessageDialog(null,"table"+e3.getMessage());
				}
			}
		});
		updateBtn.setBounds(50, 50, 105, 40);
		
		try {
			ArrayList columnNames = new ArrayList();
			ArrayList data = new ArrayList();

			Statement stmt = new DBconnection().getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("select * from employees");
			ResultSetMetaData md = rs.getMetaData();

			// System.out.println(md+"columns");

			int columns = md.getColumnCount();
			for (int i = 1; i <= columns; i++) {
				columnNames.add(md.getColumnName(i));
			}
			while (rs.next()) {
				ArrayList row = new ArrayList(columns);
				for (int i = 1; i <= columns; i++) {
					row.add(rs.getObject(i));
				}
				data.add(row);
			}

			Vector columnNamesVector = new Vector();
			Vector dataVector = new Vector();

			for (int i = 0; i < data.size(); i++) {
				ArrayList subArray = (ArrayList) data.get(i);
				Vector subVector = new Vector();
				for (int j = 0; j < subArray.size(); j++) {
					subVector.add(subArray.get(j));
				}

				dataVector.add(subVector);
			}

			for (int i = 0; i < columnNames.size(); i++)
				columnNamesVector.add(columnNames.get(i));

			 table = new JTable(dataVector, columnNamesVector) {

			public Class getColumnClass(int column) {
							for (int row = 0; row < getRowCount(); row++) {
								Object o = getValueAt(row, column);
								
								if (o != null) {
									return o.getClass();
								}
							}
							return Object.class;
						}
					};
			
			JPanel edit_employees_pane = new JPanel();
			tabbedPane.addTab("Edit Employees Data", null, edit_employees_pane, null);
			edit_employees_pane.setLayout(null);
			
			JPanel table_panel = new JPanel();
			table_panel.setBackground(new Color(230, 230, 250));
			table_panel.setBounds(0, 0, 728, 309);
			edit_employees_pane.add(table_panel);
			table_panel.setLayout(null);
			
			JScrollPane employees_details_scroll_pane = new JScrollPane(table);
			employees_details_scroll_pane.setBounds(10, 5, 708, 293);
			table_panel.add(employees_details_scroll_pane);
			
			JPanel options_pannel = new JPanel();
			options_pannel.setBackground(new Color(230, 230, 250));
			options_pannel.setBounds(0, 309, 728, 49);
			edit_employees_pane.add(options_pannel);
			
			JButton btnNewButton_1 = new JButton("Update");
			btnNewButton_1.setBounds(320, 11, 89, 23);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					try
					{
						con=new DBconnection().getConnection();  
					    con.setAutoCommit(false);
					    int rows= table.getRowCount();
					    String queryco = "Insert into employees(emp_code,name, address,contact,Gender) values (?, ?, ?, ?, ?)";
					    PreparedStatement pst = con.prepareStatement(queryco);

					for(int row = 0; row<rows; row++)
					{
						System.out.println("rows: "+row);
					    String emp_code = (String)table.getValueAt(row, 0);
					    String name = (String)table.getValueAt(row, 1);
					    String address = (String)table.getValueAt(row, 2);
					    String contact = (String)table.getValueAt(row, 3);
					    String gender = (String)table.getValueAt(row, 4);
		
					        pst.setString(1, emp_code);
					        pst.setString(2, name);
					        pst.setString(3, address);
					        pst.setString(4, contact);
					        pst.setString(5, gender);
					        
					        pst.addBatch(); 
					}    
					   
					PreparedStatement ps=con.prepareStatement("truncate table employees");
					ps.executeUpdate();
					pst.executeBatch();
					con.commit();
					
					JOptionPane.showMessageDialog(null,"Updated SuccessFully");
					}
					catch(Exception e3){
						e3.getStackTrace();
					    JOptionPane.showMessageDialog(null,"table"+e3.getMessage());
					}
				}
			});
			
//---------------------------------------------------------------------------------------------------//
//Transactions
			
			options_pannel.setLayout(null);
			options_pannel.add(btnNewButton_1);
			
			JPanel Transactions = new JPanel();
			Transactions.setBackground(new Color(230, 230, 250));
			tabbedPane.addTab("Transactions", null, Transactions, null);
			Transactions.setLayout(null);
			
			JLabel lblNewLabel_6 = new JLabel("Total Amount Deposited Today");
			lblNewLabel_6.setBounds(10, 26, 172, 14);
			Transactions.add(lblNewLabel_6);
			
			JLabel lblNewLabel_7 = new JLabel("Total Amount Withdrawl Today");
			lblNewLabel_7.setBounds(10, 69, 172, 14);
			Transactions.add(lblNewLabel_7);
			
			total_amount_credited = new JTextField();
			total_amount_credited.setEditable(false);
			total_amount_credited.setBounds(209, 23, 86, 20);
			Transactions.add(total_amount_credited);
			total_amount_credited.setColumns(10);
			
			total_amount_withdrawl = new JTextField();
			total_amount_withdrawl.setEditable(false);
			total_amount_withdrawl.setBounds(209, 66, 86, 20);
			Transactions.add(total_amount_withdrawl);
			total_amount_withdrawl.setColumns(10);
			
			try{
			    
				System.out.println("transaction Pannel");
				
				con=new DBconnection().getConnection();  

				String qry = "select * from transactions where Date = ? ";
			    PreparedStatement pst2 = con.prepareStatement(qry);
			    pst2.setString(1, getdate());
			    
			    ResultSet Rs2 = pst2.executeQuery();
			    ArrayList <Integer> list = new ArrayList<Integer>();
			    ArrayList <Integer> list2 = new ArrayList<Integer>();
			    while(Rs2.next()) {
			    	int sum = 0;
			    	String method = Rs2.getString("transactionMode");
			    	
			    	if(method.equals("Deposit")) {
			    		int scanned_amount = Rs2.getInt("Amount");
			    		list.add(scanned_amount);
			    		System.out.println(list);	    		
				    	for(int i:list) {
				    		sum += i;
			    		}
				    	System.out.println(sum);
				    	total_amount_credited.setText(String.valueOf(sum));
			    	
			    	}else if(method.equals("Withdrawn")) {
			    		int scanned_amount1 = Rs2.getInt("Amount");
			    		list2.add(scanned_amount1);
			    		System.out.println(list2);
			    		for(int i:list2) {
				    		sum += i;
			    		}
				    	total_amount_withdrawl.setText(String.valueOf(sum));
			    	}else {
			    		System.out.println("Found");
			    	}
			    }
			}catch (Exception e) {
				e.getStackTrace();
			}
			
			
//---------------------------------------------------------------------------------------------------//
//Logout
			JButton btnNewButton_2 = new JButton("Logout");
			btnNewButton_2.setBounds(669, 27, 89, 23);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
					LoginFrontEnd log = new LoginFrontEnd();
					log.setVisible(true);
					log.setLocationRelativeTo(null);
					log.setBounds(500, 200, 600, 550);
					}
			});
			contentPane.add(btnNewButton_2);	
		} catch (Exception e) {
			e.printStackTrace();
		}
		JLabel lblNewLabel_5 = new JLabel("Welcome to Admin Pannel");
		lblNewLabel_5.setBounds(220, 0, 360, 45);
		lblNewLabel_5.setForeground(new Color(0, 128, 128));
		lblNewLabel_5.setFont(new Font("Segoe UI Historic", Font.BOLD | Font.ITALIC, 25));
		contentPane.add(lblNewLabel_5);
		

		
	}
}
