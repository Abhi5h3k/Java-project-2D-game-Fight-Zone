package game_abhi;

//Start menu design

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameMenu implements MouseListener {
	private JPanel panel;
	protected JFrame frame;

	private JLabel title;
	private JLabel start;
	private JLabel score;
	private JLabel credits;
	private JLabel exit;

	protected GameMenu() {
		System.out.println("Width : " + Display.width + " Height : " + Display.height);
		createMenu();
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->

	}

	protected void createMenu() {

		frame = new JFrame(Display.title);
		frame.setPreferredSize(new Dimension(Display.width, Display.height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		// add to frame
		panel = new JPanel();
		panel.setLayout(new GridBagLayout());

		panel.setPreferredSize(new Dimension(Display.width, Display.height));
		panel.setBackground(Color.decode("#141414"));

		Box menu = new Box(1);

		// NOTE: title = new JLabel(LoadImage.title); is not working in jar.

		title = new JLabel();
		title.setIcon(LoadImage.title);
		title.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		title.addMouseListener(this);

		start = new JLabel();
		start.setIcon(LoadImage.play);
		start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start.addMouseListener(this);

		score = new JLabel();
		score.setIcon(LoadImage.score);
		score.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		score.addMouseListener(this);

		credits = new JLabel();
		credits.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		credits.setIcon(LoadImage.credits);
		credits.addMouseListener(this);

		exit = new JLabel();
		exit.setIcon(LoadImage.exit);
		exit.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exit.addMouseListener(this);

		//

		panel.add(title);
		menu.add(Box.createHorizontalStrut(200));
		menu.add(start);
		menu.add(Box.createVerticalStrut(50));
		menu.add(score);
		menu.add(Box.createVerticalStrut(50));
		menu.add(credits);
		menu.add(Box.createVerticalStrut(50));
		menu.add(exit);
		panel.add(menu);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == start) {

			frame.setVisible(false);
			new PlayerSelection().frame.setVisible(true);
		}

		if (e.getSource() == score) {
			new ScoreBoard();
		}
		if (e.getSource() == credits) {
			frame.setVisible(false);
			new Credits();
		}
		if (e.getSource() == exit) {
			Runner.stop();
			System.exit(0);
		}

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
