import java.awt.BorderLayout;
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

public class Idolsearch_r extends JFrame{
	private JPanel contentPane;
	private JTable table;

	public Idolsearch_r(Connection conn) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 700, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String header[] = {"아이돌 이름", "소속사", "유형","데뷔일"};
		DefaultTableModel model;
		table = new JTable(new DefaultTableModel(null, header));
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
			sql = "select i.iname, e.ename, i.itype, i.debutdate\r\n" + 
					"from idol i, entertainment e\r\n" + 
					"where i.eno = e.eno\r\n" + 
					"order by debutdate";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String iname = rs.getString("i.iname");
				String ename = rs.getString("e.ename");
				String itype = rs.getString("i.itype");
				String debutdate = rs.getString("i.debutdate");
				if(itype.equals("b")) itype = "보이그룹";
				else itype = "걸그룹";
				model.addRow(new Object[] {iname,ename,itype,debutdate});
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
