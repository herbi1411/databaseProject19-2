import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.sql.*;
public class DBAccess extends JFrame {
	private JPanel contentPane;
	private JTextField id_f;
	private JPasswordField pw_f;
	private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver"; //드라이버
	private final String DB_URL = "jdbc:mysql://localhost:3306/idol?serverTimezone=Asia/Seoul"; //접속할 DB 서버		
	private String USER_NAME = "root"; //DB에 접속할 사용자 이름을 상수로 정의
	private String PASSWORD = "141112"; //사용자의 비밀번호를 상수로 정의
	private boolean isExit;
	private Connection conn;
	public DBAccess() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 395, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton logIn = new JButton("\uB85C\uADF8\uC778");
		logIn.setBackground(Color.LIGHT_GRAY);
		logIn.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		logIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean isError = false;
				USER_NAME = id_f.getText();
				PASSWORD = pw_f.getText();
				id_f.setText("");
				pw_f.setText("");
				try {
					Class.forName(JDBC_DRIVER);
					conn = DriverManager.getConnection(DB_URL,USER_NAME,PASSWORD);
					System.out.println("Connection success!!");
					//state = conn.createStatement();
				} catch (Exception e) {
					isError = true;
					JOptionPane.showMessageDialog(null, "회원 정보가 일치하지 않습니다!!");
					// TODO Auto-generated catch block
					e.printStackTrace();
				}finally {
					if(!isError)
					{
						JOptionPane.showMessageDialog(null, "환영합니다 " + USER_NAME + " 님!");
						isExit = true;
						dispose();
					}
				}
			}
		});
		logIn.setBounds(253, 65, 90, 90);
		contentPane.add(logIn);
		id_f = new JTextField();
		id_f.setBounds(94, 63, 116, 24);
		contentPane.add(id_f);
		id_f.setColumns(10);
		
		pw_f = new JPasswordField();
		pw_f.setBounds(94, 134, 116, 24);
		contentPane.add(pw_f);
		
		JLabel id_l = new JLabel("ID");
		id_l.setHorizontalAlignment(SwingConstants.CENTER);
		id_l.setFont(new Font("210 맨발의청춘 B", Font.BOLD, 15));
		id_l.setBounds(18, 66, 62, 18);
		contentPane.add(id_l);
		
		JLabel pw_l = new JLabel("PW");
		pw_l.setHorizontalAlignment(SwingConstants.CENTER);
		pw_l.setFont(new Font("210 맨발의청춘 B", Font.BOLD, 15));
		pw_l.setBounds(18, 137, 62, 18);
		contentPane.add(pw_l);
		this.setVisible(true);
	}
	public static void main(String[] args)
	{
		new DBAccess();
	}
	public boolean isExit() {
		return isExit;
	}
	public Connection getConn() {
		return conn;
	}
	
}
