package fr.defretiereduteyrat.tetris.controller;

import fr.defretiereduteyrat.tetris.game.GameState;
import org.jetbrains.annotations.NotNull;

public class ConcreteMover extends Mover {

	public void fall(@NotNull GameState gameState) {
		if (Collider.canPlaceBrick(gameState.getGrid(), gameState.getCurrent(), gameState.getPlacementX(), gameState.getPlacementY() + 1f)) {
			gameState.addPlacementY(1f);
		} else {
			gameState.placeBrick();
			gameState.addScore(10);
			gameState.brickSwitch();
		}
	}

	public void left(@NotNull GameState gameState) {
		if (Collider.canPlaceBrick(gameState.getGrid(), gameState.getCurrent(), gameState.getPlacementX() - 1f, gameState.getPlacementY())) {
			gameState.subPlacementX(1f);
		}
	}

	public void right(@NotNull GameState gameState) {
		if (Collider.canPlaceBrick(gameState.getGrid(), gameState.getCurrent(), gameState.getPlacementX() + 1f, gameState.getPlacementY())) {
			gameState.addPlacementX(1f);
		}
	}

	public void rotate(@NotNull GameState gameState) {
		if (Collider.canPlaceBrick(gameState.getGrid(), gameState.getCurrent().rotate(), gameState.getPlacementX(), gameState.getPlacementY())) {
			gameState.setCurrent(gameState.getCurrent().rotate());
		}
	}
}
