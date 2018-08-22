package game_abhi;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Display {

	// Get the screen size
	private static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	protected static int height = (int) screenSize.getHeight();
	protected static int width = (int) screenSize.getWidth();

	// Game Title
	protected static String title = "Fight ZOne";

	// Canvas and JFrame
	private Canvas canvas;
	protected static JFrame frame;

	protected Display() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
		createDisplay();
	}

	protected void createDisplay() {

		frame = new JFrame(title);
		frame.setPreferredSize(new Dimension(width, height));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setUndecorated(true);
		// add to frame
		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(width, 300));
		canvas.setBackground(Color.decode("#ffcc00"));

		frame.add(canvas);
		//

		frame.setResizable(false);
		frame.pack();
		frame.setVisible(Runner.startGame);
	}

	// Used to get the canvas from other class
	protected Canvas getCanvas() {
		return canvas;
	}
}
