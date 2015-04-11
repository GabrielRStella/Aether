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
	public InputHandler getInputHandler(AetherGame game, AetherWorld world) {
		return new DebugInputHandler(world.getPlayer1(), world.getPlayer2());
	}

	@Override
	public ViewBox getViewBox(AetherGame game) {
		return new ViewBox(game);
	}

	@Override
	public String getTitle() {
		return "Aether Testing";
	}

	@Override
	public void update() {}

}
