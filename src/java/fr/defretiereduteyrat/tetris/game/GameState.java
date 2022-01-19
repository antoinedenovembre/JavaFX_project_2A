package fr.defretiereduteyrat.tetris.game;

import fr.defretiereduteyrat.tetris.bricks.*;
import fr.defretiereduteyrat.tetris.controller.Collider;
import fr.defretiereduteyrat.tetris.utils.BackupManager;
import fr.defretiereduteyrat.tetris.utils.BrickUtils;
import javafx.scene.layout.GridPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;

/**
 * The type Game state.
 */
public class GameState {

    /**
     * The Game GridPane.
     */
    private final GridPane gameGridPane;

    /**
     * The Game Grid.
     */
    private Grid     grid;

    /**
     * The Score.
     */
    private int      score;

    /**
     * The Lines.
     */
    private int      lines;

    /**
     * The Upcoming Brick.
     */
    private Brick    upcoming;

    /**
     * The Current Brick.
     */
    private Brick    current;

    /**
     * The X coordinate for placement.
     */
    private float    placementX;

    /**
     * The Y coordinate for placement.
     */
    private float    placementY;

    /**
     * The Best Score.
     */
    private int bestScore;

    /**
     * The Media Player
     */
    private final MediaPlayer mediaPlayer;

    /**
     * The isRunning variable.
     */
    private boolean running;

    /**
     * Instantiates a new Game state.
     *
     * @param gameGridPane the game grid pane
     * @throws Exception the exception
     */
    public GameState(GridPane gameGridPane) throws Exception {
        this.gameGridPane = gameGridPane;
        bestScore = BackupManager.readSaveFile();
        mediaPlayer = new MediaPlayer(new Media(new File("resources/music/soundtrack.wav").toURI().toString()));
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
            mediaPlayer.play();
        });
        mediaPlayer.setVolume(mediaPlayer.getVolume());
        reset();
    }

    /**
     * Reset.
     */
    public void reset() {
        grid = new Grid();
        score = 0;
        lines = 0;
        upcoming = BrickUtils.getRandomBrick();
        current = BrickUtils.getRandomBrick();
        placementX = Grid.COLUMNS / 2f;
        placementY = 1f;
        running = false;
    }

    /**
     * Is game over boolean.
     *
     * @return the boolean
     */
    public boolean isGameOver() {
        return !Collider.canPlaceBlock(grid, Grid.COLUMNS / 2, 0);
    }

    /**
     * Brick switch.
     */
    public void brickSwitch() {
        current = upcoming;
        upcoming = BrickUtils.getRandomBrick();
        placementX = Grid.COLUMNS / 2f;
        placementY = 1f;
    }

    /**
     * Place brick.
     */
    public void placeBrick() {
        grid.placeBrick(current, placementX, placementY);
    }

    /**
     * Check rows.
     */
    public void checkRows() {
        for (int y = 0; y < Grid.ROWS; y++) {
            if (grid.isLineFull(y)) {
                grid.deleteLine(y);
                addLines(1);
                addScore(100);
            }
        }
    }

    /**
     * Display game over.
     */
    public void displayGameOver() {
        grid = new Grid();
        grid.displayGameOver();
    }

    /**
     * Starts the soundtrack
     */
    public void startSoundtrack() {
        mediaPlayer.play();
    }

    /**
     * Stops the soundtrack
     */
    public void stopSoundtrack() {
        mediaPlayer.stop();
    }

    /**
     * Gets game grid pane.
     *
     * @return the game grid pane
     */
    public GridPane getGameGridPane() {
        return gameGridPane;
    }

    /**
     * Gets grid.
     *
     * @return the grid
     */
    public Grid getGrid() {
        return grid;
    }

    /**
     * Sets grid.
     *
     * @param grid the grid
     */
    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Add score.
     *
     * @param toAdd the to add
     */
    public void addScore(int toAdd) {
        this.score += toAdd;
    }

    /**
     * Gets lines.
     *
     * @return the lines
     */
    public int getLines() {
        return lines;
    }

    /**
     * Sets lines.
     *
     * @param lines the lines
     */
    public void setLines(int lines) {
        this.lines = lines;
    }

    /**
     * Add lines.
     *
     * @param toAdd the to add
     */
    public void addLines(int toAdd) {
        this.lines += toAdd;
    }

    /**
     * Gets upcoming.
     *
     * @return the upcoming
     */
    public Brick getUpcoming() {
        return upcoming;
    }

    /**
     * Sets upcoming.
     *
     * @param upcoming the upcoming
     */
    public void setUpcoming(Brick upcoming) {
        this.upcoming = upcoming;
    }

    /**
     * Gets current.
     *
     * @return the current
     */
    public Brick getCurrent() {
        return current;
    }

    /**
     * Sets current.
     *
     * @param current the current
     */
    public void setCurrent(Brick current) {
        this.current = current;
    }

    /**
     * Gets placement x.
     *
     * @return the placement x
     */
    public float getPlacementX() {
        return placementX;
    }

    /**
     * Sets placement x.
     *
     * @param placementX the placement x
     */
    public void setPlacementX(float placementX) {
        this.placementX = placementX;
    }

    /**
     * Add placement x.
     *
     * @param toAdd the to add
     */
    public void addPlacementX(float toAdd) {
        this.placementX += toAdd;
    }

    /**
     * Sub placement x.
     *
     * @param toSub the to sub
     */
    public void subPlacementX(float toSub) {
        this.placementX -= toSub;
    }

    /**
     * Gets placement y.
     *
     * @return the placement y
     */
    public float getPlacementY() {
        return placementY;
    }

    /**
     * Sets placement y.
     *
     * @param placementY the placement y
     */
    public void setPlacementY(float placementY) {
        this.placementY = placementY;
    }

    /**
     * Add placement y.
     *
     * @param toAdd the to add
     */
    public void addPlacementY(float toAdd) {
        this.placementY += toAdd;
    }

    /**
     * Gets best score.
     *
     * @return the best score
     */
    public int getBestScore() {
        return bestScore;
    }

    /**
     * Sets best score.
     *
     * @param bestScore the best score
     */
    public void setBestScore(int bestScore) {
        this.bestScore = bestScore;
    }

    /**
     * Is running boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return running;
    }

    /**
     * Sets running.
     *
     * @param running the running
     */
    public void setRunning(boolean running) {
        this.running = running;
    }
}
