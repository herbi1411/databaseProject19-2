import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class SelectView extends JFrame {

	private JPanel contentPane;
	public SelectView(Connection conn) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 454, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(3, 2, 5, 5));
		
		JButton income_b = new JButton("\uC544\uC774\uB3CC\uBCC4\uC218\uC775");
		income_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Idol_income(conn);
			}
		});
		income_b.setBackground(Color.ORANGE);
		income_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		contentPane.add(income_b);
		
		JButton noshow_b = new JButton("\uBD88\uCC38\uBA85\uB2E8\uAD00\uB9AC");
		noshow_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Noshow(conn);
			}
		});
		noshow_b.setBackground(Color.ORANGE);
		noshow_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		contentPane.add(noshow_b);
		
		JButton fans_b = new JButton("\uD32C \uC870\uD68C");
		fans_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Fansearch_r(conn);
			}
		});
		fans_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		fans_b.setBackground(Color.ORANGE);
		contentPane.add(fans_b);
		
		JButton idols_b = new JButton("\uC544\uC774\uB3CC \uC870\uD68C");
		idols_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Idolsearch_r(conn);
			}
		});
		idols_b.setBackground(Color.ORANGE);
		idols_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		contentPane.add(idols_b);
		
		JButton fani_b = new JButton("\uD32C \uCD94\uAC00");
		fani_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new Fan_insert(conn);
			}
		});
		fani_b.setBackground(Color.ORANGE);
		fani_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		contentPane.add(fani_b);
		
		JButton idoli_b = new JButton("\uC544\uC774\uB3CC \uCD94\uAC00");
		idoli_b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Idol_insert(conn);
			}
		});
		idoli_b.setBackground(Color.ORANGE);
		idoli_b.setFont(new Font("210 ∏«πﬂ¿«√ª√· B", Font.PLAIN, 20));
		contentPane.add(idoli_b);
		
		this.setVisible(true);
	}

}
