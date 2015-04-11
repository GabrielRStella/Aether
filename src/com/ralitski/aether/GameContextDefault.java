package com.ralitski.aether;

public class GameContextDefault implements GameContext {

	@Override
	public PlanetCreator getPlanetCreator(AetherGame game) {
		return new DebugPlanetCreator();
	}

	@Override
	public WorldRender getRenderer() {
		return new WorldRenderSimple();
	}

	@Override
	public InputHandler getInputHandler(AetherGame game, AetherWorld world) {
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

}
