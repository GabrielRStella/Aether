package com.ralitski.aether;

public interface GameContext {
	PlanetCreator getPlanetCreator(AetherGame game);
	WorldRender getRenderer();
	InputHandler getInputHandler(AetherGame game, AetherWorld world);
	ViewBox getViewBox(AetherGame game);
	void update();
	//TODO: add constants
	String getTitle();
}
