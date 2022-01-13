package fr.defretiereduteyrat.tetris.loop;

import fr.defretiereduteyrat.tetris.controller.Mover;
import fr.defretiereduteyrat.tetris.game.GameState;

public class UpdateLoop extends Loop {

    private final Mover mover;

    public UpdateLoop(GameState gameState, Mover mover) {
        super(gameState, 2);
        this.mover = mover;
    }

    @Override
    public void execute() {
        if (!gameState.isGameOver()) {
            gameState.checkRows();
            mover.fall(gameState);
        }
    }
}
