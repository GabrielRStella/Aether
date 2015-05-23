package com.ralitski.aether;

public interface GameContext {
	//render layer constants
	int RENDER_POST_BACKGROUND = 0;
	int RENDER_PRE_PLANET = 2;
	int RENDER_POST_PLANET = 3;
	int RENDER_PRE_PLAYER = 4;
	int RENDER_POST_PLAYER = 5;
	
	//methods
	PlanetCreator getPlanetCreator();
	WorldRender getRenderer();
	ViewBox getViewBox(AetherGame game);
	CollisionDetector getCollisionDetector();
	void setup();
	void update(double timeStep);
	float getRotationDegrees();
	void renderLayer(int layer);
	
	//TODO: game constants
	String getTitle();
	float getPlayerBoundaryDistance();
	float getBoundaryStrength();
	int getPlayerCount();
}
