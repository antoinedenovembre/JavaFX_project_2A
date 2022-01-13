package fr.defretiereduteyrat.tetris.supplier;

import fr.defretiereduteyrat.tetris.controller.Renderer;

@FunctionalInterface
public interface RendererSupplier {

	Renderer createRenderer();
}
