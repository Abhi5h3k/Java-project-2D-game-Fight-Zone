package game_abhi;

// Bullet class is used to manage and draw both player1 and player2 bullets. 

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;

public class Bullet {
	private static int time = 0;

	// Bullet Position : Player1(x1,y1) ,Player2(x2,y2)

	private int x1, y1, x2, y2;

	// Which player shoot so that we can decide from where the bullet must draw i.e
	// Player1=left->right or player2=right->left

	private char p;

	// Save last bullet hit location so that a hit image can e drawn there giving a
	// bullet hit effect:
	// Player1(Dx1,Dy1) ,Player2(Dx2,Dy2)

	private static int Dx1 = 0, Dy1 = 0, Dx2 = 0, Dy2 = 0;

	// Bullet speed : This determines the speed of the bullet

	private int bulletSpeed;

	// Check if Bullet cross the game Frame then remove it from arrayList<>
	// So that unnecessary bullets are not stored in arrayList<>

	protected static boolean bullet1CrossBox = false;
	protected static boolean bullet2CrossBox = false;

	// Sound Effects

	private String wooho = "woohoo.wav";
	// Bellow are some effects that I wanted player to say, whenever the bullet
	// passes few inch over the head.
	// private String closeHit1 = "come-on-1.wav";
	// private String closeHit2 = "goddamnit.wav";

	// Game end

	private int end = 0;
	protected static boolean gameover = false;

	protected Bullet() {

		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
	}

	protected Bullet(int x, int y, char p) {

		// Player 1 bullet start point

		this.x1 = Player.x1;
		this.y1 = Player.y1;

		// Player 2 bullet start point

		this.x2 = Player.x2;
		this.y2 = Player.y2;

		// which player shoot
		this.p = p;

		bulletSpeed = 4;
	}

	// Bullet update method
	protected void tick() {
		// This time is checked so that in the end : game over and other stuff can be
		// shown for some time then the game exit.
		if (gameover)
			time++;

		// If player one shoot and player one is alive then :
		if (p == '1' && Player.alive2) {

			// Increment bullet position on x axis to wards right.

			x1 += bulletSpeed;

			// Check if bullet hits opponent
			if (contains(Player.getP2Rect(), new Point(x1 + 45, y1 + 95))) {

				// Save last bullet hit position (Dx1,Dy1)
				Dx1 = x1 + 95;
				Dy1 = y1 + 95;
				// Set player2 alive to false
				Player.alive2 = false;
				Player.sef.play(wooho);
				// set game end to 1
				end = 1;

			}

			// check if the fired bullet cross the frame boundary i.e it did not hit
			// opponent and crossed the view. So this bullet is of no use to us.
			if (x1 >= Display.width)
				bullet1CrossBox = true;
			else
				bullet1CrossBox = false;

			if (end == 1) {

				for (int i = 1; i <= end; i++) {

					winner();

				}

			}

		}

		// If player two shoot and player two is alive then :

		if (p == '2' && Player.alive1) {
			x2 -= bulletSpeed;

			if (x2 <= 0)
				bullet2CrossBox = true;
			else
				bullet2CrossBox = false; // if not false then all bullets will be removed as soon as one bullet cross
											// the frame.

			// Bullet Collision check against p1
			if (contains(Player.getP1Rect(), new Point(x2, y2 + 95))) {
				Dx2 = x2 - 35;
				Dy2 = y2 + 95;
				Player.alive1 = false;
				Player.sef.play(wooho);
				end = 1;
			}

			if (end == 1) {
				for (int i = 1; i <= end; i++) {
					winner();

				}
			}
		}
	}

	// This method checks if the given bullet point lies inside the player
	// rectangle.
	protected static boolean contains(Rectangle r, Point p) {
		return p.x >= r.x && p.y >= r.y && p.x <= r.x + 50 && p.y <= r.y + r.height;
	}

	// Draw Bullet
	protected void render(Graphics g) {

		// Draw Bullet for Player 1

		if (p == '1' && Player.alive2) {

			g.drawImage(LoadImage.bullet1, x1 + 95, y1 + 95, 9, 10, null);

		}
		// If Bullet hit player 2
		if (Player.alive2 != true) {
			// if (check1 == 1)
			g.drawImage(LoadImage.Hitbullet1, Dx1, Dy1, 30, 30, null);

		}

		// Draw Bullet for Player 2
		if (p == '2' && Player.alive1) {

			// -9 , using (- width) to flip horizontally the same bullet image, so that 2
			// different image are not needed.
			g.drawImage(LoadImage.bullet1, x2, y2 + 95, -9, 10, null);

		}
		// If Bullet hit player 1
		if (Player.alive1 != true) {
			// if (check2 == 1)

			g.drawImage(LoadImage.Hitbullet1, Dx2, Dy2, 30, 30, null);

		}

		// When game ends gameover is set to true and this part is run
		if (gameover) {
			gameEnd(g);
		}

	}

	protected void winner() {

		if (Player.alive1 == false && Player.alive2 == true) {
			SqlLite.insertData(PlayerSelection.name1, PlayerSelection.name2, PlayerSelection.name2);
		}
		if (Player.alive1 == true && Player.alive2 == false) {
			SqlLite.insertData(PlayerSelection.name1, PlayerSelection.name2, PlayerSelection.name1);
		}
		gameover = true;

	}

	protected void gameEnd(Graphics g) {
		if (time < 300) {
			if (time < 200)
				g.drawImage(LoadImage.gameOver, Display.width / 2 - LoadImage.gameOver.getWidth(),
						Display.height / 2 - LoadImage.gameOver.getHeight(), LoadImage.gameOver.getWidth(),
						LoadImage.gameOver.getHeight(), null);
			if (time >= 200)
				g.drawImage(LoadImage.goodBye, Display.width / 2 - LoadImage.goodBye.getWidth() - 50,
						Display.height / 2 - LoadImage.goodBye.getHeight(), LoadImage.goodBye.getWidth(),
						LoadImage.goodBye.getHeight(), null);
		} else
			System.exit(0);

	}

}
