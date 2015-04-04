package com.ralitski.aether;

import com.ralitski.util.gui.Gui;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

public class GuiGame extends Gui {
	
	private AetherGame game;

	public GuiGame(Gui parent, AetherGame game) {
		super(parent);
		this.game = game;
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
		game.update();
	}

}
