package fr.defretiereduteyrat.tetris.controller;

import fr.defretiereduteyrat.tetris.bricks.Block;
import fr.defretiereduteyrat.tetris.bricks.Brick;
import fr.defretiereduteyrat.tetris.bricks.IBrick;
import fr.defretiereduteyrat.tetris.bricks.OBrick;
import fr.defretiereduteyrat.tetris.game.Grid;
import org.jetbrains.annotations.NotNull;

/**
 * The type Collider.
 */
public abstract class Collider {

    /**
     * Can place brick boolean.
     *
     * @param grid  the grid
     * @param brick the brick
     * @param x     the x
     * @param y     the y
     * @return the boolean
     */
    public static boolean canPlaceBrick(Grid grid, @NotNull Brick brick, float x, float y) {
        for (Block block : brick.getBlocks()) {
            int columnIndex;
            int rowIndex = (int) (y + block.getOffsetY());
            if (brick instanceof OBrick || brick instanceof IBrick) {
                columnIndex = (int) (x + block.getOffsetX() - 0.5f);
            } else {
                columnIndex = (int) (x + block.getOffsetX());
            }
            if (!canPlaceBlock(grid, columnIndex, rowIndex))
                return false;
        }
        return true;
    }

    /**
     * Can place block boolean.
     *
     * @param grid the grid
     * @param x    the x
     * @param y    the y
     * @return the boolean
     */
// WARNING DO NOT REVERT, easier to understand and use that way
    public static boolean canPlaceBlock(Grid grid, int x, int y) {
        return x >= 0 && x < Grid.COLUMNS && y >= 0 && y < Grid.ROWS && grid.getBlocks()[y][x] == null;
    }
}
