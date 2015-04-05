package com.ralitski.aether;

import com.ralitski.util.math.geom.d2.BoundingBox2d;

public interface Renderer {
	//methods defined in order of call
	void renderBackground(BoundingBox2d box);
	void renderPlanet(Planet planet);
	void renderPlayer(Player player);
}