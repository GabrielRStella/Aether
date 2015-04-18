package com.ralitski.aether;

import com.ralitski.util.Ticker;
import com.ralitski.util.gui.Gui;
import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

public class GuiGame extends Gui {
	
	private ControllerMonitor controller;
//	private GameContext context; //may be used...eventually...idk
	private AetherGame game;
	private Ticker ticker;

	public GuiGame(Gui parent, AetherDisplay display, GameContext context) {
		super(parent);
		display.setTitle("Aether (" + context.getTitle() + ")");
//		this.context = context;
		this.game = new AetherGame(this, display, context);
		controller = new ControllerMonitor(game);
		ticker = Ticker.ticksPerSecond(10);
		ticker.time();
	}
	
	public boolean renderParent() {
		return false;
	}
	
	public void render2d(float partial) {
		super.render2d(partial);
		game.render();
//		render context
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
		game.onMouseEvent(event);
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
		game.onKeyEvent(event);
	}
	
	@Override
	public void update() {
		controller.update();
		double d = ticker.time();
		if(d != 0D) game.update(d);
	}

}
