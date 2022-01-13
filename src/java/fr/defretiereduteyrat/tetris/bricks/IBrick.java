package fr.defretiereduteyrat.tetris.bricks;

public class IBrick extends Brick {

    public IBrick() {
        blocks[0] = new Block(-1.5f, -0.5f, COLOR);
        blocks[1] = new Block(-0.5f, -0.5f, COLOR);
        blocks[2] = new Block(0.5f, -0.5f, COLOR);
        blocks[3] = new Block(1.5f, -0.5f, COLOR);
    }
}
