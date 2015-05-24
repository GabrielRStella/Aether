package com.ralitski.aether;

import org.lwjgl.input.Keyboard;

import com.ralitski.aether.force.ForceAccelerate;
import com.ralitski.aether.force.ForceAttractive;
import com.ralitski.aether.force.ForceTorque;
import com.ralitski.aether.input.InputBundle;
import com.ralitski.aether.input.InputKeyboard;
import com.ralitski.util.render.img.Color;

public class GameContextDefault implements GameContext {

	@Override
	public PlanetCreator getPlanetCreator() {
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
	public void update(double timeStep, boolean tick) {}

	@Override
	public float getPlayerBoundaryDistance() {
		return 60;
	}

	@Override
	public float getBoundaryStrength() {
		return 6;
	}

	@Override
	public float getRotationDegrees() {
		return 0;//  (prevRot += 0.002F);
	}
	
//	private float prevRot;

	@Override
	public void renderLayer(int layer) {
	}

	@Override
	public int getPlayerCount() {
		return 2;
	}

	@Override
	public void setInput(InputHandler input) {
		InputBundle p1 = new InputBundle(
				new InputKeyboard(Keyboard.KEY_UP),
				new InputKeyboard(Keyboard.KEY_DOWN),
				new InputKeyboard(Keyboard.KEY_LEFT),
				new InputKeyboard(Keyboard.KEY_RIGHT)
				);
		input.setInput(0, p1);
		InputBundle p2 = new InputBundle(
				new InputKeyboard(Keyboard.KEY_W),
				new InputKeyboard(Keyboard.KEY_S),
				new InputKeyboard(Keyboard.KEY_A),
				new InputKeyboard(Keyboard.KEY_D)
				);
		input.setInput(1, p2);
	}

}
