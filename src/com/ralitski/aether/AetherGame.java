package com.ralitski.aether;

import com.ralitski.util.input.ControllerMonitor;
import com.ralitski.util.input.InputUser;
import com.ralitski.util.input.event.ControllerEvent;
import com.ralitski.util.input.event.KeyEvent;
import com.ralitski.util.input.event.MouseEvent;

//TODO: keyboard/controller -> game abstraction layer
public class AetherGame extends ControllerMonitor implements InputUser {

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
	}
	
	public void render() {
		
	}
}
