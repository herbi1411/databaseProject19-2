import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;

import java.awt.Font;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Fan_insert extends JFrame {

	private JPanel contentPane;
	private JTextField name_t;
	private JList<String> list;
	private JScrollPane scrollPane;
	private Vector<String> vec;
	private JLabel bd_l;
	private JTextField bd_t;
	private JLabel email_l;
	private JLabel pn_l;
	private JTextField email_t;
	private JTextField pn_t;

	public Fan_insert(Connection conn) {
		vec = new Vector<String>();
		vec.addElement("남"); vec.addElement("여");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		name_t = new JTextField();
		name_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		name_t.setBounds(196, 49, 98, 24);
		contentPane.add(name_t);
		name_t.setColumns(10);
		
		JLabel name_l = new JLabel("\uC774\uB984");
		name_l.setHorizontalAlignment(SwingConstants.RIGHT);
		name_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 18));
		name_l.setBounds(118, 49, 64, 24);
		contentPane.add(name_l);
		
		JLabel sex_l = new JLabel("\uC131\uBCC4");
		sex_l.setHorizontalAlignment(SwingConstants.RIGHT);
		sex_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 18));
		sex_l.setBounds(118, 85, 64, 24);
		contentPane.add(sex_l);
		
		list = new JList<String>();
		list.setVisibleRowCount(2);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		list.setListData(vec);
		list.setSelectedIndex(0);
		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(196, 85, 98, 24);
		contentPane.add(scrollPane);
		
		bd_l = new JLabel("\uC0DD\uC77C(yyyy-mm-dd)");
		bd_l.setHorizontalAlignment(SwingConstants.RIGHT);
		bd_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		bd_l.setBounds(0, 122, 182, 24);
		contentPane.add(bd_l);
		
		bd_t = new JTextField();
		bd_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		bd_t.setColumns(10);
		bd_t.setBounds(196, 119, 98, 24);
		contentPane.add(bd_t);
		
		email_l = new JLabel("\uC774\uBA54\uC77C");
		email_l.setHorizontalAlignment(SwingConstants.RIGHT);
		email_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 18));
		email_l.setBounds(118, 158, 64, 24);
		contentPane.add(email_l);
		
		pn_l = new JLabel("\uC804\uD654\uBC88\uD638");
		pn_l.setHorizontalAlignment(SwingConstants.RIGHT);
		pn_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 18));
		pn_l.setBounds(101, 198, 81, 24);
		contentPane.add(pn_l);
		
		email_t = new JTextField();
		email_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		email_t.setColumns(10);
		email_t.setBounds(196, 156, 162, 24);
		contentPane.add(email_t);
		
		pn_t = new JTextField();
		pn_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		pn_t.setColumns(10);
		pn_t.setBounds(196, 196, 162, 24);
		contentPane.add(pn_t);
		
		JButton reg_b = new JButton("\uB4F1\uB85D");
		reg_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Statement state = null;
				String name = name_t.getText();
				String bd = bd_t.getText();
				String sex;
				String em = email_t.getText();
				String pn = pn_t.getText();
				String num = null;
				if(list.getSelectedValue().equals("남")) sex = "m";
				else sex = "f";
				try {
					state = conn.createStatement();
					String sql;
					sql = "select count(*)+1 as total\r\n" + 
							"from fan;";
					ResultSet rs = state.executeQuery(sql);
					if(rs.next()) num = rs.getString("total");
					sql = "insert into fan(fnum,fname,sex,bd,email,pn) values\r\n" + 
							"("+num+",\""+name+"\",\""+sex+"\",\""+ bd + "\",\""+em+"\",\""+pn+"\");";
					if(state.executeUpdate(sql)==1)
					{
						JOptionPane.showMessageDialog(null, "입력에 성공했습니다!");
						name_t.setText("");
						bd_t.setText("");
						email_t.setText("");
						pn_t.setText("");
					}
					else JOptionPane.showMessageDialog(null, "입력에 실패했습니다!");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					JOptionPane.showMessageDialog(null, "입력에 실패했습니다!");
				}finally {
					try {
						if(state!=null)
							state.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			}
		});
		reg_b.setBackground(Color.ORANGE);
		reg_b.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		reg_b.setBounds(308, 58, 105, 80);
		contentPane.add(reg_b);
		this.setVisible(true);
	}
}
