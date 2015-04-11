package com.ralitski.aether;

import org.lwjgl.opengl.GL11;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.math.geom.d2.BoundingBox2d;

public class AetherGame implements InputUser, ControllerUser {
	
	private Gui owner;
	private GameContext context;
	private ViewBox viewBox;
	private AetherWorld world;
	private InputHandler input;
	private WorldRender renderer;
	
	public AetherGame(Gui owner, GameContext context) {
		this.owner = owner;
		this.context = context;
		viewBox = context.getViewBox(this);
		world = new AetherWorld(this, context.getPlanetCreator(this));
		input = context.getInputHandler(this, world);
		renderer = context.getRenderer();
	}
	
	public Gui getOwner() {
		return owner;
	}
	
	public GameContext getContext() {
		return context;
	}

	public AetherWorld getWorld() {
		return world;
	}
	
	public ViewBox getViewBox() {
		return viewBox;
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		input.onMouseEvent(event);
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		input.onKeyEvent(event);
	}

	@Override
	public void onControllerEvent(ControllerEvent event) {
		input.onControllerEvent(event);
	}
	
	public void update() {
		input.update();
		world.update();
		viewBox.update();
		context.update();
	}
	
	public void render() {
		//fit to viewbox
		GL11.glPushMatrix();
		rescale();
		//render stuff
		renderer.renderBackground(viewBox.getViewBox());
		for(Planet planet : world.getPlanets()) {
			renderer.renderPlanet(planet);
		}
		renderer.renderPlayer1(world.getPlayer1());
		renderer.renderPlayer2(world.getPlayer2());
		GL11.glPopMatrix();
	}
	
	private void rescale() {
		GuiManager manager = owner.getOwner();
		BoundingBox2d box = viewBox.getViewBox();
		float xScale = (float)manager.getWindowWidth() / box.getWidth();
		float yScale = (float)manager.getWindowHeight() / box.getHeight();
		GL11.glScalef(xScale, yScale, 1);
		GL11.glTranslatef(-box.getMinX(), -box.getMinY(), 0);
	}
}
