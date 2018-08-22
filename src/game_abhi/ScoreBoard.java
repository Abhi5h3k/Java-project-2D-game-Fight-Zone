package game_abhi;

//ScoreBoard, Kindly Improve the UI as it is very basic. 

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class ScoreBoard implements MouseListener {

	private JPanel panel;
	private JFrame frame;
	private JTable table;
	private static DefaultTableModel model;
	private JLabel back;

	protected ScoreBoard() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
		getData();
		createMenu();

	}

	protected void createMenu() {

		frame = new JFrame(Display.title);
		frame.setPreferredSize(new Dimension(Display.width, Display.height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		// add to frame
		panel = new JPanel();

		panel.setPreferredSize(new Dimension(Display.width, Display.height));
		panel.setBackground(Color.decode("#ffcc00"));

		back = new JLabel(LoadImage.back);
		back.addMouseListener(this);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.setBounds(0, 0, 100, 100);

		JLabel score = new JLabel(LoadImage.score);

		table = new JTable();
		table.setModel(model);
		panel.add(back, BorderLayout.WEST);
		panel.add(score, BorderLayout.PAGE_START);
		panel.add(new JScrollPane(table), BorderLayout.CENTER); // added a scroll pane else column header were not shown
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

	protected static void getData() {
		System.out.println("getting data");
		Connection c = null;
		Statement stmt = null;
		SqlLite.createDb();
		SqlLite.createTable();
		try {

			model = new DefaultTableModel(new String[] { "Player one ", "Player Two", "Winner" }, 0);

			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:Game_abhi_score.db");
			c.setAutoCommit(false);

			stmt = c.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM score;");

			while (rs.next()) {

				String p1Name = rs.getString(1);
				String p2Name = rs.getString(2);
				String Winner = rs.getString(3);
				model.addRow(new Object[] { p1Name, p2Name, Winner });

			}

			rs.close();
			stmt.close();
			c.close();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(1);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == back) {
			new GameMenu().frame.setVisible(true);
			this.frame.setVisible(false);

		}

	}

	@Override
	public void mousePressed(MouseEvent e) {

	}

	@Override
	public void mouseReleased(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}
}
