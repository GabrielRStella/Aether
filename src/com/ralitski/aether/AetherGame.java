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
	
	public AetherGame(Gui owner, AetherDisplay display, GameContext context) {
		this.owner = owner;
		this.context = context;
		viewBox = context.getViewBox(this);
		world = new AetherWorld(this, context);
		input = new InputHandler(this, world);
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
	
	public void update(double timeStep) {
		input.update(timeStep);
		world.update(timeStep);
		viewBox.update();
		viewBox.getViewBox().setAngleDegrees(context.getRotationDegrees());
		context.update(timeStep);
	}
	
	public void render() {
		//fit to viewbox
		GL11.glPushMatrix();
		
		GuiManager manager = owner.getOwner();
		BoundingBox2d box = viewBox.getViewBox();
		float w2 = manager.getWindowWidth();
		float h2 = manager.getWindowHeight();
		float rot = context.getRotationDegrees();
		float xScale = w2 / box.getWidth();
		float yScale = h2 / box.getHeight();
		w2 /= 2F;
		h2 /= 2F;
		
		//scale view to world coordinates
		GL11.glScalef(xScale, yScale, 1);
		GL11.glTranslatef(-box.getMinX(), -box.getMinY(), 0);
		renderer.renderBackground(viewBox.getViewBox(), rot);
		context.renderLayer(GameContext.RENDER_POST_BACKGROUND);

		//rotate view
		GL11.glTranslatef(box.getMinX(), box.getMinY(), 0);
		w2 = box.getWidth() / 2F;
		h2 = box.getHeight() / 2F;
		GL11.glTranslatef(w2, h2, 0);
		GL11.glRotatef(rot, 0, 0, 1);
		GL11.glTranslatef(-w2, -h2, 0);
		GL11.glTranslatef(-box.getMinX(), -box.getMinY(), 0);

		context.renderLayer(GameContext.RENDER_PRE_PLANET);
		for(Planet planet : world.getPlanets()) {
			renderer.renderPlanet(planet);
		}
		context.renderLayer(GameContext.RENDER_POST_PLANET);
		
		context.renderLayer(GameContext.RENDER_PRE_PLAYER);
		renderer.renderPlayer1(world.getPlayer1());
		renderer.renderPlayer2(world.getPlayer2());
		context.renderLayer(GameContext.RENDER_POST_PLAYER);
		
		GL11.glPopMatrix();
	}
}
