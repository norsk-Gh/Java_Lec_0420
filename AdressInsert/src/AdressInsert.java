import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AdressInsert {

	
	/*용어정리
	 * ResultSet executeQuery() - SELECT 쿼리를 실행할 때 사용되며 ResultSet을 결과값으로 리턴한다.

		int executeUpdate() - INSERT, UPDATE, DELETE 쿼리를 실행할 때 사용되며, 실행결과 변경된 레코드의 개수를 리턴한다.
	 * 
	 * setString은 첫번째 인자로 물음표의 위치가 들어가고 두번째로는 값이 들어간다.
	 * 
	 * PreparedStatement는 Statement와 동일한 기능을 하지만 차이점이 있다면 전자는 미리 SQL쿼리의 틀을 짜 놓고 나중에 값일 지정한다는 것이다
	 * 
	 * 
	 */
	
	
	
	
	
	
	
	
	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AdressInsert window = new AdressInsert();
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
	public AdressInsert() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		nameField = new JTextField();
		nameField.setToolTipText("");
		nameField.setBounds(60, 26, 130, 26);
		frame.getContentPane().add(nameField);
		nameField.setColumns(10);
		
		phontField = new JTextField();
		phontField.setToolTipText("");
		phontField.setColumns(10);
		phontField.setBounds(60, 64, 130, 26);
		frame.getContentPane().add(phontField);
		
		adressField = new JTextField();
		adressField.setToolTipText("");
		adressField.setColumns(10);
		adressField.setBounds(60, 102, 130, 26);
		frame.getContentPane().add(adressField);
		
		emailField = new JTextField();
		emailField.setToolTipText("");
		emailField.setColumns(10);
		emailField.setBounds(60, 140, 130, 26);
		frame.getContentPane().add(emailField);
		
		relationField = new JTextField();
		relationField.setToolTipText("");
		relationField.setColumns(10);
		relationField.setBounds(60, 192, 130, 26);
		frame.getContentPane().add(relationField);
		
		JButton btnEnter = new JButton("enter");
		btnEnter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int i_chk = checkBlank();
				if(i_chk == 0) {
					insertAction(); 
				}
				// Action
			}
		});
		btnEnter.setBounds(255, 220, 117, 29);
		frame.getContentPane().add(btnEnter);
	}
	
	private final String url_mysql = "jdbc:mysql://192.168.35.13/useraddress?serverTimezone=UTC&characterEncoding=utf8";
	private final String id_mysql = "root";
	private final String pw_mysql = "qwer1234";
	
	private JTextField nameField;
	private JTextField phontField;
	private JTextField adressField;
	private JTextField emailField;
	private JTextField relationField;
	
	
	private int checkBlank() {
		int check = 0;
		String alert = "Please add blank.";
		
		if (relationField.getText().trim().isEmpty()) {
			alert = "Relation is empty, " + alert;
			check++;
			relationField.requestFocus();
		}
		if (emailField.getText().trim().isEmpty()) {
			alert = "Adress is empty, " + alert;
			check++;
			emailField.requestFocus();
		} 
		if (adressField.getText().trim().isEmpty()) {
			alert = "Email is empty, " + alert;
			check++;
			adressField.requestFocus();
		} 
		if (phontField.getText().trim().isEmpty()) {
			alert = "Phone is empty, " + alert;
			check++;
			phontField.requestFocus();
		} 
		if (nameField.getText().trim().isEmpty()) {
			alert = "Name is empty, " + alert;
			check++;
			nameField.requestFocus();
		}
		if (check > 0) {
		JOptionPane.showMessageDialog(null, alert);
		}
		
		return check;
	}
	
	private void insertAction() {//사용자 정보 입력
		PreparedStatement ps = null;
		
		//if (nameField.getText().trim().isEmpty() || phontField.getText().trim().isEmpty() || adressField.getText().trim().isEmpty() || emailField.getText().trim().isEmpty() || relationField.getText().trim().isEmpty()) {
			
		
		//	JOptionPane.showMessageDialog(null, checkBlank());
		//} else {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection conn_mysql = DriverManager.getConnection(url_mysql, id_mysql,pw_mysql);
				Statement stmt_mysql = conn_mysql.createStatement();

				String query = "insert into userinfo (name, telno, address, email, relation ) values (?, ?, ?, ?, ?)";// space is important.
				ps = conn_mysql.prepareStatement(query);
				ps.setString(1, nameField.getText().trim());
				ps.setString(2, phontField.getText().trim());
				ps.setString(3, adressField.getText().trim());
				ps.setString(4, emailField.getText().trim());
				ps.setString(5, relationField.getText().trim());

				ps.executeUpdate();

				conn_mysql.close();
				JOptionPane.showMessageDialog(null, "Success add " + nameField.getText() + "'s info.");

			} catch(Exception e) {
				e.printStackTrace();
			}
		}
	//}
}
