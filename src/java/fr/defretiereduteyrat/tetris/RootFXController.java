package fr.defretiereduteyrat.tetris;

import fr.defretiereduteyrat.tetris.bricks.Block;
import fr.defretiereduteyrat.tetris.bricks.IBrick;
import fr.defretiereduteyrat.tetris.bricks.OBrick;
import fr.defretiereduteyrat.tetris.controller.ConcreteMover;
import fr.defretiereduteyrat.tetris.controller.Mover;
import fr.defretiereduteyrat.tetris.controller.Renderer;
import fr.defretiereduteyrat.tetris.game.GameState;
import fr.defretiereduteyrat.tetris.game.Grid;
import fr.defretiereduteyrat.tetris.loop.AnimationTimerExt;
import fr.defretiereduteyrat.tetris.loop.UpdateLoop;
import fr.defretiereduteyrat.tetris.supplier.RendererSupplier;
import fr.defretiereduteyrat.tetris.utils.BackupManager;
import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.geometry.Orientation;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import org.jetbrains.annotations.NotNull;

/**
 * The type Root fx controller.
 */
public class RootFXController  {

    /**
     * STATIC
     */
    static {
        rendererSupplier = Renderer::new;
    }

    /**
     * Suppliers
     */
    private static RendererSupplier rendererSupplier;

    /**
     * Gets renderer supplier.
     *
     * @return the renderer supplier
     */
    public static RendererSupplier getRendererSupplier() {
        return rendererSupplier;
    }

    /**
     * Sets renderer supplier.
     *
     * @param newRendererSupplier the new renderer supplier
     */
    public static void setRendererSupplier(RendererSupplier newRendererSupplier) {
        rendererSupplier = newRendererSupplier;
    }

    /**
     * Reset grid pane.
     *
     * @param gridPane the grid pane
     */
    public static void resetGridPane(@NotNull GridPane gridPane) {
        gridPane.getChildren().clear();
        for (int y = 0; y < gridPane.getRowCount(); y++) {
            for (int x = 0; x < gridPane.getColumnCount(); x++) {
                Pane pane = new Pane();
                pane.getStyleClass().add("game-grid-cell");
                gridPane.add(pane, x, y);
            }
        }
    }

    /**
     * OBJECT
     */
    /**
     * FX Components
     */

    /**
     * The Start Button.
     */
    @FXML private Button    startButton;

    /**
     * The Game GridPane.
     */
    @FXML private GridPane  gameGridPane;

    /**
     * The Sidebar.
     */
    @FXML private FlowPane  sidebar;

    /**
     * The Upcoming Text.
     */
    @FXML private Text      upcomingText;

    /**
     * The Upcoming GridPane.
     */
    @FXML private GridPane  upcomingGridPane;

    /**
     * The Score Text.
     */
    @FXML private Text      scoreText;

    /**
     * The Line Text.
     */
    @FXML private Text      lineText;

    /**
     * The Best Score Text.
     */
    @FXML private Text bestScoreText;

    /**
     * Game Components
     */

    /**
     * The Game State.
     */
    private GameState   gameState;

    /**
     * The Mover.
     */
    private Mover       mover;

    /**
     * The Renderer.
     */
    private Renderer    renderer;

    /**
     * The Update Loop.
     */
    private Thread updateThreadLoop;

    /**
     * The Render Loop.
     */
    private AnimationTimer renderLoop;

    /**
     * The Score text prop.
     */
    StringProperty scoreTextProp = new SimpleStringProperty();
    /**
     * The Lines text prop.
     */
    StringProperty linesTextProp = new SimpleStringProperty();
    /**
     * The Best score prop.
     */
    StringProperty bestScoreProp = new SimpleStringProperty();


    /**
     * Initialize.
     */
    @FXML
    public void initialize() {
        initGameComponents();
        initFXComponents();
    }

    /**
     * Initializes FX Components.
     */
    private void initFXComponents() {
        startButton.setOnAction(e -> {
            if (!gameState.isRunning())
                startGame();
            else
                stopGame();
        });
        initGridPane(gameGridPane, Grid.COLUMNS, Grid.ROWS);
        initGridPane(upcomingGridPane, 5, 2);
        initCssClass();
    }

    /**
     * Initializes a GridPane
     *
     * @param gridPane the grid pane
     * @param width the width
     * @param height the height
     */
    private void initGridPane(@NotNull GridPane gridPane, int width, int height) {
        gridPane.getStyleClass().add("game-grid");
        for (int y = 0; y < height; y++) {
            RowConstraints row = new RowConstraints(Block.SIZE);
            gridPane.getRowConstraints().add(row);
        }
        for (int x = 0; x < width; x++) {
            ColumnConstraints column = new ColumnConstraints(Block.SIZE);
            gridPane.getColumnConstraints().add(column);
        }
        resetGridPane(gridPane);
    }
    /**
     * Initializes CSS classes.
     */
    private void initCssClass() {
        startButton.getStyleClass().add("bouton-start");
        sidebar.setOrientation(Orientation.VERTICAL);
        sidebar.getStyleClass().add("sidebar");
        upcomingText.getStyleClass().add("text");
        upcomingGridPane.getStyleClass().add("grilleProchaineForme");
        scoreText.getStyleClass().add("text");
        lineText.getStyleClass().add("text");
        bestScoreText.getStyleClass().add("text");
    }
    /**
     * Resets the score part of the window.
     */
    private void resetScore() {
        scoreTextProp.setValue("Score : " + gameState.getScore());
        scoreText.textProperty().bindBidirectional(scoreTextProp);
        linesTextProp.setValue("Lines : " + gameState.getLines());
        lineText.textProperty().bindBidirectional(linesTextProp);
        bestScoreProp.setValue("Best score : " + gameState.getBestScore());
        bestScoreText.textProperty().bindBidirectional(bestScoreProp);
    }

    /**
     * Initializes Game Components.
     */
    private void initGameComponents() {
        try {
            gameState = new GameState(gameGridPane);
            resetScore();
            mover = new ConcreteMover();
            renderer = getRendererSupplier().createRenderer();
            if (gameState.getCurrent() instanceof OBrick || gameState.getCurrent() instanceof IBrick) {
                gameState.setPlacementX(gameState.getPlacementX() - 0.5f);
                gameState.setPlacementY(gameState.getPlacementY() + 0.5f);
            }
        } catch ( Exception e ) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Starts the game.
     */
    private void startGame() {
        setHandler();
        gameState.reset();
        gameState.setRunning(true);
        startButton.setText("Stop");
        gameState.startSoundtrack();

        updateThreadLoop = new Thread(new UpdateLoop(gameState, mover));
        renderLoop = new AnimationTimerExt(17) {

            public void handle() {
                if (gameState.isGameOver()){
                    stopGame();
                } else {
                    resetGridPane(gameGridPane);
                    resetGridPane(upcomingGridPane);
                    renderer.renderGrid(gameState.getGameGridPane(), gameState.getGrid());
                    renderer.renderBrick(upcomingGridPane, gameState.getUpcoming(), 2, 1);
                    renderer.renderBrick(gameGridPane, gameState.getCurrent(), gameState.getPlacementX(), gameState.getPlacementY());
                    resetScore();
                }
            }
        };
        renderLoop.start();
        updateThreadLoop.start();
    }
    /**
     * Stops the game.
     */
    private void stopGame() {
        gameState.stopSoundtrack();

        if(gameState.getBestScore() < gameState.getScore()){
            gameState.setBestScore(gameState.getScore());
            try {
                BackupManager.saveToFile(gameState.getScore());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        updateThreadLoop.interrupt();
        renderLoop.stop();

        gameState.reset();
        startButton.setText("Start");
        resetGridPane(gameGridPane);
        resetGridPane(upcomingGridPane);
        gameState.displayGameOver();
        renderer.renderGrid(gameState.getGameGridPane(), gameState.getGrid());
    }

    /**
     * Add the Key Event handler to the scene.
     */
    private void setHandler() {
        startButton.getScene().setOnKeyPressed(this::handle);
    }

    /**
     * Handles Key Events
     *
     * @param event the event
     */
    private void handle(@NotNull KeyEvent event) {
        switch (event.getCode()) {
            case Z, W -> Platform.runLater(() -> mover.rotate(gameState));
            case Q, A -> Platform.runLater(() -> mover.left(gameState));
            case D -> Platform.runLater(() -> mover.right(gameState));
            case S -> Platform.runLater(() -> mover.fall(gameState));
        }
    }

    /**
     * Gets start button.
     *
     * @return the start button
     */
    public Button getStartButton() {
        return startButton;
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
     * Gets sidebar.
     *
     * @return the sidebar
     */
    public FlowPane getSidebar() {
        return sidebar;
    }

    /**
     * Gets score text.
     *
     * @return the score text
     */
    public Text getScoreText() {
        return scoreText;
    }

    /**
     * Gets line text.
     *
     * @return the line text
     */
    public Text getLineText() {
        return lineText;
    }

    /**
     * Gets upcoming text.
     *
     * @return the upcoming text
     */
    public Text getUpcomingText() {
        return upcomingText;
    }

    /**
     * Gets upcoming grid pane.
     *
     * @return the upcoming grid pane
     */
    public GridPane getUpcomingGridPane() {
        return upcomingGridPane;
    }

    /**
     * Gets mover.
     *
     * @return the mover
     */
    public Mover getMover() {
        return mover;
    }

    /**
     * Gets game state.
     *
     * @return the game state
     */
    public GameState getGameState() {
        return gameState;
    }
}
