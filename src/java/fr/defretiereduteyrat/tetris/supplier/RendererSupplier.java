package fr.defretiereduteyrat.tetris.supplier;

import fr.defretiereduteyrat.tetris.controller.Renderer;

/**
 * The interface Renderer supplier.
 */
@FunctionalInterface
public interface RendererSupplier {

	/**
	 * Create renderer renderer.
	 *
	 * @return the renderer
	 */
	Renderer createRenderer();
}
