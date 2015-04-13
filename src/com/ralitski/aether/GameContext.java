package com.ralitski.aether;

public interface GameContext {
	PlanetCreator getPlanetCreator();
	WorldRender getRenderer();
	ViewBox getViewBox(AetherGame game);
	CollisionDetector getCollisionDetector();
	void setup();
	void update(double timeStep);
	float getRotationDegrees();
	//TODO: add constants
	String getTitle();
	float getPlayerBoundaryDistance();
	float getBoundaryStrength();
}
