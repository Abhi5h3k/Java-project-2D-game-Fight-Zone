package game_abhi;

//This class play Title track

import java.io.BufferedInputStream;
import java.io.InputStream;

import javazoom.jl.player.Player;

//Music credits: https://www.dl-sounds.com/royalty-free/defense-line/
//https://stackoverflow.com/questions/14180023/loading-an-mp3-file-with-jlayer-from-inside-the-jar
//http://www.dreamincode.net/forums/topic/298897-running-an-mp3-file-with-javazooms-jlayer-from-a-jar-file-problem/
public class TitleTrack {

	private javazoom.jl.player.Player player;
	private Thread mp3Thread;

	private InputStream fis = null;
	private BufferedInputStream bis = null;

	private String songPath = "DefenseLine.mp3";

	protected TitleTrack() {
		// <-------For DEbugging------->
		//System.out.println(getClass().getName() + " : " + Debug.getCallerCallerClassName());
		//new Debug().getTime();
		// <-------For DEbugging------->
		if (Runner.running)
			play();
	}

	protected void play() {

		try {
			fis = getClass().getClassLoader().getResourceAsStream(songPath);
			bis = new BufferedInputStream(fis);
			player = new Player(bis);

			mp3Thread = new Thread() {
				@Override
				public void run() {
					try {
						player.play();
					} catch (Exception e) {
						System.out.println(e);
						System.exit(1);
					}
				}
			};

			mp3Thread.start();

		} catch (Exception e) {
			System.out.println("Exception in Title track thread");
			System.out.println(e);
			System.exit(1);

		}

	}

	protected synchronized void close() {

		try {
			System.out.println("closing title track thread");
			player.close();
			fis.close();
			bis.close();
			mp3Thread.join();
		} catch (Exception e) {
		}

	}

}
