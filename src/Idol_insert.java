import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import java.awt.Component;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Idol_insert extends JFrame {

	private JPanel contentPane;
	private JTextField in_t;
	private JTextField dd_t;
	private JList<String> list;
	private JList<String> list2;
	private Vector<String> vec1;
	private Vector<String> vec2;

	public Idol_insert(Connection conn) {
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel iname_l = new JLabel("\uC544\uC774\uB3CC\uC774\uB984");
		iname_l.setHorizontalAlignment(SwingConstants.RIGHT);
		iname_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		iname_l.setBounds(71, 47, 79, 18);
		contentPane.add(iname_l);
		
		JLabel com_l = new JLabel("\uC18C\uC18D\uC0AC");
		com_l.setHorizontalAlignment(SwingConstants.RIGHT);
		com_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		com_l.setBounds(71, 89, 79, 18);
		contentPane.add(com_l);
		
		JLabel type_l = new JLabel("\uADF8\uB8F9\uC720\uD615");
		type_l.setHorizontalAlignment(SwingConstants.RIGHT);
		type_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		type_l.setBounds(71, 139, 79, 18);
		contentPane.add(type_l);
		
		JLabel dd_l = new JLabel("\uB370\uBDD4\uC77C(yyyy-mm-dd)");
		dd_l.setHorizontalAlignment(SwingConstants.RIGHT);
		dd_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 13));
		dd_l.setBounds(0, 169, 150, 18);
		contentPane.add(dd_l);
		
		in_t = new JTextField();
		in_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		in_t.setBounds(179, 42, 116, 24);
		contentPane.add(in_t);
		in_t.setColumns(10);
		
		dd_t = new JTextField();
		dd_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		dd_t.setColumns(10);
		dd_t.setBounds(179, 166, 116, 24);
		contentPane.add(dd_t);
		
		vec1 = new Vector<String>();
		Statement state = null;
		try {
			state = conn.createStatement();
			String sql;
			sql = "select ename\r\n" + 
					"from entertainment\r\n" + 
					"order by eno;";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()) {vec1.addElement(rs.getString("ename"));}
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
		
		list = new JList<String>();
		list.setVisibleRowCount(3);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		list.setListData(vec1);
		list.setSelectedIndex(0);

		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBounds(164, 85, 144, 24);
		contentPane.add(scrollPane);
		
		vec2 = new Vector<String>();
		list2 = new JList<String>();
		vec2.addElement("걸그룹");
		vec2.addElement("보이그룹");
		list2.setVisibleRowCount(2);
		list2.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list2.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		list2.setListData(vec2);
		list2.setSelectedIndex(0);
		
		JScrollPane scrollPane2 = new JScrollPane(list2);
		scrollPane2.setBounds(179, 133, 116, 24);
		contentPane.add(scrollPane2);
		
		JButton rg_b = new JButton("\uB4F1\uB85D");
		rg_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String num =  null;
				String idolName = in_t.getText();
				String enName = list.getSelectedValue();
				String en = Integer.toString(list.getSelectedIndex()+1);
				String idolType = null;
				String debutDate = dd_t.getText();
				if(list2.getSelectedValue().equals("걸그룹")) idolType = "g";
				else idolType = "b";
				Statement state = null;
				try {
					state = conn.createStatement();
					String sql;
					sql = "select count(*)+1 as total\r\n" + 
							"from idol;";
					ResultSet rs = state.executeQuery(sql);
					if(rs.next()) {num = rs.getString("total");}
					
					sql = "insert into idol(inum,eno,iname,itype,debutdate) values\r\n" + 
							"("+num+","+en+",\""+idolName+"\",\""+idolType+"\",\""+debutDate+"\");";
					if(state.executeUpdate(sql)==1)
					{
						JOptionPane.showMessageDialog(null, "입력에 성공했습니다!");
						in_t.setText("");
						dd_t.setText("");
					}
					else
					{
						JOptionPane.showMessageDialog(null, "입력에 실패했습니다!");
					}
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null, "입력에 실패했습니다!");
				}finally {
					try {
						if(state!=null)
							state.close();
						} catch (SQLException e2) {
							// TODO Auto-generated catch block
							e2.printStackTrace();
						}
				}
			}
		});
		rg_b.setBackground(Color.ORANGE);
		rg_b.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		rg_b.setBounds(313, 80, 105, 77);
		contentPane.add(rg_b);
		
		this.setVisible(true);
	}
}
