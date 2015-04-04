package com.ralitski.aether;

import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

//TODO: keyboard/controller -> game abstraction layer
public class AetherGame extends ControllerMonitor implements InputUser {
	
	private ViewBox viewBox;
	private AetherWorld world;
	
	public AetherGame() {
		viewBox = new ViewBox(this);
		world = new AetherWorld(this, new PlanetCreatorSimple());
	}

	public AetherWorld getWorld() {
		return world;
	}
	
	public ViewBox getViewBox() {
		return viewBox;
	}

	@Override
	public void onMouseEvent(MouseEvent event) {
	}

	@Override
	public void onKeyEvent(KeyEvent event) {
	}

	@Override
	public void handleEvent(ControllerEvent event) {
	}
	
	@Override
	public void update() {
		super.update();
		world.update();
		viewBox.update();
	}
	
	public void render() {
		
	}
}
