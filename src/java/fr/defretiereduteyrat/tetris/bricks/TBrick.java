package fr.defretiereduteyrat.tetris.bricks;

/**
 * The type T brick.
 */
public class TBrick extends Brick {

    /**
     * Instantiates a new T brick.
     */
    public TBrick() {
        blocks[0] = new Block(0f, -1f, COLOR);
        blocks[1] = new Block(-1f, 0f, COLOR);
        blocks[2] = new Block(0f, 0f, COLOR);
        blocks[3] = new Block(1f, 0f, COLOR);
    }
}
