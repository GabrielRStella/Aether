package com.ralitski.aether;

import com.ralitski.util.gui.Box;

public interface Renderer {
	//methods defined in order of call
	void renderBackground(Box box);
	void renderPlanet(Planet planet);
	void renderPlayer(Player player);
}