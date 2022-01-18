package fr.defretiereduteyrat.tetris.bricks;

/**
 * The type J brick.
 */
public class JBrick extends Brick {

    /**
     * Instantiates a new J brick.
     */
    public JBrick() {
        blocks[0] = new Block(-1f, -1f, COLOR);
        blocks[1] = new Block(-1f, 0f, COLOR);
        blocks[2] = new Block(-0f, 0f, COLOR);
        blocks[3] = new Block(1f, 0f, COLOR);
    }
}
