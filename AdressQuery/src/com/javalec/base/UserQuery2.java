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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UserQuery2 {

	private JFrame frame;
	private JScrollPane scrollPane;
	private JTable inner_Table;
	private JButton btn_OK;
	private JTextField tfSelection;
	private JComboBox selectBox;
	
	private final String url_mysql = "jdbc:mysql://192.168.35.13/useraddress?serverTimezone=UTC&characterEncoding=utf8";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";

	DefaultTableModel Outer_Table = new DefaultTableModel();
	
	private JTextField tf_Count;
	private JTextField tf_SeqNo;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JTextField tf_Name;
	private JLabel lblNewLabel_2;
	private JTextField tf_Telno;
	private JLabel lblNewLabel_3;
	private JTextField tf_Adress;
	private JLabel lblNewLabel_4;
	private JTextField tf_Email;
	private JLabel lblNewLabel_5;
	private JTextField tf_Relation;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserQuery2 window = new UserQuery2();
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
	public UserQuery2() {
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
		frame.getContentPane().add(getBtn_OK());
		frame.getContentPane().add(getTfSelection());
		frame.getContentPane().add(getSelectBox());
		frame.getContentPane().add(getTf_Count());
		frame.getContentPane().add(getTf_SeqNo());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getTf_Name());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getTf_Telno());
		frame.getContentPane().add(getLblNewLabel_3());
		frame.getContentPane().add(getTf_Adress());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getTf_Email());
		frame.getContentPane().add(getLblNewLabel_5());
		frame.getContentPane().add(getTf_Relation());
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
	private JButton getBtn_OK() {
		if (btn_OK == null) {
			btn_OK = new JButton("Search");
			btn_OK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					conditionQuery();
				}
			});
			btn_OK.setBounds(326, 17, 117, 29);
		}
		return btn_OK;
	}
	private JTextField getTfSelection() {
		if (tfSelection == null) {
			tfSelection = new JTextField();
			tfSelection.setBounds(156, 17, 130, 26);
			tfSelection.setColumns(10);
		}
		return tfSelection;
	}
	private JComboBox getSelectBox() {
		if (selectBox == null) {
			selectBox = new JComboBox();
			selectBox.setModel(new DefaultComboBoxModel(new String[] {"Name", "Phone", "Adress", "E-mail", "Relation"}));
			selectBox.setBounds(27, 17, 88, 27);
		}
		return selectBox;
	}
	
	
	
	private JTextField getTf_Count() {
		if (tf_Count == null) {
			tf_Count = new JTextField();
			tf_Count.setEditable(false);
			tf_Count.setHorizontalAlignment(SwingConstants.TRAILING);
			tf_Count.setBounds(296, 236, 130, 26);
			tf_Count.setColumns(10);
		}
		return tf_Count;
	}
	private JTextField getTf_SeqNo() {
		if (tf_SeqNo == null) {
			tf_SeqNo = new JTextField();
			tf_SeqNo.setEditable(false);
			tf_SeqNo.setBounds(76, 300, 130, 26);
			tf_SeqNo.setColumns(10);
		}
		return tf_SeqNo;
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
	private JTextField getTf_Name() {
		if (tf_Name == null) {
			tf_Name = new JTextField();
			tf_Name.setEditable(false);
			tf_Name.setColumns(10);
			tf_Name.setBounds(70, 336, 130, 26);
		}
		return tf_Name;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("Phone");
			lblNewLabel_2.setBounds(0, 379, 61, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getTf_Telno() {
		if (tf_Telno == null) {
			tf_Telno = new JTextField();
			tf_Telno.setEditable(false);
			tf_Telno.setColumns(10);
			tf_Telno.setBounds(70, 374, 130, 26);
		}
		return tf_Telno;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("Adress");
			lblNewLabel_3.setBounds(0, 419, 61, 16);
		}
		return lblNewLabel_3;
	}
	private JTextField getTf_Adress() {
		if (tf_Adress == null) {
			tf_Adress = new JTextField();
			tf_Adress.setEditable(false);
			tf_Adress.setColumns(10);
			tf_Adress.setBounds(70, 414, 130, 26);
		}
		return tf_Adress;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("E-Mail");
			lblNewLabel_4.setBounds(0, 458, 61, 16);
		}
		return lblNewLabel_4;
	}
	private JTextField getTf_Email() {
		if (tf_Email == null) {
			tf_Email = new JTextField();
			tf_Email.setEditable(false);
			tf_Email.setColumns(10);
			tf_Email.setBounds(70, 453, 130, 26);
		}
		return tf_Email;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("Relation");
			lblNewLabel_5.setBounds(6, 491, 61, 16);
		}
		return lblNewLabel_5;
	}
	private JTextField getTf_Relation() {
		if (tf_Relation == null) {
			tf_Relation = new JTextField();
			tf_Relation.setEditable(false);
			tf_Relation.setColumns(10);
			tf_Relation.setBounds(76, 486, 130, 26);
		}
		return tf_Relation;
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
				tf_Count.setText(Integer.toString(dataCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		// Table click
		private void tableClick() {
			int i = inner_Table.getSelectedRow();
			String wkSeq = (String) inner_Table.getValueAt(i, 0);
			String query =  "select seqno, name, telno, address, email, relation from userinfo where seqno="+ wkSeq; //TODO
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery(query);
				//while
					if (rs.next()) {
					tf_SeqNo.setText(rs.getString(1)); 
					tf_Name.setText(rs.getString(2));
					tf_Telno.setText(rs.getString(3));
					tf_Adress.setText(rs.getString(4));
					tf_Email.setText(rs.getString(5));
					tf_Relation.setText(rs.getString(6));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		// 콤보박스 상태에 따라 검색하는 내용이 날라지게 하는거임 
		private void conditionQuery() {
			int i = selectBox.getSelectedIndex();  // 콤보박스의 인덱스는 0부터 시작함.
			String conditonQueryColumn = "";
			
			switch (i) {
			case 0:
				conditonQueryColumn = "name";
				break;
			case 1:
				conditonQueryColumn = "telno";
				break;
			case 2:
				conditonQueryColumn = "address";
				break;
			case 4:
				conditonQueryColumn = "email";
				break;
			case 5:
				conditonQueryColumn = "relation";
				break;
			default:
				break;
			}
			//System.out.println(conditonQueryColumn);
			
			tableInit();       // 검색을 하게되면  inner테이블을 초기화 시켜줘야함
			clearColumn();     // 이건 박스 밑에 뜨는 것들을 초기화 시켜줌!
			conditionQueryAction(conditonQueryColumn);  // 다 초기화 시켜준 다음에 이제 검색버튼을 눌렀을때 
														// 처음에 했더 이너테이블에 나올때까지 뜨게하는 거랑 같은데 like 조건만 추가가 된거임
		}
		
		private void conditionQueryAction(String columnName) {
			String query1 = "select seqno, name, telno, address, email, relation from userinfo where " + columnName;
			String query2 =  " like '%" + tfSelection.getText() + "%'";
			//System.out.println(query1 + query2);
			int dataCount = 0;
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery(query1 + query2);
				while(rs.next()) {
					String[] qTxt = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					Outer_Table.addRow(qTxt);
					dataCount++;
				}
				
				conn_mysql.close();
				tf_Count.setText(Integer.toString(dataCount));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
		private void clearColumn() {
			tf_SeqNo.setText("");
			tf_Name.setText("");
			tf_Telno.setText("");
			tf_Adress.setText("");
			tf_Email.setText("");
			tf_Relation.setText("");
		}
		
		
}
