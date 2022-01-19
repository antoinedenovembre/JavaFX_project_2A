package fr.defretiereduteyrat.tetris;

import fr.defretiereduteyrat.tetris.controller.Renderer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import java.util.Objects;

/**
 * The type App.
 */
public class App extends Application {

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        RootFXController.setRendererSupplier(Renderer::new);

        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/scene/root.fxml")));
        Scene sc = new Scene(root);
        sc.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/style/app.css")).toExternalForm());
        primaryStage.setTitle("Tetris");
        primaryStage.setScene(sc);
        primaryStage.setResizable(false);
        primaryStage.setOnCloseRequest(e -> {
            Platform.exit();
            System.exit(0);
        });
        primaryStage.show();
    }
}
