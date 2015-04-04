package com.ralitski.aether;

import com.ralitski.util.gui.Box;

public class ViewBox {

	private AetherGame game;
	private Box viewBox;
	
	public ViewBox(AetherGame game) {
		this.game = game;
		viewBox = new Box();
	}
	
	public Box getViewBox() {
		return viewBox;
	}

	public void update() {
		AetherWorld world = game.getWorld();
	}
}
