import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.awt.event.ActionEvent;

public class Noshow extends JFrame {
	private Connection conn = null;
	private JPanel contentPane;
	private JTextField idoln_t;
	private JTextField nscount_t;
	private Vector<String> vec;
	private JList<String> list;

	public Noshow(Connection conn) {
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
		
		JLabel nscount_l = new JLabel("불참횟수");
		nscount_l.setForeground(Color.BLACK);
		nscount_l.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		nscount_l.setBackground(Color.ORANGE);
		nscount_l.setHorizontalAlignment(SwingConstants.CENTER);
		nscount_l.setBounds(14, 150, 110, 25);
		contentPane.add(nscount_l);
		
		nscount_t = new JTextField();
		nscount_t.setHorizontalAlignment(SwingConstants.CENTER);
		nscount_t.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		nscount_t.setColumns(10);
		nscount_t.setBounds(138, 150, 115, 24);
		contentPane.add(nscount_t);
		
		JButton btnNewButton = new JButton("\uAC80\uC0C9");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idolName = list.getSelectedValue();
				int nscount = Integer.parseInt(nscount_t.getText());
				if(nscount<=0) JOptionPane.showMessageDialog(null, "올바른 숫자를 입력해주세요!");
				else
				{
					System.out.println(idolName + " / " +nscount_t.getText());
					new Noshow_r(conn,idolName,Integer.toString(nscount));
				}
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 20));
		btnNewButton.setBounds(267, 94, 105, 72);
		contentPane.add(btnNewButton);
		
		list = new JList<String>();
		list.setVisibleRowCount(10);
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setFont(new Font("210 맨발의청춘 B", Font.PLAIN, 15));
		list.setListData(vec);
		list.setSelectedIndex(0);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setBounds(138, 85, 128, 61);
		contentPane.add(scrollPane);
		
		this.setVisible(true);
	}
}
