package fr.defretiereduteyrat.tetris.utils;

import fr.defretiereduteyrat.tetris.bricks.Block;
import javafx.scene.effect.Blend;
import javafx.scene.effect.BlendMode;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.ColorInput;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

public class ImageUtils {

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

	public static ImageView getBlockImageView(Color color) {
		ImageView imageView = new ImageView("/texture/block_texture.png");
		imageView.setFitWidth(Block.SIZE);
		imageView.setFitHeight(Block.SIZE);
		setFilter(imageView, color);
		return imageView;
	}
}
