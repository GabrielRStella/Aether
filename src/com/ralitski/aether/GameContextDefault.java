package com.ralitski.aether;

import com.ralitski.aether.force.ForceAccelerate;
import com.ralitski.aether.force.ForceAttractive;
import com.ralitski.aether.force.ForceTorque;
import com.ralitski.util.render.img.Color;

public class GameContextDefault implements GameContext {

	@Override
	public PlanetCreator getPlanetCreator(AetherGame game) {
		PlanetCreatorSimple creator = new PlanetCreatorSimple();
		creator.addType(Color.RED, Color.MAGENTA, new ForceAccelerate());
		creator.addType(Color.BLUE, Color.CYAN, new ForceAttractive());
		creator.addType(Color.GREEN, Color.YELLOW, new ForceTorque());
		return creator;
	}

	@Override
	public WorldRender getRenderer() {
		return new WorldRenderSimple();
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
	public String getTitle() {
		return "Aether";
	}
	
	@Override
	public void setup() {
		//TODO: add icons
	}

	@Override
	public void update(float timeStep) {}

	@Override
	public float getPlayerBoundaryDistance() {
		return 100;
	}

	@Override
	public float getBoundaryStrength() {
		return 4;
	}

	@Override
	public float getMaxPlayerDistance() {
		return 150;
	}

}