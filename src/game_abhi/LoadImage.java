package game_abhi;

//This class loads all the images path for resources and lable icons

import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class LoadImage {

	// Game Background

	protected static BufferedImage building;
	protected static BufferedImage ground;
	protected static BufferedImage grass;
	protected static BufferedImage rock;

	// Player 1 :
	protected static BufferedImage player1;
	protected static BufferedImage player1_walk;
	protected static BufferedImage player1_walk2;
	protected static BufferedImage Deadplayer1;
	// Player 2 :
	protected static BufferedImage player2;
	protected static BufferedImage player2_walk;
	protected static BufferedImage player2_walk2;
	protected static BufferedImage Deadplayer2;

	// Bullet :

	protected static BufferedImage bullet1;
	protected static BufferedImage Hitbullet1;

	// Start menu
	protected static ImageIcon title;
	protected static ImageIcon play;
	protected static ImageIcon score;
	protected static ImageIcon credits;
	protected static ImageIcon exit;

	// chose player
	protected static ImageIcon cP1;
	protected static ImageIcon cP2;
	// protected static ImageIcon cPlayerImg1;
	// protected static ImageIcon cPlayerImg2;
	// protected static ImageIcon ready;
	// protected static ImageIcon sword;
	protected static ImageIcon vs;

	// Helper
	protected static BufferedImage gameOver;
	protected static BufferedImage goodBye;
	protected static ImageIcon back;

	// credits
	protected static ImageIcon creator;
	protected static ImageIcon creator_name;
	protected static ImageIcon iLjava;
	protected static ImageIcon motu;
	protected static ImageIcon music;
	protected static ImageIcon jlayer;
	protected static ImageIcon title_track;
	protected static ImageIcon sqlLite;
	protected static ImageIcon stackOverflow;
	protected static ImageIcon defense_line;

	private InputStream fis = null;
	private BufferedInputStream bis = null;

	protected LoadImage() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->

	}

	protected static void init() {

		LoadImage load = new LoadImage();

		back = load.iconLoader("/back.png");
		gameOver = load.imageLoader("gameover.png");
		goodBye = load.imageLoader("goodbye.png");

		// start menu
		title = load.iconLoader("/title.png");
		play = load.iconLoader("/play.png");
		score = load.iconLoader("/score.png");
		credits = load.iconLoader("/credits.png");
		exit = load.iconLoader("/exit.png");
		// start menu ----> ends

		// V/s menu :
		cP1 = load.iconLoader("/1.png");
		cP2 = load.iconLoader("/2.png");
		vs = load.iconLoader("/vs.png");

		// credits :
		creator = load.iconLoader("/creator.png");
		creator_name = load.iconLoader("/abhishek_bhardwaj.png");
		iLjava = load.iconLoader("/love_java.png");
		music = load.iconLoader("/music.png");
		jlayer = load.iconLoader("/jlayer.png");
		title_track = load.iconLoader("/title_track.png");
		sqlLite = load.iconLoader("/sqllite.png");
		defense_line = load.iconLoader("/defense_line.png");
		motu = load.iconLoader("/motu.png");
		stackOverflow = load.iconLoader("/stackoverflow.png");

		// Player 1 :
		player1 = load.imageLoader("AplayerR2.png");
		player1_walk = load.imageLoader("AplayerR2_walk.png");
		player1_walk2 = load.imageLoader("AplayerR2_walk2.png");
		Deadplayer1 = load.imageLoader("DplayerR2.png");
		// Player 2 :
		player2 = load.imageLoader("AplayerL.png");
		player2_walk = load.imageLoader("AplayerL_walk.png");
		player2_walk2 = load.imageLoader("AplayerL_walk2.png");
		Deadplayer2 = load.imageLoader("DplayerL.png");

		// Bullet :

		bullet1 = load.imageLoader("bullet1.png");
		Hitbullet1 = load.imageLoader("bullet_hole.png");

		// Game Background :
		ground = load.imageLoader("ground5.jpg");
		building = load.imageLoader("building.png");
		grass = load.imageLoader("ground4.png");

		rock = load.imageLoader("rock.png");
		// NOTE: // in eclipse case in path does not mater but in jar runnable it gives
		// error, so rock.png works in eclipse even if the real name of file was
		// Rock.png but this was not working in jar runnable.

	}

	protected BufferedImage imageLoader(String path) {
		try {
			fis = getClass().getClassLoader().getResourceAsStream(path);
			bis = new BufferedInputStream(fis);
			return ImageIO.read(bis);
		} catch (IOException e) {

			e.printStackTrace();
			System.exit(1);// exit with 1 to accept errors
		}

		return null;
	}

	protected ImageIcon iconLoader(String path) {
		// https://stackoverflow.com/questions/25635636/eclipse-exported-runnable-jar-not-showing-images
		URL resource = LoadImage.class.getResource(path);
		return new ImageIcon(resource);
	}

}