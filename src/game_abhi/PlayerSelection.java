package game_abhi;

// Edit the design, for example you can add player images like counter strike player selection.
//I have not added the validation for user to enter the names before starting the game, user can directly click start without entering the name.

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PlayerSelection implements MouseListener {

	protected static String name1, name2;
	private JTextField p1Name, p2Name;
	private JPanel panel;
	protected JFrame frame;
	private JLabel start;

	protected PlayerSelection() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
		createPlayerSelection();

	}

	protected void createPlayerSelection() {

		frame = new JFrame(Display.title);
		frame.setPreferredSize(new Dimension(Display.width, Display.height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setUndecorated(true);

		panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		panel.setPreferredSize(new Dimension(Display.width, Display.height));
		panel.setBackground(Color.decode("#4D4D4D"));

		JPanel blackTint = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();

		blackTint.setPreferredSize(new Dimension(Display.width, Display.height - 200));
		blackTint.setBackground(new Color(38, 38, 38, 50));

		JLabel p1 = new JLabel();
		p1.setIcon(LoadImage.cP1);
		blackTint.add(p1);
		p1Name = new JTextField(20);
		blackTint.add(p1Name);
		JLabel title = new JLabel();
		title.setIcon(LoadImage.vs);
		blackTint.add(title);

		p2Name = new JTextField(20);

		blackTint.add(p2Name);
		JLabel p2 = new JLabel();
		p2.setIcon(LoadImage.cP2);

		blackTint.add(p2);

		c.gridx = 2;
		c.gridy = 3;
		start = new JLabel();
		start.setIcon(LoadImage.play);
		start.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		start.addMouseListener(this);
		blackTint.add(start, c);

		panel.add(blackTint, gbc);
		frame.add(panel);
		frame.setResizable(false);
		frame.pack();
		frame.setVisible(true);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == start) {
			Runner.startGame = true;
			name1 = p1Name.getText();
			name2 = p2Name.getText();

			if (name1.length() <= 0)
				name1 = "Player1";
			if (name2.length() <= 0)
				name2 = "Player2";

			frame.setVisible(false);
			Display.frame.setVisible(true);

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
