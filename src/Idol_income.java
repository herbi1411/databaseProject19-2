import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.ListSelectionModel;

public class Idol_income extends JFrame {
	private JPanel contentPane;
	private JTextField year_t;
	private JTextField month_t;
	private JTextField day_t;
	private Vector<String> vec;
	private JList<String> list;

	public Idol_income(Connection conn) {
		vec = new Vector<String>();
		Statement state = null;
		try {
			state = conn.createStatement();
			String sql;
			sql = "select iname\r\n" + 
					"from idol\r\n" + 
					"order by inum;";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				vec.addElement(rs.getString("iname"));
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}finally {
			try {
				if(state!=null)
					state.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		//
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 416, 363);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel idoln_l = new JLabel("\uC544\uC774\uB3CC \uC774\uB984");
		idoln_l.setForeground(Color.BLACK);
		idoln_l.setBackground(Color.ORANGE);
		idoln_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		idoln_l.setHorizontalAlignment(SwingConstants.CENTER);
		idoln_l.setBounds(14, 85, 110, 25);
		contentPane.add(idoln_l);
		
		JLabel period_l = new JLabel("\uAE30\uAC04");
		period_l.setHorizontalAlignment(SwingConstants.CENTER);
		period_l.setForeground(Color.BLACK);
		period_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		period_l.setBackground(Color.ORANGE);
		period_l.setBounds(14, 138, 56, 25);
		contentPane.add(period_l);
		
		JLabel year_l = new JLabel("\uC5F0\uB3C4");
		year_l.setHorizontalAlignment(SwingConstants.CENTER);
		year_l.setForeground(Color.BLACK);
		year_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		year_l.setBackground(Color.ORANGE);
		year_l.setBounds(68, 183, 56, 25);
		contentPane.add(year_l);
		
		JLabel month_l = new JLabel("\uC6D4");
		month_l.setHorizontalAlignment(SwingConstants.CENTER);
		month_l.setForeground(Color.BLACK);
		month_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		month_l.setBackground(Color.ORANGE);
		month_l.setBounds(68, 218, 56, 25);
		contentPane.add(month_l);
		
		JLabel day_l = new JLabel("\uC77C");
		day_l.setHorizontalAlignment(SwingConstants.CENTER);
		day_l.setForeground(Color.BLACK);
		day_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		day_l.setBackground(Color.ORANGE);
		day_l.setBounds(68, 255, 56, 25);
		contentPane.add(day_l);
		
		year_t = new JTextField();
		year_t.setHorizontalAlignment(SwingConstants.CENTER);
		year_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		year_t.setColumns(10);
		year_t.setBounds(138, 183, 115, 24);
		contentPane.add(year_t);
		
		month_t = new JTextField();
		month_t.setHorizontalAlignment(SwingConstants.CENTER);
		month_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		month_t.setColumns(10);
		month_t.setBounds(138, 218, 115, 24);
		contentPane.add(month_t);
		
		day_t = new JTextField();
		day_t.setHorizontalAlignment(SwingConstants.CENTER);
		day_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		day_t.setColumns(10);
		day_t.setBounds(138, 255, 115, 24);
		contentPane.add(day_t);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idolName = list.getSelectedValue();
				int year = Integer.parseInt(year_t.getText());
				int month = Integer.parseInt(month_t.getText());
				int day = Integer.parseInt(day_t.getText());
				if(year<2000 || year>2020) JOptionPane.showMessageDialog(null, "올바른 연도를 입력해주세요!");
				else if(month>12 || month<1) JOptionPane.showMessageDialog(null, "올바른 달을 입력해주세요!");
				else if(day>30 || day<1)
				{
					if(day==31 && (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12)) {}
					else JOptionPane.showMessageDialog(null, "올바른 일자를 입력해주세요!");
				}
				else
				{
					System.out.println(list.getSelectedValue() + " / " +year_t.getText() + " / " +month_t.getText() + " / " +day_t.getText() + " / " );
					String date = Integer.toString(year) + "-" + Integer.toString(month) + "-" + Integer.toString(day);
					Idol_income_r iir = new Idol_income_r(conn,idolName,date);
				}
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		btnNewButton.setBounds(267, 113, 105, 72);
		contentPane.add(btnNewButton);
		
		list = new JList<String>();
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		list.setListData(vec);
		list.setSelectedIndex(0);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(125, 87, 128, 61);
		contentPane.add(scrollPane);
		this.setVisible(true);
	}
}
