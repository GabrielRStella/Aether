package com.ralitski.aether;

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
