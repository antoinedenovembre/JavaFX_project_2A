package fr.defretiereduteyrat.tetris;

import fr.defretiereduteyrat.tetris.bricks.*;
import fr.defretiereduteyrat.tetris.controller.ConcreteMover;
import fr.defretiereduteyrat.tetris.controller.Mover;
import fr.defretiereduteyrat.tetris.game.GameState;
import fr.defretiereduteyrat.tetris.game.Grid;
import fr.defretiereduteyrat.tetris.loop.AnimationTimerExt;
import fr.defretiereduteyrat.tetris.loop.UpdateLoop;
import fr.defretiereduteyrat.tetris.supplier.RendererSupplier;
import fr.defretiereduteyrat.tetris.controller.Renderer;
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

public class RootFXController {

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
    public static RendererSupplier getRendererSupplier() {
        return rendererSupplier;
    }
    public static void setRendererSupplier(RendererSupplier newRendererSupplier) {
        rendererSupplier = newRendererSupplier;
    }

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
    @FXML private Button    startButton;
    @FXML private GridPane  gameGridPane;
    @FXML private FlowPane  sidebar;
    @FXML private Text      upcomingText;
    @FXML private GridPane  upcomingGridPane;
    @FXML private Text      scoreText;
    @FXML private Text      lineText;

    /**
     * Game Components
     */
    private GameState   gameState;
    private Mover       mover;
    private Renderer    renderer;

    private Thread updateThreadLoop;
    private AnimationTimer renderLoop;

    StringProperty scoreTextProp = new SimpleStringProperty();
    StringProperty linesTextProp = new SimpleStringProperty();

    @FXML
    public void initialize() {
        initFXComponents();
        initGameComponents();
    }

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
    private void initCssClass() {
        startButton.getStyleClass().add("bouton-start");
        sidebar.setOrientation(Orientation.VERTICAL);
        sidebar.getStyleClass().add("sidebar");
        upcomingText.getStyleClass().add("text");
        upcomingGridPane.getStyleClass().add("grilleProchaineForme");
        scoreText.getStyleClass().add("text");
        lineText.getStyleClass().add("text");
    }
    private void resetScore() {
        scoreTextProp.setValue("Score : " + gameState.getScore());
        scoreText.textProperty().bindBidirectional(scoreTextProp);
        linesTextProp.setValue("Lines : " + gameState.getLines());
        lineText.textProperty().bindBidirectional(linesTextProp);
    }

    private void initGameComponents() {
        gameState = new GameState(gameGridPane);
        resetScore();
        mover = new ConcreteMover();
        renderer = getRendererSupplier().createRenderer();
        if (gameState.getCurrent() instanceof OBrick || gameState.getCurrent() instanceof IBrick) {
            gameState.setPlacementX(gameState.getPlacementX() - 0.5f);
            gameState.setPlacementY(gameState.getPlacementY() + 0.5f);
        }
    }

    private void startGame() {
        setHandler();
        gameState.reset();
        gameState.setRunning(true);
        startButton.setText("Stop");

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
    private void stopGame() {
        updateThreadLoop.interrupt();
        renderLoop.stop();

        gameState.reset();
        startButton.setText("Start");
        resetGridPane(gameGridPane);
        resetGridPane(upcomingGridPane);
        gameState.displayGameOver();
        renderer.renderGrid(gameState.getGameGridPane(), gameState.getGrid());
    }

    private void setHandler() {
        startButton.getScene().setOnKeyPressed(this::handle);
    }
    private void handle(@NotNull KeyEvent event) {
        switch (event.getCode()) {
            case Z, W -> Platform.runLater(() -> mover.rotate(gameState));
            case Q, A -> Platform.runLater(() -> mover.left(gameState));
            case D -> Platform.runLater(() -> mover.right(gameState));
            case S -> Platform.runLater(() -> mover.fall(gameState));
        }
    }

    public Button getStartButton() {
        return startButton;
    }
    public GridPane getGameGridPane() {
        return gameGridPane;
    }
    public FlowPane getSidebar() {
        return sidebar;
    }
    public Text getScoreText() {
        return scoreText;
    }
    public Text getLineText() {
        return lineText;
    }
    public Text getUpcomingText() {
        return upcomingText;
    }
    public GridPane getUpcomingGridPane() {
        return upcomingGridPane;
    }
    public Mover getMover() {
        return mover;
    }
    public GameState getGameState() {
        return gameState;
    }
}
