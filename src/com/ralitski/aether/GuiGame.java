package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

public class GuiGame extends Gui {
	
	private ControllerMonitor controller;
	private AetherGame game;

	public GuiGame(Gui parent, GameContext context) {
		super(parent);
		this.game = new AetherGame(this, context);
		controller = new ControllerMonitor(game);
	}
	
	public boolean renderParent() {
		return false;
	}
	
	public void render2d(float partial) {
		game.render();
		super.render2d(partial);
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
