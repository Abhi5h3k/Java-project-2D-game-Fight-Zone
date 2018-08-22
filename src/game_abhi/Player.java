package game_abhi;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.ThreadLocalRandom;

public class Player implements KeyListener {

	// Player 1 coordinates
	public static int x1 = (Display.width / 2 - 10) - 200;
	public static int y1 = Display.height / 2;
	// Player 2 coordinates
	public static int x2 = (Display.width / 2 - 10) + 50;
	public static int y2 = Display.height / 2;

	// Player Movement Direction
	private boolean left1, right1, up1, down1, left2, right2, up2, down2;
	// Fire key pressed/not
	private boolean fire1, fire2;

	// Player movement speed
	private int movementSpeed = 3;

	// Player ammo
	private static int ammo1;
	private static int ammo2;

	// Player dead or alive
	public static Boolean alive1 = true, alive2 = true;

	// Fire Interval
	int shotIntervel1 = 0;
	int shotIntervel2 = 0;

	// Sound Effects
	public static SoundEffects sef;
	private String fire = "gun-gunshot.wav";
	private String no_ammo = "GunEmpty.wav";

	public Player() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
	}

	public void init() {
		Display.frame.addKeyListener(this);

		// Player ammo
		ammo1 = 20;
		ammo2 = 20;
		// Sound Effects
		sef = new SoundEffects();

	}

	public void tick() {
		if (alive1)
			player1();
		if (alive2)
			player2();

		// For Player 1
		shotIntervel1++; // tick is 60 FPS so increase 'i' till 10

		if (fire1 && ammo1 > 0 && shotIntervel1 > 10 && alive1 && alive2) {
			Manager.bullet1.add(new Bullet(x1, y1, '1'));
			sef.play(fire);
			ammo1--;
			shotIntervel1 = 0;
		}

		// For Player 2
		shotIntervel2++;

		if (fire2 && ammo2 > 0 && shotIntervel2 > 10 && alive2 && alive1) {
			sef.play(fire);
			Manager.bullet2.add(new Bullet(x2, y2, '2'));
			ammo2--;
			shotIntervel2 = 0;
		}

		// SE Empty gun sound effect
		if ((fire1 && shotIntervel1 > 10 && alive1 && ammo1 <= 0)
				|| (fire2 && ammo2 <= 0 && shotIntervel2 > 10 && alive2)) {
			sef.play(no_ammo);
			shotIntervel1 = 0;
			shotIntervel2 = 0;
		}

	}

	// Move Player 1
	public void player1() {

		if (left1) {
			if (x1 >= 0)
				x1 -= movementSpeed;
		}
		if (right1) {
			if (x1 <= Display.width / 2 - LoadImage.player1.getWidth() * 1 / 2 - 20)
				x1 += movementSpeed;
		}
		if (up1) {
			if (y1 >= Display.height * 1 / 3 - LoadImage.player1.getHeight() * 1 / 2 + 20)
				y1 -= movementSpeed;
		}
		if (down1) {
			if (y1 <= Display.height - LoadImage.player1.getWidth() + 10)
				y1 += movementSpeed;
		}
	}

	// Move Player 2
	public void player2() {

		if (left2) {
			if (x2 >= Display.width / 2 + 20)
				x2 -= movementSpeed;
		}
		if (right2) {
			if (x2 <= Display.width - LoadImage.player2.getWidth() * 1 / 2 - 20)
				x2 += movementSpeed;
		}
		if (up2) {
			if (y2 >= Display.height * 1 / 3 - LoadImage.player2.getHeight() * 1 / 2 + 20)
				y2 -= movementSpeed;
		}
		if (down2) {
			if (y2 <= Display.height - LoadImage.player2.getWidth() + 10)
				y2 += movementSpeed;
		}
	}

	public static Rectangle getP1Rect() {
		return new Rectangle(Player.x1 + 20, Player.y1,
				LoadImage.player1.getWidth() - (LoadImage.player1.getWidth() * 1 / 2),
				LoadImage.player1.getHeight() - (LoadImage.player1.getHeight() * 1 / 2));
	}

	public static Rectangle getP2Rect() {
		return new Rectangle(Player.x2, Player.y2,
				LoadImage.player2.getWidth() - (LoadImage.player2.getWidth() * 1 / 2),
				LoadImage.player2.getHeight() - (LoadImage.player2.getHeight() * 1 / 2));
	}

	// Testing p1 coordinates
	public void testingP1(Graphics g) {

		// Draw coordinates for player1

		g.setColor(Color.RED);

		g.drawRect(Player.x1, Player.y1, 10, 10);

		g.drawRect(Player.x1 + (LoadImage.player1.getWidth() - (LoadImage.player1.getWidth() * 1 / 2)), Player.y1, 10,
				10);

		g.drawRect(Player.x1, Player.y1 + (LoadImage.player1.getHeight() - (LoadImage.player1.getHeight() * 1 / 2)), 10,
				10);

		g.drawRect(Player.x1 + (LoadImage.player1.getWidth() - (LoadImage.player1.getWidth() * 1 / 2)),
				Player.y1 + (LoadImage.player1.getHeight() - (LoadImage.player1.getHeight() * 1 / 2)), 10, 10);

		// Box Around player 1

		g.setColor(Color.blue);
		g.drawRect(Player.x1, Player.y1, LoadImage.player1.getWidth() - (LoadImage.player1.getWidth() * 1 / 2),
				LoadImage.player1.getHeight() - (LoadImage.player1.getHeight() * 1 / 2));

		// Hit line between player 1 where bullet hit is detected.

		g.setColor(Color.pink);
		g.fillRect(Player.x1 + 50, Player.y1,
				(LoadImage.player1.getWidth() - (LoadImage.player1.getWidth() * 1 / 2) - 90),
				LoadImage.player1.getHeight() - (LoadImage.player1.getHeight() * 1 / 2) + 5);
	}

	public void render(Graphics g) {

		g.setColor(Color.RED);

		// Show player ammo
		g.setFont(new Font("default", Font.BOLD, 15));

		// Show player 1 ammo

		g.setColor(Color.red);
		g.drawString(PlayerSelection.name1 + " : " + ammo1, 90, Display.height * 1 / 3 - 35);

		// Show player 2 ammo

		g.setColor(Color.black);
		g.drawString(PlayerSelection.name2 + " : " + ammo2, Display.width / 2 + 150, Display.height * 1 / 3 - 35);

		// If player 1 is alive then draw Player
		if (alive1) {

			if (up1 || left1 || down1 || right1) {
				int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				// Draw and redraw images of walking effect
				if (randomNum == 1)
					g.drawImage(LoadImage.player1_walk, x1, y1, 100, 149, null);
				if (randomNum == 2)
					g.drawImage(LoadImage.player1_walk2, x1, y1, 100, 149, null);
			} else {
				// Draw still image for standing effect
				g.drawImage(LoadImage.player1, x1, y1, 100, 149, null);
			}

		} else
			g.drawImage(LoadImage.Deadplayer1, x1, y1, 100, 149, null);

		// If player 2 is alive then draw Player
		if (alive2) {

			if (up2 || left2 || down2 || right2) {

				int randomNum = ThreadLocalRandom.current().nextInt(1, 2 + 1);
				// Draw and redraw images of walking effect
				if (randomNum == 1)
					g.drawImage(LoadImage.player2_walk, x2, y2, 100, 149, null);

				if (randomNum == 2)
					g.drawImage(LoadImage.player2_walk2, x2, y2, 100, 149, null);

			} else {
				// Draw still image for standing effect
				g.drawImage(LoadImage.player2, x2, y2, 100, 149, null);
			}
		} else
			g.drawImage(LoadImage.Deadplayer2, x2, y2, 100, 149, null);

		// Testing player1
		// testingP1(g);
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void keyPressed(KeyEvent e) {

		int source = e.getKeyCode();

		// p1
		if (source == KeyEvent.VK_D) {
			right1 = true;
		}
		if (source == KeyEvent.VK_A) {
			left1 = true;
		}
		if (source == KeyEvent.VK_W) {
			up1 = true;
		}
		if (source == KeyEvent.VK_S) {
			down1 = true;
		}

		// p2

		if (source == KeyEvent.VK_LEFT) {
			left2 = true;
		}
		if (source == KeyEvent.VK_RIGHT) {
			right2 = true;
		}
		if (source == KeyEvent.VK_UP) {
			up2 = true;
		}
		if (source == KeyEvent.VK_DOWN) {
			down2 = true;
		}

		// fire
		if (source == KeyEvent.VK_B) {
			fire1 = true;
		}
		if (source == KeyEvent.VK_NUMPAD2) {
			fire2 = true;
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {

		int source = e.getKeyCode();
		// p1

		if (source == KeyEvent.VK_D) {
			right1 = false;
		}
		if (source == KeyEvent.VK_A) {
			left1 = false;
		}
		if (source == KeyEvent.VK_W) {
			up1 = false;
		}
		if (source == KeyEvent.VK_S) {
			down1 = false;
		}

		// p2

		if (source == KeyEvent.VK_LEFT) {
			left2 = false;
		}
		if (source == KeyEvent.VK_RIGHT) {
			right2 = false;
		}
		if (source == KeyEvent.VK_UP) {
			up2 = false;
		}
		if (source == KeyEvent.VK_DOWN) {
			down2 = false;
		}

		// fire
		if (source == KeyEvent.VK_B) {
			fire1 = false;
		}
		if (source == KeyEvent.VK_NUMPAD2) {
			fire2 = false;
		}
	}

}
