package fr.defretiereduteyrat.tetris.utils;

import fr.defretiereduteyrat.tetris.bricks.*;

import java.util.Random;

public class BrickUtils {

	public static Brick getRandomBrick() {
		int random = new Random().nextInt(7);
		return switch (random) {
			case 0 -> new IBrick();
			case 1 -> new JBrick();
			case 2 -> new LBrick();
			case 3 -> new OBrick();
			case 4 -> new SBrick();
			case 5 -> new TBrick();
			case 6 -> new ZBrick();
			default -> null;
		};
	}
}
