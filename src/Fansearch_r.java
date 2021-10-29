import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Fansearch_r extends JFrame{
	private JPanel contentPane;
	private JTable table;

	public Fansearch_r(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 900, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String header[] = {"팬 이름", "전화번호", "성별","생년월일","이메일"};
		DefaultTableModel model;
		table = new JTable(new DefaultTableModel(null, header));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(128);
		table.getColumnModel().getColumn(2).setMaxWidth(30);
		table.getColumnModel().getColumn(4).setPreferredWidth(128);
		contentPane.setLayout(new BorderLayout(0, 0));
		table.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		table.setRowHeight(25);
		JScrollPane scrollPane = new JScrollPane(table);
		contentPane.add(scrollPane, BorderLayout.CENTER);
		
		model = (DefaultTableModel) table.getModel();
		table.setRowSorter(new TableRowSorter<DefaultTableModel>(model));
		this.setVisible(true);
		Statement state = null;
		try {
			state = conn.createStatement();
			String sql;
			sql = "select fname,pn,sex,bd,email\r\n" + 
					"from fan\r\n" + 
					"order by fname";
			
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String fname = rs.getString("fname");
				String fpn = rs.getString("pn");
				String sex = rs.getString("sex");
				String bd = rs.getString("bd");
				String email = rs.getString("email");
				if(sex.equals("m")) sex = "남";
				else sex = "여";
				model.addRow(new Object[] {fname,fpn,sex,bd,email});
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
}
