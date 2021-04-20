package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableColumnModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class UserQuery {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable inner_Table;
	private JButton searchBtn;
	private JTextField textField;
	private JComboBox selectBox;
	
	private final String url_mysql = "jdbc:mysql://192.168.0.179/useraddress?serverTimezone=UTC&characterEncoding=utf8";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";

	DefaultTableModel Outer_Table = new DefaultTableModel();
	
	private JTextField tfCount;
	private JTextField tfSeqNo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tfName;
	private JLabel lblNewLabel_2;
	private JTextField tfTPhone;
	private JLabel lblNewLabel_3;
	private JTextField tfAdress;
	private JLabel lblNewLabel_4;
	private JTextField tfEmail;
	private JLabel lblNewLabel_5;
	private JTextField tfRelation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserQuery window = new UserQuery();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public UserQuery() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowOpened(WindowEvent e) {
				tableInit();
				searchAction();

			}
		});
		frame.setBounds(100, 100, 450, 585);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getScrollPane());
		frame.getContentPane().add(getSearchBtn());
		frame.getContentPane().add(getTextField());
		frame.getContentPane().add(getSelectBox());
		frame.getContentPane().add(getTfCount());
		frame.getContentPane().add(getTfSeqNo());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getTfName());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getTfTPhone());
		frame.getContentPane().add(getLblNewLabel_3());
		frame.getContentPane().add(getTfAdress());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getTfEmail());
		frame.getContentPane().add(getLblNewLabel_5());
		frame.getContentPane().add(getTfRelation());
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(6, 56, 438, 168);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JTable getInner_Table() {
		if (inner_Table == null) {
			inner_Table = new JTable();
			inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tableClick();
				}
			});
			inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			inner_Table.setModel(Outer_Table);
		}
		return inner_Table;
	}
	private JButton getSearchBtn() {
		if (searchBtn == null) {
			searchBtn = new JButton("Search");
			searchBtn.setBounds(326, 17, 117, 29);
		}
		return searchBtn;
	}
	private JTextField getTextField() {
		if (textField == null) {
			textField = new JTextField();
			textField.setBounds(156, 17, 130, 26);
			textField.setColumns(10);
		}
		return textField;
	}
	private JComboBox getSelectBox() {
		if (selectBox == null) {
			selectBox = new JComboBox();
			selectBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Phone", "Adress", "E-mail", "Relation"}));
			selectBox.setBounds(27, 17, 88, 27);
		}
		return selectBox;
	}
	
	
	
	private JTextField getTfCount() {
		if (tfCount == null) {
			tfCount = new JTextField();
			tfCount.setEditable(false);
			tfCount.setHorizontalAlignment(SwingConstants.TRAILING);
			tfCount.setBounds(296, 236, 130, 26);
			tfCount.setColumns(10);
		}
		return tfCount;
	}
	private JTextField getTfSeqNo() {
		if (tfSeqNo == null) {
			tfSeqNo = new JTextField();
			tfSeqNo.setEditable(false);
			tfSeqNo.setBounds(76, 300, 130, 26);
			tfSeqNo.setColumns(10);
		}
		return tfSeqNo;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Seq No :");
			lblNewLabel.setBounds(6, 305, 61, 16);
		}
		return lblNewLabel;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("Name :");
			lblNewLabel_1.setBounds(0, 341, 61, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTfName() {
		if (tfName == null) {
			tfName = new JTextField();
			tfName.setEditable(false);
			tfName.setColumns(10);
			tfName.setBounds(70, 336, 130, 26);
		}
		return tfName;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Phone");
			lblNewLabel_2.setBounds(0, 379, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getTfTPhone() {
		if (tfTPhone == null) {
			tfTPhone = new JTextField();
			tfTPhone.setEditable(false);
			tfTPhone.setColumns(10);
			tfTPhone.setBounds(70, 374, 130, 26);
		}
		return tfTPhone;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Adress");
			lblNewLabel_3.setBounds(0, 419, 61, 16);
		}
		return lblNewLabel_3;
	}
	private JTextField getTfAdress() {
		if (tfAdress == null) {
			tfAdress = new JTextField();
			tfAdress.setEditable(false);
			tfAdress.setColumns(10);
			tfAdress.setBounds(70, 414, 130, 26);
		}
		return tfAdress;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("E-Mail");
			lblNewLabel_4.setBounds(0, 458, 61, 16);
		}
		return lblNewLabel_4;
	}
	private JTextField getTfEmail() {
		if (tfEmail == null) {
			tfEmail = new JTextField();
			tfEmail.setEditable(false);
			tfEmail.setColumns(10);
			tfEmail.setBounds(70, 453, 130, 26);
		}
		return tfEmail;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Relation");
			lblNewLabel_5.setBounds(6, 491, 61, 16);
		}
		return lblNewLabel_5;
	}
	private JTextField getTfRelation() {
		if (tfRelation == null) {
			tfRelation = new JTextField();
			tfRelation.setEditable(false);
			tfRelation.setColumns(10);
			tfRelation.setBounds(76, 486, 130, 26);
		}
		return tfRelation;
	}
	
	// Window Table init!
		private void tableInit() {
			Outer_Table.addColumn("Order");
			Outer_Table.addColumn("Name");
			Outer_Table.addColumn("Phone");
			Outer_Table.addColumn("Adress");
			Outer_Table.addColumn("Email");
			Outer_Table.addColumn("Relation");
			
			int i = Outer_Table.getRowCount();
			for(int j=0; j<i; j++) {
				Outer_Table.removeRow(0);
			}
			
			inner_Table.setAutoResizeMode(inner_Table.AUTO_RESIZE_OFF);
			
			int vColIndex = 0;
			TableColumn col = inner_Table.getColumnModel().getColumn(vColIndex);
			int width = 30;
			col.setPreferredWidth(width);
			
			vColIndex = 1;
			col = inner_Table.getColumnModel().getColumn(vColIndex);
			width = 100;
			col.setPreferredWidth(width);

			vColIndex = 2;
			col = inner_Table.getColumnModel().getColumn(vColIndex);
			width = 100;
			col.setPreferredWidth(width);
			
			vColIndex = 3;
			col = inner_Table.getColumnModel().getColumn(vColIndex);
			width = 100;
			col.setPreferredWidth(width);

			vColIndex = 4;
			col = inner_Table.getColumnModel().getColumn(vColIndex);
			width = 100;
			col.setPreferredWidth(width);
			
			vColIndex = 5;
			col = inner_Table.getColumnModel().getColumn(vColIndex);
			width = 100;
			col.setPreferredWidth(width);
			
		}
		
		// Search!
		private void searchAction() {
			String query = "select seqno, name, telno, address, email, relation from userinfo ";
			int dataCount = 0;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery(query);
				while(rs.next()) {
					String[] qTxt = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					Outer_Table.addRow(qTxt);
					dataCount++;
				}
				
				conn_mysql.close();
				tfCount.setText(Integer.toString(dataCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Table click
		private void tableClick() {
			int i = inner_Table.getSelectedRow();
			String wkSeq = (String) inner_Table.getValueAt(i, 0);
			String query =  "select seqno, name, telno, relation from userinfo where seqno="+ wkSeq; //TODO
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery(query);
				while(rs.next()) {
					tfSeqNo.setText(rs.getString(1)); 
					tfName.setText(rs.getString(2));
					tfTPhone.setText(rs.getString(3));
					tfAdress.setText(rs.getString(4));
					tfEmail.setText(rs.getString(5));
					tfRelation.setText(rs.getString(6));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
}