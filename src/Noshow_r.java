import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
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

public class Noshow_r extends JFrame {

	private JPanel contentPane;
	private JTable table;
	
	public Noshow_r(Connection conn, String idolName, String nscount) {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 588, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		String header[] = {"ÆÒ ÀÌ¸§", "ÀüÈ­¹øÈ£", "ºÒÂü È½¼ö"};
		DefaultTableModel model;
		table = new JTable(new DefaultTableModel(null, header));
		table.getColumnModel().getColumn(1).setPreferredWidth(128);
		contentPane.setLayout(new BorderLayout(0, 0));
		table.setFont(new Font("210 ¸Ç¹ßÀÇÃ»Ãá B", Font.PLAIN, 15));
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
			 sql = " select f.fname, f.pn, count(*)AS \"ºÒÂüÈ½¼ö\"\r\n" + 
			 		"from fan f, idol i, idolfan idf, program p,  appearance a, fanprogram fp\r\n" + 
			 		"where i.iname = \""+ idolName + "\" and f.fnum = idf.fnum  and i.inum = idf.inum and fp.fnum = f.fnum and p.pnum = fp.pnum and p.pnum = a.pnum and a.inum = i.inum\r\n" + 
			 		"group by f.fnum\r\n" + 
			 		"having count(*)>=" + nscount + "\r\n" + 
			 		"order by count(*) desc";
			ResultSet rs = state.executeQuery(sql);
			while(rs.next()){
				String fname = rs.getString("f.fname");
				String fpn = rs.getString("f.pn");
				String nsc = rs.getString("ºÒÂüÈ½¼ö");
				System.out.println(fname + " " + fpn + "  " + nsc);
				model.addRow(new Object[] {fname,fpn,nsc});
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
