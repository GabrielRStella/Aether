package com.ralitski.aether.gui;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import org.lwjgl.opengl.GL11;

import com.ralitski.aether.AetherDisplay;
import com.ralitski.aether.AetherWorld;
import com.ralitski.aether.GameContext;
import com.ralitski.aether.InputHandler;
import com.ralitski.aether.Planet;
import com.ralitski.aether.Player;
import com.ralitski.aether.Profiler;
import com.ralitski.aether.ViewBox;
import com.ralitski.aether.WorldRender;
import com.ralitski.aether.event.EventSystem;
import com.ralitski.util.Settings;
import com.ralitski.util.Ticker;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.gui.GuiManager;
import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;
import com.ralitski.util.math.geom.d2.BoundingBox2d;

public class GuiGame extends Gui implements WindowListener, ControllerUser {
	
	private ControllerMonitor controller;
	private GameContext context; //may be used...eventually...idk
	private Ticker ticker;
	private ViewBox viewBox;
	private AetherWorld world;
	private InputHandler input;
	private WorldRender renderer;
	private Settings settings;
	private EventSystem events;

	public GuiGame(Gui parent, AetherDisplay display, GameContext context) {
		super(parent);
		this.context = context;
		controller = new ControllerMonitor(this);
		ticker = Ticker.ticksPerSecond(10);
		ticker.time();
		viewBox = context.getViewBox(this);
		world = new AetherWorld(this, context);
		input = new InputHandler(this, world);
		renderer = context.getRenderer();
		context.setInput(input);
		settings = new Settings(context.getTitle());
		settings.load();
		events = new EventSystem();
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
	
	public EventSystem getEventSystem() {
		return events;
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
	
	public boolean renderParent() {
		return false;
	}
	
	public void render2d(float partial) {
		Profiler profiler = AetherDisplay.getInstance().getProfiler();
		profiler.startSection("render");
		
		super.render2d(partial);
		render();
		
		profiler.endSection();
	}
	
	public void render() {
		//fit to viewbox
		GL11.glPushMatrix();
		
		GuiManager manager = getOwner();
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

		Profiler profiler = AetherDisplay.getInstance().getProfiler();
		profiler.startSection("background");
		
		renderer.renderBackground(viewBox.getViewBox(), rot);
		context.renderLayer(GameContext.RENDER_POST_BACKGROUND);
		
		profiler.endSection();

		//rotate view
		float x = box.getCenter().getX();
		float y = box.getCenter().getY();
		GL11.glTranslatef(x, y, 0);
		GL11.glRotatef(rot, 0, 0, 1);
		GL11.glTranslatef(-x, -y, 0);

		profiler.startSection("planet");
		context.renderLayer(GameContext.RENDER_PRE_PLANET);
		for(Planet planet : world.getPlanets()) {
			renderer.renderPlanet(planet);
		}
		context.renderLayer(GameContext.RENDER_POST_PLANET);
		profiler.endSection();

		profiler.startSection("player");
		context.renderLayer(GameContext.RENDER_PRE_PLAYER);
		int pCount = context.getPlayerCount();
		int i = 0;
		for(Player player : world.getPlayers()) {
			renderer.renderPlayer(player, i++, pCount);
		}
		context.renderLayer(GameContext.RENDER_POST_PLAYER);
		profiler.endSection();
		
		GL11.glPopMatrix();
	}
	
	@Override
	public void update() {
		Profiler profiler = AetherDisplay.getInstance().getProfiler();
		profiler.startSection("logic");
		
		double d = ticker.time();
		boolean tick = getOwner().getCurrentScreen() == this;
		if(tick) controller.update();
		if(d != 0D) update(d, tick);
		
		profiler.endSection();
	}
	
	public void update(double timeStep, boolean tick) {
		if(tick) {
			input.update(timeStep);
			world.update(timeStep);
		}
		viewBox.update();
		viewBox.getViewBox().setAngleDegrees(context.getRotationDegrees());
		context.update(timeStep, tick);
	}

    @Override
    public void windowOpened(WindowEvent event) {
        // TODO window created
    }

    @Override
    public void windowActivated(WindowEvent event) {
        // TODO window focused
    }

    @Override
    public void windowDeactivated(WindowEvent event) {
        // TODO window focus lost
    	pause();
    }

    @Override
    public void windowClosed(WindowEvent event) {
        // TODO window closed via call to dispose()
    }

    @Override
    public void windowClosing(WindowEvent event) {
        //red X button clicked
    }

    @Override
    public void windowDeiconified(WindowEvent event) {
        // TODO window selected from taskbar
    }

    @Override
    public void windowIconified(WindowEvent event) {
        // TODO window minimized to taskbar
    	pause();
    }
    
	public void pause() {
		GuiGameMenu gui = new GuiGameMenu(this);
		gui.init();
		getOwner().openScreen(gui);
	}
	
	public void endGame() {
		GuiEndGameMenu gui = new GuiEndGameMenu(this);
		gui.init();
		getOwner().openScreen(gui);
	}
}
