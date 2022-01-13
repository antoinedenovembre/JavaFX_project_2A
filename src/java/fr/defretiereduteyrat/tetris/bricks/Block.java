package fr.defretiereduteyrat.tetris.bricks;

import javafx.scene.paint.Color;

public class Block {

    public static final float SIZE = 30;

    private float offsetX;
    private float offsetY;
    private Color color;

    public Block(float offsetX, float offsetY, Color color) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.color = color;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
