package com.ralitski.aether.debug;

import com.ralitski.aether.AetherGame;
import com.ralitski.aether.CollisionDetector;
import com.ralitski.aether.CollisionDetectorSimple;
import com.ralitski.aether.GameContext;
import com.ralitski.aether.PlanetCreator;
import com.ralitski.aether.ViewBox;
import com.ralitski.aether.WorldRender;

public class DebugGameContext implements GameContext {

	@Override
	public PlanetCreator getPlanetCreator() {
		return new DebugPlanetCreator();
	}

	@Override
	public WorldRender getRenderer() {
		return new DebugWorldRender();
	}

	@Override
	public ViewBox getViewBox(AetherGame game) {
		return new ViewBox(game);
	}

	@Override
	public CollisionDetector getCollisionDetector() {
		return new CollisionDetectorSimple();
	}

	@Override
	public void setup() {}

	@Override
	public void update(double timeStep) {}

	@Override
	public String getTitle() {
		return "Aether Testing";
	}

	@Override
	public float getBoundaryStrength() {
		return 5;
	}

	@Override
	public float getPlayerBoundaryDistance() {
		return 150;
	}

	@Override
	public float getRotationDegrees() {
		return 0;
	}

}
