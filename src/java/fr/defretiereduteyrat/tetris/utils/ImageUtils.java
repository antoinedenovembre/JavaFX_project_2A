package fr.defretiereduteyrat.tetris.utils;

import fr.defretiereduteyrat.tetris.bricks.Block;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.Random;

/**
 * The type Image utils.
 */
public class ImageUtils {

	/**
	 * Sets filter.
	 *
	 * @param img   the img
	 * @param color the color
	 */
	public static void setFilter(ImageView img, Color color) {
		ColorAdjust filter = new ColorAdjust();
		filter.setSaturation(-1.0f);

		Blend blend = new Blend(
				BlendMode.MULTIPLY,
				filter,
				new ColorInput(
						0,
						0,
						img.getFitWidth(),
						img.getFitHeight(),
						color
				)
		);
		img.setEffect(blend);
	}

	/**
	 * Gets block image view.
	 *
	 * @param color the color
	 * @return the block image view
	 */
	public static ImageView getBlockImageView(Color color) {
		ImageView imageView = new ImageView("/texture/block_texture.png");
		imageView.setFitWidth(Block.SIZE);
		imageView.setFitHeight(Block.SIZE);
		setFilter(imageView, color);
		return imageView;
	}

	/**
	 * Returns a random color.
	 *
	 * @return the color
	 */
	public static Color getRandomColor() {
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
