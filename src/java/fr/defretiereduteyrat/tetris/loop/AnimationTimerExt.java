package fr.defretiereduteyrat.tetris.loop;

import javafx.animation.AnimationTimer;

/**
 * The type Animation timer ext.
 */
public abstract class AnimationTimerExt extends AnimationTimer {

	/**
	 * The sleep time.
	 */
	private final long sleepNs;

	/**
	 * The previous time.
	 */
	private long prevTime = 0;

	/**
	 * Instantiates a new Animation timer ext.
	 *
	 * @param sleepMs the sleep ms
	 */
	public AnimationTimerExt(long sleepMs) {
		this.sleepNs = sleepMs * 1000000;
	}

	@Override
	public void handle(long now) {
		if ((now - prevTime) < sleepNs) {
			return;
		}
		prevTime = now;
		handle();
	}

	/**
	 * Handle.
	 */
	public abstract void handle();
}
