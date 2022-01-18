package fr.defretiereduteyrat.tetris.loop;

import fr.defretiereduteyrat.tetris.controller.Mover;
import fr.defretiereduteyrat.tetris.game.GameState;

/**
 * The type Update loop.
 */
public class UpdateLoop extends Loop {

    /**
     * The Mover.
     */
    private final Mover mover;

    /**
     * Instantiates a new Update loop.
     *
     * @param gameState the game state
     * @param mover     the mover
     */
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
