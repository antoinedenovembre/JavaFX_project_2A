package fr.defretiereduteyrat.tetris.bricks;

/**
 * The type O brick.
 */
public class OBrick extends Brick {

    /**
     * Instantiates a new O brick.
     */
    public OBrick() {
        blocks[0] = new Block(-0.5f, -0.5f, COLOR);
        blocks[1] = new Block(-0.5f, 0.5f, COLOR);
        blocks[2] = new Block(0.5f, -0.5f, COLOR);
        blocks[3] = new Block(0.5f, 0.5f, COLOR);
    }
}
