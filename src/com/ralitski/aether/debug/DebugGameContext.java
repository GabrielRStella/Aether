package com.ralitski.aether.debug;

import com.ralitski.aether.AetherDisplay;
import com.ralitski.aether.AetherGame;
import com.ralitski.aether.AetherWorld;
import com.ralitski.aether.CollisionDetector;
import com.ralitski.aether.CollisionDetectorSimple;
import com.ralitski.aether.GameContext;
import com.ralitski.aether.InputHandler;
import com.ralitski.aether.PlanetCreator;
import com.ralitski.aether.ViewBox;
import com.ralitski.aether.WorldRender;

public class DebugGameContext implements GameContext {

	@Override
	public PlanetCreator getPlanetCreator(AetherGame game) {
		return new DebugPlanetCreator();
	}

	@Override
	public WorldRender getRenderer() {
		return new DebugWorldRender();
	}

	@Override
	public InputHandler getInputHandler(AetherDisplay display, AetherGame game, AetherWorld world) {
		return new DebugInputHandler(world.getPlayer1(), world.getPlayer2());
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
	public void update(float timeStep) {}

	@Override
	public String getTitle() {
		return "Aether Testing";
	}

	@Override
	public float getMaxPlayerDistance() {
		return 250;
	}

	@Override
	public float getBoundaryStrength() {
		return 5;
	}

	@Override
	public float getPlayerBoundaryDistance() {
		return 150;
	}

}
