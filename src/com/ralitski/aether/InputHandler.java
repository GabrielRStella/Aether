package com.ralitski.aether;

import com.ralitski.util.input.ControllerUser;
import com.ralitski.util.input.InputUser;

public interface InputHandler extends InputUser, ControllerUser {
	void update();
}
