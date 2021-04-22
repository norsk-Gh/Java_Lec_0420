package com.javalec.base;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Font;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.ButtonGroup;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollBar;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Address {

	// [00] GUI 세팅하기
	/* 
	 * 
	 * Button
	 * JScrollpane -> viewPoint -> JTable -> Single_Selection
	 */
	
	
	
	
	
	// [01] 데이터베이스 연결시키기                                           *[01]   
	private final String url_mysql = "jdbc:mysql://192.168.35.13/useraddress?serverTimezone=UTC&characterEncoding=utf8";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	
	// [06] 테이블 만들기 전 세팅
	DefaultTableModel Outer_Table = new DefaultTableModel();
	
	private JFrame frame;
	private JRadioButton rdb_Insert;
	private JRadioButton rdb_Update;
	private JRadioButton rdb_Delete;
	private JRadioButton rdb_Search;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private JComboBox cb_Select;
	private JTextField tf_Search;
	private JButton btn_Search;
	private JScrollPane scrollPane;
	private JLabel lblNewLabel;
	private JTextField tf_SeqNo;
	private JTextField tf_Name;
	private JLabel lblNewLabel_1;
	private JTextField tf_Telno;
	private JLabel lblNewLabel_2;
	private JTextField tf_Address;
	private JLabel lblNewLabel_3;
	private JTextField tf_Email;
	private JLabel lblNewLabel_4;
	private JTextField tf_Relation;
	private JLabel lblNewLabel_5;
	private JButton btn_OK;
	private JScrollBar scrollBar;
	private JTable inner_Table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Address window = new Address();
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
	public Address() {
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
				first_uneditable();
			// [10] frame -> handler -> window -> windowOpened
			//  ** 윈도우 처음 열리면 바로 정보 보이게 하기 **	
				tableInit();
				
			// [12] DB에서 불러온 값 보여주게 하기	  -> 클릭하면 밑에 뜨게 하기
				searchAction();
			}
		});
		frame.setFont(new Font("Dialog", Font.PLAIN, 15));
		frame.setTitle("주소록");
		frame.setBounds(100, 100, 425, 465);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(getRdb_Insert());
		frame.getContentPane().add(getRdb_Update());
		frame.getContentPane().add(getRdb_Delete());
		frame.getContentPane().add(getRdb_Search());
		frame.getContentPane().add(getCb_Select());
		frame.getContentPane().add(getTf_Search());
		frame.getContentPane().add(getBtn_Search());
		frame.getContentPane().add(getScrollPane());
		frame.getContentPane().add(getLblNewLabel());
		frame.getContentPane().add(getTf_SeqNo());
		frame.getContentPane().add(getTf_Name());
		frame.getContentPane().add(getLblNewLabel_1());
		frame.getContentPane().add(getTf_Telno());
		frame.getContentPane().add(getLblNewLabel_2());
		frame.getContentPane().add(getTf_Address());
		frame.getContentPane().add(getLblNewLabel_3());
		frame.getContentPane().add(getTf_Email());
		frame.getContentPane().add(getLblNewLabel_4());
		frame.getContentPane().add(getTf_Relation());
		frame.getContentPane().add(getLblNewLabel_5());
		frame.getContentPane().add(getBtn_OK());
	}
	private JRadioButton getRdb_Insert() {
		if (rdb_Insert == null) {
			rdb_Insert = new JRadioButton("입력");
			rdb_Insert.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editableFalse();
					clearColumn();
				}
			});
			buttonGroup.add(rdb_Insert);
			rdb_Insert.setBounds(6, 6, 54, 23);
		}
		return rdb_Insert;
	}
	private JRadioButton getRdb_Update() {
		if (rdb_Update == null) {
			rdb_Update = new JRadioButton("수정");
			rdb_Update.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editableFalse();
					clearColumn();
					
				}
			});
			buttonGroup.add(rdb_Update);
			rdb_Update.setBounds(77, 6, 54, 23);
		}
		return rdb_Update;
	}
	private JRadioButton getRdb_Delete() {
		if (rdb_Delete == null) {
			rdb_Delete = new JRadioButton("삭제");
			rdb_Delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editableFalse();
				}
			});
			buttonGroup.add(rdb_Delete);
			rdb_Delete.setBounds(147, 6, 54, 23);
		}
		return rdb_Delete;
	}
	private JRadioButton getRdb_Search() {
		if (rdb_Search == null) {
			rdb_Search = new JRadioButton("검색");
			rdb_Search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					editableFalse();
					
				
				}
			});
			rdb_Search.setSelected(true);
			buttonGroup.add(rdb_Search);
			rdb_Search.setBounds(216, 6, 54, 23);
		}
		return rdb_Search;
	}
	private JComboBox getCb_Select() {
		if (cb_Select == null) {
			cb_Select = new JComboBox();
			cb_Select.setModel(new DefaultComboBoxModel(new String[] {"이름", "전화번호", "주소", "전자우편", "관계"}));
			cb_Select.setBounds(16, 41, 88, 27);
		}
		return cb_Select;
	}
	private JTextField getTf_Search() {
		if (tf_Search == null) {
			tf_Search = new JTextField();
			tf_Search.setBounds(116, 41, 175, 26);
			tf_Search.setColumns(10);
		}
		return tf_Search;
	}
	private JButton getBtn_Search() {
		if (btn_Search == null) {
			btn_Search = new JButton("검색");
			btn_Search.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					// [18]
					conditionQuery();
				}
			});
			btn_Search.setBounds(299, 40, 106, 29);
		}
		return btn_Search;
	}
	private JScrollPane getScrollPane() {
		if (scrollPane == null) {
			scrollPane = new JScrollPane();
			scrollPane.setBounds(26, 80, 364, 129);
			scrollPane.setViewportView(getInner_Table());
		}
		return scrollPane;
	}
	private JLabel getLblNewLabel() {
		if (lblNewLabel == null) {
			lblNewLabel = new JLabel("Sequence No : ");
			lblNewLabel.setBounds(25, 232, 93, 16);
		}
		return lblNewLabel;
	}
	private JTextField getTf_SeqNo() {
		if (tf_SeqNo == null) {
			tf_SeqNo = new JTextField();
			tf_SeqNo.setEditable(false);
			tf_SeqNo.setBounds(116, 227, 130, 26);
			tf_SeqNo.setColumns(10);
		}
		return tf_SeqNo;
	}
	private JTextField getTf_Name() {
		if (tf_Name == null) {
			tf_Name = new JTextField();
			tf_Name.setColumns(10);
			tf_Name.setBounds(117, 260, 130, 26);
		}
		return tf_Name;
	}
	private JLabel getLblNewLabel_1() {
		if (lblNewLabel_1 == null) {
			lblNewLabel_1 = new JLabel("이     름 : ");
			lblNewLabel_1.setBounds(26, 265, 93, 16);
		}
		return lblNewLabel_1;
	}
	private JTextField getTf_Telno() {
		if (tf_Telno == null) {
			tf_Telno = new JTextField();
			tf_Telno.setColumns(10);
			tf_Telno.setBounds(117, 293, 130, 26);
		}
		return tf_Telno;
	}
	private JLabel getLblNewLabel_2() {
		if (lblNewLabel_2 == null) {
			lblNewLabel_2 = new JLabel("전화번호 : ");
			lblNewLabel_2.setBounds(26, 298, 93, 16);
		}
		return lblNewLabel_2;
	}
	private JTextField getTf_Address() {
		if (tf_Address == null) {
			tf_Address = new JTextField();
			tf_Address.setColumns(10);
			tf_Address.setBounds(117, 326, 130, 26);
		}
		return tf_Address;
	}
	private JLabel getLblNewLabel_3() {
		if (lblNewLabel_3 == null) {
			lblNewLabel_3 = new JLabel("주     소 : ");
			lblNewLabel_3.setBounds(26, 331, 93, 16);
		}
		return lblNewLabel_3;
	}
	private JTextField getTf_Email() {
		if (tf_Email == null) {
			tf_Email = new JTextField();
			tf_Email.setColumns(10);
			tf_Email.setBounds(117, 359, 130, 26);
		}
		return tf_Email;
	}
	private JLabel getLblNewLabel_4() {
		if (lblNewLabel_4 == null) {
			lblNewLabel_4 = new JLabel("전자우편 : ");
			lblNewLabel_4.setBounds(26, 364, 93, 16);
		}
		return lblNewLabel_4;
	}
	private JTextField getTf_Relation() {
		if (tf_Relation == null) {
			tf_Relation = new JTextField();
			tf_Relation.setColumns(10);
			tf_Relation.setBounds(116, 392, 130, 26);
		}
		return tf_Relation;
	}
	private JLabel getLblNewLabel_5() {
		if (lblNewLabel_5 == null) {
			lblNewLabel_5 = new JLabel("관      계 : ");
			lblNewLabel_5.setBounds(25, 397, 93, 16);
		}
		return lblNewLabel_5;
	}
	private JButton getBtn_OK() {
		if (btn_OK == null) {
			btn_OK = new JButton("OK");
			btn_OK.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					// [02] '입력'메소드 이름 먼저 정해서 넣어주기                     *[02]
					if(rdb_Insert.isSelected()==true) {
						insertAction();
					}
					// [16-3] 지우기 액션 여기에 추가해줬음!
					if(rdb_Delete.isSelected()==true) {
						deleteQueryAction();
					}
					// [17] 수정하기 추가
					if(rdb_Update.isSelected()==true) {
						reviseAction();
					}
					tableInit();
					searchAction();
					clearColumn();
				}
			});
			btn_OK.setBounds(273, 392, 117, 29);
		}
		return btn_OK;
	}
	private JScrollBar getScrollBar() {
		if (scrollBar == null) {
			scrollBar = new JScrollBar();
		}
		return scrollBar;
	}
	private JTable getInner_Table() {
		if (inner_Table == null) {
			inner_Table = new JTable();
			inner_Table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					// [13] 클릭했을떄 밑에 텍스트필드들에 각각 뜨게하기 (미리만들어주기)
					/**** 착안사항
					 * 1.이너테이블에 handler > mouseCliked
					 * 2.이거 안해주니까 뭔가 바운드? 관련 오류가 났음
					 */
					
					tableClick();
					
				}
			});
			inner_Table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			// [07] setModel 넣어주기  --> 08 테이블 초기화 Method 만들어주기 (frame-windowopened)
			inner_Table.setModel(Outer_Table);
			
		}
		return inner_Table;
	}
	
//*************************************************************************//
	// Method
	
	// [03] 입력 Method --------------------------//   
	private void insertAction() {
		
		//[05] 빈칸이 있을 경우 확인메세지
		if (tf_Relation.getText().trim().isEmpty() ||
			tf_Email.getText().trim().isEmpty()	   ||
			tf_Address.getText().trim().isEmpty()  ||
			tf_Telno.getText().trim().isEmpty()    ||
			tf_Name.getText().trim().isEmpty()) {
			checkBlank();
			}//if
		else {
		
		PreparedStatement ps = null;
		
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				// [04] SQL Qeury문 만들기                              *[04]
				String query = "insert into userinfo (name, telno, address, email, relation ) values (?, ?, ?, ?, ?)";// space is important.
				ps = conn_mysql.prepareStatement(query);
				ps.setString(1, tf_Name.getText().trim());
				ps.setString(2, tf_Telno.getText().trim());
				ps.setString(3, tf_Address.getText().trim());
				ps.setString(4, tf_Email.getText().trim());
				ps.setString(5, tf_Relation.getText().trim());

				ps.executeUpdate();

				conn_mysql.close();
				JOptionPane.showMessageDialog(null, "입력되었습니다");
			} catch(Exception e) {
				e.printStackTrace();
			}
	    	}//else
		}//insertAction
	// INSERT METHOD ------------------------------//
	
	// [04] 빈칸 체크 Method ----------------------//
	/**착안사항**
	 * 1. 가장 아래에 있는 tf부터 역순으로 올라오게 했으며, 공란 알람 표시 후, 해당 위치로 입력창 이동
	 * 2. Return 값이 있기때문에, void가 아닌 int 사용
	 */
	private int checkBlank() {
		int check = 0;
		String alert = "Please add blank.";
		
		if (tf_Relation.getText().trim().isEmpty()) {
			alert = "Relation is empty, " + alert;
			check++;
			tf_Relation.requestFocus();
		}
		if (tf_Email.getText().trim().isEmpty()) {
			alert = "Adress is empty, " + alert;
			check++;
			tf_Email.requestFocus();
		} 
		if (tf_Address.getText().trim().isEmpty()) {
			alert = "Email is empty, " + alert;
			check++;
			tf_Address.requestFocus();
		} 
		if (tf_Telno.getText().trim().isEmpty()) {
			alert = "Phone is empty, " + alert;
			check++;
			tf_Telno.requestFocus();
		} 
		if (tf_Name.getText().trim().isEmpty()) {
			alert = "Name is empty, " + alert;
			check++;
			tf_Name.requestFocus();
		}
		if (check > 0) {
		JOptionPane.showMessageDialog(null, alert);
		}
		
		return check;
	}
	// -----------------------
	
	// [08] 테이블 양식하고 초기값 만들어주기 & 초기화할떄 쓰기
	private void tableInit() {
		Outer_Table.addColumn("Order");
		Outer_Table.addColumn("Name");
		Outer_Table.addColumn("Phone");
		Outer_Table.addColumn("Adress");
		Outer_Table.addColumn("Email");
		Outer_Table.addColumn("Relation");
		
		Outer_Table.setColumnCount(6);
		
		// 맨 윗줄(Row)부터 무한으로 지워서 clear 하게 만드는방법
		int i = Outer_Table.getRowCount();
		for(int j=0; j<i; j++) {
			Outer_Table.removeRow(0);
		}
		
		inner_Table.setAutoResizeMode(inner_Table.AUTO_RESIZE_OFF);
		
		// [09] 테이블크기 만들어주기
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
	//-------------------------------------------//
	
	// [11] 처음에 창 열었을떄! 그리고 데이터베이스 다시 불러올떄(init+search)
	private void searchAction() {
		String query = "select seqno, name, telno, address, email, relation from userinfo ";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(query);
			while(rs.next()) {
				String[] qTxt = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
				Outer_Table.addRow(qTxt);
			}
			
			conn_mysql.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//--------------------------------------------//
	
	// [14] 클릭하면 텍스트필드에 뜨게하기-----------------//
	private void tableClick() {
		int i = inner_Table.getSelectedRow();
		String wkSeq = (String) inner_Table.getValueAt(i, 0);
		String query =  "select seqno, name, telno, address, email, relation from userinfo ";
		String query2 = "where seqno = ";
//		String query3 = "'";
		// **** 여기서 오래동안 오류가 났음!!! where중복..
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			ResultSet rs = stmt_mysql.executeQuery(query+query2+wkSeq);
			//while
				while (rs.next()) {
				tf_SeqNo.setText(rs.getString(1)); 
				tf_Name.setText(rs.getString(2));
				tf_Telno.setText(rs.getString(3));
				tf_Address.setText(rs.getString(4));
				tf_Email.setText(rs.getString(5));
				tf_Relation.setText(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	//----------------------------------------------//
	
	
	// [16-1] 밑에 텍스트필드 초기화해주기
	private void clearColumn() {
		tf_SeqNo.setText("");
		tf_Name.setText("");
		tf_Telno.setText("");
		tf_Address.setText("");
		tf_Email.setText("");
		tf_Relation.setText("");
	}
	
	// [16] 지워주기
	private void deleteQueryAction() {
		
		int dataCount = 0;
		//삭제하기 위한 쿼리
		String queryDel = "delete from userinfo where seqno = '";
		String queryDel2 = tf_SeqNo.getText().toString();
		String queryDel3 = "'";
		String selectedName = tf_Name.getText().toString();
		
		// 다시 보여주기 위한 쿼리
		String query = "select seqno, name, telno, address, email, relation from userinfo ";
				
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
			Statement stmt_mysql = conn_mysql.createStatement();
			int rsDel = stmt_mysql.executeUpdate(queryDel+queryDel2+queryDel3);  // 삭제는 또update로 써야함;;;;

			//초기화시켜주기
			tableInit();      
			clearColumn();     // 이건 박스 밑에 뜨는 것들을 초기화 시켜줌!
			
			//삭제하고 나서의 리스트 다시 보여주기
			ResultSet rs = stmt_mysql.executeQuery(query);
			while(rs.next()) {
				String[] qTxt = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
				Outer_Table.addRow(qTxt);
				dataCount++;
			}
			conn_mysql.close();
			
			// 삭제됐다고 메세지 보여주기
			JOptionPane.showMessageDialog(null, selectedName + "'s information was deleted successfully");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//---------------------------//

	
	// [15] 불가능하게 막아주기
	private void editableFalse() {
		if(rdb_Insert.isSelected()==true || rdb_Update.isSelected()==true)
			tf_Name.setEditable(true);
			tf_Telno.setEditable(true);
			tf_Address.setEditable(true);
			tf_Email.setEditable(true);
			tf_Relation.setEditable(true);
		
		
		if(rdb_Search.isSelected()==true || rdb_Delete.isSelected()==true) {
			tf_Name.setEditable(false);
			tf_Telno.setEditable(false);
			tf_Address.setEditable(false);
			tf_Email.setEditable(false);
			tf_Relation.setEditable(false);
		}
	}
	
		private void first_uneditable() {
			tf_Name.setEditable(false);
			tf_Telno.setEditable(false);
			tf_Address.setEditable(false);
			tf_Email.setEditable(false);
			tf_Relation.setEditable(false);
		}
	
	
	// [17] 수정하기 관련 ------------------------
		
		private void reviseAction() {
			
			PreparedStatement ps = null;  
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				
				String query1 = "update userinfo set name = ?, telno = ?,address = ?, email = ?, relation = ? "; //끝에 띄어쓰기
				String query2 = "where seqno = ? ";
				
				ps = conn_mysql.prepareStatement(query1+query2);
				ps.setString(1, tf_Name.getText());
				ps.setString(2, tf_Telno.getText());
				ps.setString(3, tf_Address.getText());
				ps.setString(4, tf_Email.getText());
				ps.setString(5, tf_Relation.getText());
				ps.setString(6, tf_SeqNo.getText());
				
				ps.executeUpdate();
				
				conn_mysql.close();
				
				
				
				JOptionPane.showMessageDialog(null, tf_Name.getText() + "'s information was updeted successfully");
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		//---------------------------
		
		private void conditionQuery() {
			int i = cb_Select.getSelectedIndex();  // 콤보박스의 인덱스는 0부터 시작함.
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
			String query2 =  " like '%" + tf_Search.getText() + "%'";
			//System.out.println(query1 + query2);
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql, pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();
				ResultSet rs = stmt_mysql.executeQuery(query1 + query2);
				while(rs.next()) {
					String[] qTxt = { rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6)};
					Outer_Table.addRow(qTxt);
				}
				
				conn_mysql.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		
	
	
}
