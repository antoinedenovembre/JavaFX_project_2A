package fr.defretiereduteyrat.tetris.controller;

import fr.defretiereduteyrat.tetris.bricks.Block;
import fr.defretiereduteyrat.tetris.bricks.Brick;
import fr.defretiereduteyrat.tetris.game.Grid;
import fr.defretiereduteyrat.tetris.utils.ImageUtils;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

public class Renderer {

    public void renderGrid(GridPane gridPane, Grid grid) {
        for (int y = 0; y < Grid.ROWS; y++) {
            for (int x = 0; x < Grid.COLUMNS; x++) {
                final Block block = grid.matrix[y][x];
                if (block != null)
                    renderBlock(gridPane, block, x, y);
            }
        }
    }

    public void renderBrick(GridPane gridPane, @NotNull Brick brick, float x, float y) {
        for (int i = 0; i < brick.getBlocks().length; i++) {
            final Block block = brick.getBlocks()[i];
            final int columnIndex = (int)(x + block.getOffsetX());
            final int rowIndex = (int)(y + block.getOffsetY());
            renderBlock(gridPane, brick.getBlocks()[i], columnIndex, rowIndex);
        }
    }

    public void renderBlock(GridPane gridPane, Block block, int columnIndex, int rowIndex) {
        if (columnIndex < 0 || columnIndex >= Grid.COLUMNS || rowIndex < 0 || rowIndex >= Grid.ROWS) {
            return;
        }
        final ImageView imageView = ImageUtils.getBlockImageView(block.getColor());
        gridPane.add(imageView, columnIndex, rowIndex);
    }
}
