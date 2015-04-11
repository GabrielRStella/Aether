package com.ralitski.aether;

public interface GameContext {
	PlanetCreator getPlanetCreator(AetherGame game);
	WorldRender getRenderer();
	InputHandler getInputHandler(AetherDisplay display, AetherGame game, AetherWorld world);
	ViewBox getViewBox(AetherGame game);
	CollisionDetector getCollisionDetector();
	void setup();
	void update(float timeStep);
	//TODO: add constants
	String getTitle();
	float getPlayerBoundaryDistance();
	float getBoundaryStrength();
	float getMaxPlayerDistance();
}
