package game_abhi;

// This is the manager class that manages other class calling

import java.awt.Graphics;
import java.util.ArrayList;

public class Manager {

	private Player player;
	private WarGround warGround;

	// ArrayList to store bullets for players
	protected static ArrayList<Bullet> bullet1;
	protected static ArrayList<Bullet> bullet2;

	protected Manager() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
	}

	protected void init() {

		warGround = new WarGround();
		warGround.init();

		player = new Player();
		player.init();

		bullet1 = new ArrayList<Bullet>();
		bullet2 = new ArrayList<Bullet>();
	}

	protected void tick() {
		warGround.tick();

		player.tick();

		// Remove bullet from arrayList if bullet cross the frame
		for (int i = 0; i < bullet1.size(); i++) {
			bullet1.get(i).tick();
			if (Bullet.bullet1CrossBox)// remove bullet for player1
				bullet1.remove(i);

		}
		for (int i = 0; i < bullet2.size(); i++) {
			bullet2.get(i).tick();
			if (Bullet.bullet2CrossBox)// remove bullet for player1
				bullet2.remove(i);
		}
	}

	protected void render(Graphics g) {

		warGround.render(g);
		player.render(g);

		// Calling render method of Bullet class for each bullet
		for (int i = 0; i < bullet1.size(); i++) {
			bullet1.get(i).render(g);

		}
		for (int i = 0; i < bullet2.size(); i++) {
			bullet2.get(i).render(g);
		}

	}

}
