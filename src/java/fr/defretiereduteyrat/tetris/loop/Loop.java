package fr.defretiereduteyrat.tetris.loop;

import fr.defretiereduteyrat.tetris.game.GameState;

/**
 * The type Loop.
 */
public abstract class Loop implements Runnable {

	/**
	 * The Game state.
	 */
	protected final GameState gameState;

	/**
	 * The Framerate.
	 */
	private final int framerate;

	/**
	 * The previous time.
	 */
	private long lastLoopTimeMillis;

	/**
	 * Instantiates a new Loop.
	 *
	 * @param gameState the game state
	 * @param framerate the framerate
	 */
	public Loop(GameState gameState, int framerate) {
		this.gameState = gameState;
		this.framerate = 1000 / framerate;
		this.lastLoopTimeMillis = 0;
	}

	@Override
	public void run() {
		while (gameState.isRunning()) {
			final long currentTimeMillis = System.currentTimeMillis();
			final long diff = currentTimeMillis - lastLoopTimeMillis;
			if (diff >= framerate) {
				lastLoopTimeMillis = currentTimeMillis;
				execute();
			}
		}
	}

	/**
	 * Execute.
	 */
	public abstract void execute();

	/**
	 * Gets framerate.
	 *
	 * @return the framerate
	 */
	public int getFramerate() {
		return framerate;
	}

	/**
	 * Gets last loop time millis.
	 *
	 * @return the last loop time millis
	 */
	public long getLastLoopTimeMillis() {
		return lastLoopTimeMillis;
	}

	/**
	 * Sets last loop time millis.
	 *
	 * @param lastLoopTimeMillis the last loop time millis
	 */
	public void setLastLoopTimeMillis(long lastLoopTimeMillis) {
		this.lastLoopTimeMillis = lastLoopTimeMillis;
	}
}
