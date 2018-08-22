package game_abhi;

//This is the heart of the game the game loop, I have not coded an efficient game loop. You can help me with that. 

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Runner implements Runnable {

	private static Thread thread;
	protected static boolean running;
	private BufferStrategy buffer;
	private Graphics g;
	private Display display;
	private Manager manager;
	protected static boolean startGame = false;
	protected int ticks = 0;
	protected static Runner runner = new Runner();

	protected static Runner getRunner() {
		return runner;
	}

	protected void init() {
		// <-------For DEbugging------->
		// System.out.println(getClass().getName() + " : " +
		// Debug.getCallerCallerClassName());
		// new Debug().getTime();
		// <-------For DEbugging------->

		// Load all the game resource image
		System.out.println("Loading all resources");
		LoadImage.init();
		// Create a Start menu
		new GameMenu();

		// Call the Display and Manager in background
		display = new Display();
		manager = new Manager();
		manager.init();

	}

	// Start Game thread
	protected synchronized void start() {

		// If already running then just return
		if (running) {
			return;
		}
		// Else make running true
		running = true;

		// If no thread is create d i.e it is first time. Then create the thread.
		if (thread == null) {
			System.out.println("Create Game Thread");
			thread = new Thread(this);
			System.out.println("Game Thread start");
			thread.start();
			System.out.println("Call to title track");
			new TitleTrack();

		}
	}

	// To stop the Thread, Called when Exit is clicked
	protected static synchronized void stop() {

		try {
			running = false;
			System.out.println("closeing runner thread");
			new TitleTrack().close();
			thread.join();
			System.out.println("closed runner thread");
			System.exit(0);
		} catch (InterruptedException e) {

			e.printStackTrace();
			System.exit(0);
		}

	}

	// Game update loop
	protected void tick() {

		manager.tick();

	}

	@Override
	public void run() {
		
		// call init() once only before the game loop
		init();
		
		// https://docs.oracle.com/javase/1.5.0/docs/api/java/lang/System.html#nanoTime()

		long initialTime = System.nanoTime();
		final double timeU = 1000000000 / 60;

		double deltaU = 0;

		while (running) {

			long currentTime = System.nanoTime();
			deltaU += (currentTime - initialTime) / timeU;

			initialTime = currentTime;

			if (deltaU >= 1) {
				tick();
				render();

				ticks++;
				deltaU--;
			}

		}

	}

	protected void render() {

		buffer = display.getCanvas().getBufferStrategy();

		// Create a BufferStratergy
		if (buffer == null) {
			display.getCanvas().createBufferStrategy(3);
			return;//
		}

		// Get the graphics object
		g = buffer.getDrawGraphics();
		g.clearRect(0, 0, Display.width, Display.height);// clear display

		// draw

		if (startGame)
			manager.render(g);

		// end draw
		buffer.show();
		g.dispose();

	}

}
