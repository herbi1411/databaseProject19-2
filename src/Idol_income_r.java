import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.ScrollPaneConstants;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Idol_income_r extends JFrame {

	private JPanel contentPane;
	private JTable table;

	public Idol_income_r(Connection conn, String idolName, String date) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String header[] = {"팬 이름", "전화번호", "수익", "굿즈구매횟수","콘서트관람횟수"};
		DefaultTableModel model;
		table = new JTable(new DefaultTableModel(null, header));
		table.getColumnModel().getColumn(1).setPreferredWidth(128);
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
			sql = " create view goods_i AS\r\n" + 
					" select f.fnum, f.fname, f.pn, sum(g.price) as gi, count(distinct fg.fgnum) as gpc\r\n" + 
					" from fan f, idol i, goods g, fangoods fg \r\n" + 
					" where i.iname = \""+ idolName +"\"and g.inum = i.inum and g.gnum = fg.gnum and f.fnum = fg.fnum \r\n" + 
					" group by f.fnum\r\n" + 
					" having count(*)>=1\r\n" + 
					" order by sum(g.price);";
			state.executeUpdate(sql);
			sql = 
					" create view con_i AS\r\n" + 
					" select f.fnum, f.fname, f.pn, sum(fc.income) as ci, count(*) as cpc\r\n" + 
					" from fan f, idol i, concert c, concertdate cd,fanconcert fc\r\n" + 
					" where i.iname = \""+ idolName + "\"  and c.inum = i.inum and c.connum = cd.connum and cd.condate >\""+date+"\" and fc.cdnum = cd.cdnum and fc.fnum = f.fnum and c.connum = cd.connum -- 콘서트\r\n" + 
					" group by f.fnum\r\n" + 
					" having count(*)>=1\r\n" + 
					" order by sum(fc.income);";
			state.executeUpdate(sql);
			sql = 
					"select g.fname,g.pn,sum(g.gi + c.ci) AS income, g.gpc as gnum, c.cpc as cnum \r\n" + 
					"from  goods_i g , con_i c\r\n" + 
					"where g.fname = c.fname and g.gi is not null and c.ci is not null\r\n" + 
					"group by g.fname\r\n" + 
					"union\r\n" + 
					"select g.fname,g.pn,sum(g.gi) AS income, g.gpc, 0 as cnum\r\n" + 
					"from goods_i g\r\n" + 
					"where g.fname not in(\r\n" + 
					"select fname\r\n" + 
					"from con_i)\r\n" + 
					"group by g.fname\r\n" + 
					"union\r\n" + 
					"select c.fname,c.pn,sum(c.ci) AS income, 0 as gnum,  c.cpc as cnum\r\n" + 
					"from con_i c\r\n" + 
					"where c.fname not in(\r\n" + 
					"select fname\r\n" + 
					"from goods_i)\r\n" + 
					"group by c.fname\r\n" + 
					"order by income desc;\r\n";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String fname = rs.getString("fname");
				String fpn = rs.getString("pn");
				String ci = rs.getString("income");
				String gn = rs.getString("gnum");
				String cn = rs.getString("cnum");
				System.out.println(fname + " " + fpn + "  " + ci + "  " + gn + " " + cn);
				model.addRow(new Object[] {fname,fpn,ci,gn,cn});
			}
			
			sql = " drop view con_i;\r\n";
			state.executeUpdate(sql);
			sql = " drop view goods_i;";
			state.executeUpdate(sql);
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
