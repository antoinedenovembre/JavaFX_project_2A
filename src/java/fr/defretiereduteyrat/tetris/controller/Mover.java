package fr.defretiereduteyrat.tetris.controller;

import fr.defretiereduteyrat.tetris.game.GameState;
import org.jetbrains.annotations.NotNull;

/**
 * The type Mover.
 */
public abstract class Mover {

    /**
     * Fall.
     *
     * @param gameState the game state
     */
    public abstract void fall(@NotNull GameState gameState);

    /**
     * Left.
     *
     * @param gameState the game state
     */
    public abstract void left(@NotNull GameState gameState);

    /**
     * Right.
     *
     * @param gameState the game state
     */
    public abstract void right(@NotNull GameState gameState);

    /**
     * Rotate.
     *
     * @param gameState the game state
     */
    public abstract void rotate(@NotNull GameState gameState);
}
