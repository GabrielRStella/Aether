package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

public class GuiGame extends Gui {
	
	private ControllerMonitor controller;
	private GameContext context; //may be used...eventually...idk
	private AetherGame game;

	public GuiGame(Gui parent, AetherDisplay display, GameContext context) {
		super(parent);
		this.context = context;
		this.game = new AetherGame(this, display, context);
		controller = new ControllerMonitor(game);
	}
	
	public boolean renderParent() {
		return false;
	}
	
	public void render2d(float partial) {
		game.render();
		super.render2d(partial);
		//render context?
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
		game.update();
	}

}
