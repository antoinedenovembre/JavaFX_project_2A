package fr.defretiereduteyrat.tetris.game;

import fr.defretiereduteyrat.tetris.bricks.*;
import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * The type Grid.
 */
public class Grid {

    /**
     * The constant ROWS.
     */
    public static final int ROWS = 20;
    /**
     * The constant COLUMNS.
     */
    public static final int COLUMNS = 10;

    /**
     * The Matrix.
     */
    public Block[][] matrix;

    /**
     * Instantiates a new Grid.
     */
    public Grid() {
        matrix = new Block[ROWS][COLUMNS];
    }

    /**
     * Place brick.
     *
     * @param brick the brick
     * @param x     the x
     * @param y     the y
     */
    public void placeBrick(@NotNull Brick brick, float x, float y) {
        for (Block block : brick.getBlocks()) {
            setBlock(block, (int)(x + block.getOffsetX()), (int)(y + block.getOffsetY()));
        }
    }

    /**
     * Delete line.
     *
     * @param lineY the line y
     */
    public void deleteLine(int lineY) {
        Arrays.fill(matrix[lineY], null); // delete line
        System.arraycopy(matrix, 0, matrix, 1, lineY); // lower every line
        Arrays.fill(matrix[0], null);
    }

    /**
     * Is line full boolean.
     *
     * @param lineY the line y
     * @return the boolean
     */
    public boolean isLineFull(int lineY) {
        for (int x = 0; x < COLUMNS; x++) {
            if (matrix[lineY][x] == null) {
                return false;
            }
        }
        return true;
    }

    /**
     * Display game over.
     */
    public void displayGameOver() {
        Block b = new Block(0, 0, Color.BLACK);

        setBlock(b, 1, 6);
        setBlock(b, 3, 6);
        setBlock(b, 6, 6);
        setBlock(b, 8, 6);
        setBlock(b, 2, 7);
        setBlock(b, 7, 7);
        setBlock(b, 1, 8);
        setBlock(b, 3, 8);
        setBlock(b, 6, 8);
        setBlock(b, 8, 8);
        setBlock(b, 2, 12);
        setBlock(b, 3, 12);
        setBlock(b, 4, 12);
        setBlock(b, 5, 12);
        setBlock(b, 6, 12);
        setBlock(b, 7, 12);
        setBlock(b, 1, 13);
        setBlock(b, 4, 13);
        setBlock(b, 7, 13);
        setBlock(b, 8, 13);
        setBlock(b, 4, 14);
        setBlock(b, 7, 14);
        setBlock(b, 5, 15);
        setBlock(b, 6, 15);
    }

    /**
     * Get blocks block matrix.
     *
     * @return the block matrix
     */
    public Block[][] getBlocks() {
        return matrix;
    }

    /**
     * Sets a block in the matrix.
     *
     * @param block the block
     * @param x X coordinate
     * @param y Y coordinate
     */
    private void setBlock(Block block, int x, int y) {
        matrix[y][x] = block;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        for (Block[] blocks : matrix) {
            for (Block block : blocks) {
                if (block == null)
                    str.append(0);
                else
                    str.append(1);
            }
            str.append('\n');
        }
        return str.toString();
    }
}
