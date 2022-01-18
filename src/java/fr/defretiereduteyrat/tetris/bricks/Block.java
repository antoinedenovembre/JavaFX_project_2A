package fr.defretiereduteyrat.tetris.bricks;

import javafx.scene.paint.Color;

/**
 * The type Block.
 */
public class Block {

    /**
     * The constant SIZE.
     */
    public static final float SIZE = 30;

    /**
     * The Offset on X axis.
     */
    private float offsetX;

    /**
     * The Offset on Y axis.
     */
    private float offsetY;

    /**
     * The Color.
     */
    private Color color;

    /**
     * Instantiates a new Block.
     *
     * @param offsetX the offset x
     * @param offsetY the offset y
     * @param color   the color
     */
    public Block(float offsetX, float offsetY, Color color) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.color = color;
    }

    /**
     * Gets offset x.
     *
     * @return the offset x
     */
    public float getOffsetX() {
        return offsetX;
    }

    /**
     * Sets offset x.
     *
     * @param offsetX the offset x
     */
    public void setOffsetX(float offsetX) {
        this.offsetX = offsetX;
    }

    /**
     * Gets offset y.
     *
     * @return the offset y
     */
    public float getOffsetY() {
        return offsetY;
    }

    /**
     * Sets offset y.
     *
     * @param offsetY the offset y
     */
    public void setOffsetY(float offsetY) {
        this.offsetY = offsetY;
    }

    /**
     * Gets color.
     *
     * @return the color
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets color.
     *
     * @param color the color
     */
    public void setColor(Color color) {
        this.color = color;
    }
}
