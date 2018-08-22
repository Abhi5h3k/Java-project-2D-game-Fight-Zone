package game_abhi;

//This class draw the game background

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;

public class WarGround {
	protected static int bx;
	protected static int by;
	protected static ArrayList<Rectangle> bushes = new ArrayList<Rectangle>();// creating new generic arraylist

	protected WarGround() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
	}

	protected void init() {

	}

	protected void tick() {

	}

	protected void render(Graphics g) {

		g.drawImage(LoadImage.building, 0, 0, Display.width, Display.height * 1 / 3, null);
		g.drawImage(LoadImage.ground, 0, Display.height * 1 / 3, Display.width, Display.height * 2 / 3, null);

		for (int i = (Display.height * 1 / 3); i < Display.height; i = (i + LoadImage.rock.getHeight())) {

			g.drawImage(LoadImage.rock, (Display.width / 2 - 10), i, LoadImage.rock.getWidth(),
					LoadImage.rock.getHeight(), null);
		}

		g.drawImage(LoadImage.grass, 0, Display.height - LoadImage.grass.getHeight() * 1 / 2, Display.width,
				LoadImage.grass.getHeight() * 2 / 3, null);
	}

	// TRsting bushes array
	protected void testBushes(Graphics g) {
		g.setColor(Color.RED);
		for (Rectangle obj : bushes) {
			g.drawRect(obj.x, obj.y, obj.width, obj.height);
		}
	}

}
