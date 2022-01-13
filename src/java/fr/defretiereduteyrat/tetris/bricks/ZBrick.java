package fr.defretiereduteyrat.tetris.bricks;

public class ZBrick extends Brick {

    public ZBrick() {
        blocks[0] = new Block(-1f, -1f, this.COLOR);
        blocks[1] = new Block(0f, -1f, COLOR);
        blocks[2] = new Block(0f, 0f, COLOR);
        blocks[3] = new Block(1f, 0f, COLOR);
    }
}
