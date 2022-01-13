package fr.defretiereduteyrat.tetris.loop;

import fr.defretiereduteyrat.tetris.game.GameState;

public abstract class Loop implements Runnable {

	protected final GameState gameState;
	private final int framerate;
	private long lastLoopTimeMillis;

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

	public abstract void execute();

	public int getFramerate() {
		return framerate;
	}
	public long getLastLoopTimeMillis() {
		return lastLoopTimeMillis;
	}
	public void setLastLoopTimeMillis(long lastLoopTimeMillis) {
		this.lastLoopTimeMillis = lastLoopTimeMillis;
	}
}
