package fr.defretiereduteyrat.tetris.controller;

import fr.defretiereduteyrat.tetris.game.GameState;
import org.jetbrains.annotations.NotNull;

public abstract class Mover {

    public abstract void fall(@NotNull GameState gameState);

    public abstract void left(@NotNull GameState gameState);

    public abstract void right(@NotNull GameState gameState);

    public abstract void rotate(@NotNull GameState gameState);
}
