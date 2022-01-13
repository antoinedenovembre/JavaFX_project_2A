package fr.defretiereduteyrat.tetris.loop;

import javafx.animation.AnimationTimer;

public abstract class AnimationTimerExt extends AnimationTimer {

	private final long sleepNs;
	private long prevTime = 0;

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

	public abstract void handle();
}
