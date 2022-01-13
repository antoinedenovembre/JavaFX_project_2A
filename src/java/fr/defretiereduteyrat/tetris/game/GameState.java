package fr.defretiereduteyrat.tetris.game;

import fr.defretiereduteyrat.tetris.bricks.*;
import fr.defretiereduteyrat.tetris.controller.Collider;
import fr.defretiereduteyrat.tetris.utils.BrickUtils;
import javafx.scene.layout.GridPane;

public class GameState {

    private final GridPane gameGridPane;
    private Grid     grid;
    private int      score;
    private int      lines;
    private Brick    upcoming;
    private Brick    current;
    private float    placementX;
    private float    placementY;

    private boolean running;

    public GameState(GridPane gameGridPane) {
        this.gameGridPane = gameGridPane;
        reset();
    }

    public void reset() {
        grid = new Grid();
        score = 0;
        lines  = 0;
        upcoming = BrickUtils.getRandomBrick();
        current = BrickUtils.getRandomBrick();
        placementX = Grid.COLUMNS / 2f;
        placementY = 1f;
        running = false;
    }

    public boolean isGameOver() {
        return !Collider.canPlaceBlock(grid, Grid.COLUMNS / 2, 0);
    }

    public void brickSwitch() {
        current = upcoming;
        upcoming = BrickUtils.getRandomBrick();
        placementX = Grid.COLUMNS / 2f;
        placementY = 1f;
    }

    public void placeBrick() {
        grid.placeBrick(current, placementX, placementY);
    }

    public void checkRows() {
        for (int y = 0; y < Grid.ROWS; y++) {
            if (grid.isLineFull(y)) {
                grid.deleteLine(y);
                addLines(1);
                addScore(100);
            }
        }
    }

    public void displayGameOver() {
        grid = new Grid();
        grid.displayGameOver();
    }

    public GridPane getGameGridPane() {
        return gameGridPane;
    }
    public Grid getGrid() {
        return grid;
    }
    public void setGrid(Grid grid) {
        this.grid = grid;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void addScore(int toAdd) {
        this.score += toAdd;
    }
    public int getLines() {
        return lines;
    }
    public void setLines(int lines) {
        this.lines = lines;
    }
    public void addLines(int toAdd) {
        this.lines += toAdd;
    }
    public Brick getUpcoming() {
        return upcoming;
    }
    public void setUpcoming(Brick upcoming) {
        this.upcoming = upcoming;
    }
    public Brick getCurrent() {
        return current;
    }
    public void setCurrent(Brick current) {
        this.current = current;
    }
    public float getPlacementX() {
        return placementX;
    }
    public void setPlacementX(float placementX) {
        this.placementX = placementX;
    }
    public void addPlacementX(float toAdd) {
        this.placementX += toAdd;
    }
    public void subPlacementX(float toSub) {
        this.placementX -= toSub;
    }
    public float getPlacementY() {
        return placementY;
    }
    public void setPlacementY(float placementY) {
        this.placementY = placementY;
    }
    public void addPlacementY(float toAdd) {
        this.placementY += toAdd;
    }
    public boolean isRunning() {
        return running;
    }
    public void setRunning(boolean running) {
        this.running = running;
    }
}
