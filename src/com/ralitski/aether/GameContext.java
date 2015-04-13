package com.ralitski.aether;

public interface GameContext {
	PlanetCreator getPlanetCreator(AetherGame game);
	WorldRender getRenderer();
	ViewBox getViewBox(AetherGame game);
	CollisionDetector getCollisionDetector();
	void setup();
	void update(double timeStep);
	//TODO: add constants
	String getTitle();
	float getPlayerBoundaryDistance();
	float getBoundaryStrength();
}
