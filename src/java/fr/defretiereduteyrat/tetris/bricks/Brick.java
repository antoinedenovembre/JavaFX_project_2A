package fr.defretiereduteyrat.tetris.bricks;

import fr.defretiereduteyrat.tetris.utils.ImageUtils;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * The type Brick.
 */
public class Brick {

    /**
     * The Blocks.
     */
    protected Block[] blocks;
    /**
     * The Color.
     */
    protected final Color COLOR = ImageUtils.getRandomColor();

    /**
     * Instantiates a new Brick.
     */
    protected Brick() {
        this.blocks = new Block[4];
    }

    /**
     * Get blocks block [ ].
     *
     * @return the block [ ]
     */
    public Block[] getBlocks() {
        return blocks;
    }

    /**
     * Rotate brick.
     *
     * @return the brick
     */
    public Brick rotate() {
        Brick newBrick = new Brick();
        for (int i = 0; i < blocks.length; i++) {
            final Block block = blocks[i];
            newBrick.getBlocks()[i] = new Block(-block.getOffsetY(), block.getOffsetX(), block.getColor());
        }
        return newBrick;
    }
}
