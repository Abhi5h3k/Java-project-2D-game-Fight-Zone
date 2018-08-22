package game_abhi;

//Please always keep the credit and add yours too, You can add a scrolling or sliding page for the more credits.
//Don't remove it, You can add yours too. 
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

public class Credits implements MouseListener {

	private JPanel panel;
	private JFrame frame;
	private JLabel back;
	private JLabel motuimg;
	private Box cred;
	private static Boolean mClicked = false;
	private JLabel creator_name;

	protected Credits() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->

		CreditsForm();

	}

	protected void CreditsForm() {

		frame = new JFrame("Credits");
		frame.setPreferredSize(new Dimension(Display.width, Display.height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);
		panel = new JPanel(new GridBagLayout());

		panel.setPreferredSize(new Dimension(Display.width, Display.height));
		panel.setBackground(Color.decode("#4D4D4D"));

		back = new JLabel();
		back.setIcon(LoadImage.back);
		back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		back.addMouseListener(this);
		panel.add(back);

		cred = new Box(1);
		Box hor = new Box(0);

		JLabel credits = new JLabel();
		credits.setIcon(LoadImage.credits);

		JLabel creator = new JLabel();
		creator.setIcon(LoadImage.creator);

		motuimg = new JLabel();
		motuimg.setIcon(LoadImage.motu);

		creator_name = new JLabel();
		creator_name.setIcon(LoadImage.creator_name);
		creator_name.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		creator_name.addMouseListener(this);

		JLabel iljava = new JLabel();
		iljava.setIcon(LoadImage.iLjava);

		JLabel sqllite = new JLabel();
		sqllite.setIcon(LoadImage.sqlLite);

		JLabel stack = new JLabel();
		stack.setIcon(LoadImage.stackOverflow);

		hor.add(iljava);
		hor.add(Box.createHorizontalStrut(50));
		hor.add(sqllite);
		hor.add(Box.createHorizontalStrut(50));
		hor.add(stack);

		JLabel music = new JLabel();
		music.setIcon(LoadImage.music);

		JLabel jlayer = new JLabel();
		jlayer.setIcon(LoadImage.jlayer);

		JLabel title_track = new JLabel();
		title_track.setIcon(LoadImage.title_track);

		JLabel title_track_song = new JLabel();
		title_track_song.setIcon(LoadImage.defense_line);

		cred.add(credits);
		cred.add(Box.createVerticalStrut(50));
		cred.add(creator);
		if (mClicked)
			cred.add(motuimg);
		cred.add(Box.createVerticalStrut(20));
		cred.add(creator_name);
		cred.add(Box.createVerticalStrut(20));
		cred.add(hor);
		cred.add(Box.createVerticalStrut(20));
		cred.add(music);
		cred.add(Box.createVerticalStrut(20));
		cred.add(jlayer);
		cred.add(Box.createVerticalStrut(20));
		cred.add(title_track);
		cred.add(Box.createVerticalStrut(20));
		cred.add(title_track_song);
		panel.add(cred);

		frame.add(panel);
		frame.setResizable(false);
		frame.pack();

		frame.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {

		if (e.getSource() == back) {
			frame.setVisible(false);
			new GameMenu().frame.setVisible(true);
		}
		if (e.getSource() == creator_name) {
			mClicked = true;

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

	}

}
