package fr.defretiereduteyrat.tetris.bricks;

import javafx.scene.paint.Color;

import java.util.Random;

public class Brick {

    protected Block[] blocks;
    protected final Color COLOR = getRandomColor();

    protected Brick() {
        this.blocks = new Block[4];
    }

    public Block[] getBlocks() {
        return blocks;
    }

    public Brick rotate() {
        Brick newBrick = new Brick();
        for (int i = 0; i < blocks.length; i++) {
            final Block block = blocks[i];
            newBrick.getBlocks()[i] = new Block(-block.getOffsetY(), block.getOffsetX(), block.getColor());
        }
        return newBrick;
    }

    private Color getRandomColor() {
        int random = new Random().nextInt(7);
        return switch (random) {
            case 0 -> Color.GREEN;
            case 1 -> Color.MAGENTA;
            case 2 -> Color.BLUE;
            case 3 -> Color.RED;
            case 4 -> Color.ORANGE;
            case 5 -> Color.YELLOW;
            case 6 -> Color.CYAN;
            default -> Color.GREY;
        };
    }
}
